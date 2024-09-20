
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class GuessingGame implements DecisionTreeInterface<String>, Serializable {

    // instance variables
    private static final long serialVersionUID = 1L;
    private DecisionTreeNode<String> root; // root node
    private DecisionTreeNode<String> current; // current node (the node you are on now)

    // constructor
    public GuessingGame() { // set the root node with a question and 2 child nodes as answers
        root = new DecisionTreeNode<String>("Is it a mammal?");
        root.setNo(new DecisionTreeNode<String>("a Crocodile"));
        root.setYes(new DecisionTreeNode<String>("an Elephant"));
        current = root; // because we start at the root node
    }

    // ----------------- Methods from DecisionTreeInterface.java
    // -----------------------------
    @Override
    public String getRootData() { // get the question
        return root.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public String getCurrentData() {
        return current.getData();
    }

    @Override
    public void setCurrentData(String newData) {
        current.setData(newData);
    }

    @Override
    public void setAnswers(String answerForNo, String answerForYes) {
        current.setNo(new DecisionTreeNode<String>(answerForNo));
        current.setYes(new DecisionTreeNode<String>(answerForYes));
    }

    @Override
    public boolean isAnswer() {
        return current.isLeaf();
    }

    @Override
    public void advanceToNo() {
        current = current.getNo();
    }

    @Override
    public void advanceToYes() {
        current = current.getYes();
    }

    @Override
    public void reset() {
        current = root;
    }
    // ---------------------------------------------------------------------------------------

    public void play() {
        Scanner input = new Scanner(System.in);
        reset(); // start by resetting the tree to the root node
        while (true) {
            if (isAnswer()) { // if current node is an answer (leaf node)
                System.out.println("Is it " + getCurrentData() + "? (yes/no)"); // get the current data of the node from the constructor
                String response = input.nextLine();

                if (response.equalsIgnoreCase("yes")) { // if answer is yes, will print the line below
                    System.out.println("I guessed it!");
                } else { // if answer is no

                    System.out.println("I give up. What is the correct answer?");
                    String correctAnimal = input.nextLine(); // user inputs an animal (answer)

                    System.out.println(
                            "Enter a question to distinguish " + correctAnimal + " from " + getCurrentData() + ".");
                    String newQuestion = input.nextLine(); // user need to input a new question

                    System.out.println("For " + correctAnimal + ", is the answer to the question yes or no?");
                    String answer = input.nextLine(); // user need to input yes or no

                    String incorrectGuess = getCurrentData(); // get the wrong answer
                    setCurrentData(newQuestion); // replace the wrong answer with the new question user has inputted

                    if (answer.equalsIgnoreCase("yes")) { // if yes
                        setAnswers(incorrectGuess, correctAnimal); // set the two nodes where incorrectGuess is No and correctAnimal is Yes
                    } else { // if no
                        setAnswers(correctAnimal, incorrectGuess); // set the two nodes where correctAnimal is No and incorrectGuess is Yes
                    }
                }

                break; // exit out the while true loop

            } else { // if the current node is a question (leaf node)
                System.out.println(getCurrentData() + " (yes/no)"); // print out the current node of the data (which is a question)
                String response = input.nextLine(); // user respond yes or no

                if (response.equalsIgnoreCase("yes")) { // if yes
                    advanceToYes(); // move to Yes node
                } else { // if no
                    advanceToNo(); // move to No node
                }
            }
        }
    }

    // ------------------------------------------ Extra stuff ---------------------------------------------
    public void saveTree(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            // System.out.println("Error saving game: " + e.getMessage()); // debug
        }
    }

    public static GuessingGame loadTree(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (GuessingGame) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // System.out.println("Error loading game: " + e.getMessage()); // debug
            return null;
        }
    }

    // output the tree (although it doesnt even remotely look like a tree)
    public void printTree(DecisionTreeNode<String> node, String indent, boolean isYes) {
        if (node == null) {
            return;
        }

        // Print current node
        String prefix = isYes ? "YES -> " : "NO -> ";
        System.out.println(indent + prefix + node.getData());

        if (!node.isLeaf()) {
            // Recursive calls for the NO and YES branches
            printTree(node.getNo(), indent + "     ", false);
            printTree(node.getYes(), indent + "     ", true);
        }
    }
    // ------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        GuessingGame game;
        File file = new File("gameTree.dat");

        if (file.exists()) {
            game = GuessingGame.loadTree("gameTree.dat");
            if (game == null) {
                game = new GuessingGame();
            }
        } else {
            game = new GuessingGame();
        }

        game.play();
        game.printTree(game.root, "", true);
        game.saveTree("gameTree.dat");
    }
}
