public interface DecisionTreeInterface<T> {

    /*
     * to make things clear:
     * 
     * 1. root node: the very top node of the tree
     * 2. parent node: a node that has child nodes 
     * 3. child node: a node that has a parent node
     * 4. leaf node: a node that has no child nodes, they are at the most bottom of
     * the tree
     * 
     */

    public T getRootData(); // to get the data of the root node (question)

    public boolean isEmpty(); // check whether the tree have nodes, if dont have, then return true

    public void clear(); // clear all the data in the tree, by setting root = null

    public T getCurrentData(); // to get the data of the current node

    public void setCurrentData(T newData); // to update the data of the current node

    public void setAnswers(T answerForNo, T answerForYes); // modify the current node by setting 2 different child
                                                           // nodes: yes and no

    public boolean isAnswer(); // check whether the current node is an answer (leaf node)

    public void advanceToNo(); // moves to the no child node

    public void advanceToYes(); // moves to the yes child node

    public void reset(); // reset your current position in the tree back to the root node
}