import java.io.Serializable;

public class DecisionTreeNode<T> implements Serializable {

    /*
     * What is DecisionTreeNode<T> for?
     * it focuses on handling the data and connections between the nodes (parent and
     * child nodes)
     * 
     * 
     * Why use serializable?
     * In this project, its to save and load the tree structure to a binary file
     * (.dat)
     */

    // instance variables
    private static final long serialVersionUID = 1L; //
    private T data; // stores either a question or an answer or other data types (int, double, char
                    // etc)
    private DecisionTreeNode<T> no; // points to the left node
    private DecisionTreeNode<T> yes; // points to the right node

    // constructor
    public DecisionTreeNode(T data) { // initializes a node with either a question or an answer
        this.data = data;
        // this.no = null;
        // this.yes = null;
    }

    // public static long getSerialversionuid() {
    // return serialVersionUID;
    // }

    public T getData() { // to get the data of a specific node
        return data;
    }

    public void setData(T data) { // to update the data of a specific node
        this.data = data;
    }

    public DecisionTreeNode<T> getNo() { // get the child node (No)
        return no;
    }

    public void setNo(DecisionTreeNode<T> no) { // manually set the child node as No
        this.no = no;
    }

    public DecisionTreeNode<T> getYes() { // get the child node (Yes)
        return yes;
    }

    public void setYes(DecisionTreeNode<T> yes) { // manually set the child node as Yes
        this.yes = yes;
    }

    public boolean isLeaf() { // to differentiate between the nodes (whether its a question or an answer)
        return no == null && yes == null;
    }
}
