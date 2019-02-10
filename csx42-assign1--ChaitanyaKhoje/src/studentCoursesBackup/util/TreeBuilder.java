package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;
import java.util.List;

public class TreeBuilder {

    private Node rootNode;
    private FileProcessor fileProcessor = new FileProcessor();
    private static boolean insertStatus;

    /**
     * processNode is the parent method for insert and delete methods.
     *
     * @param fileLineIn A single file line passed as parameter which will be split into tokens.
     * @param actionIn   Specifies if it's an insert or a delete operation.
     */
    public void processNode(String fileLineIn, Action actionIn) {

        if (!fileLineIn.equals("")) {
            String[] tokens = fileLineIn.split(":| :|: ", 2);
            int BNumber = Integer.parseInt(tokens[0]);
            String courseName = tokens[1];

            if (actionIn == Action.INSERT) {
                insertNode(BNumber, courseName);
                insertStatus = true;
            } else if (actionIn == Action.DELETE) {
                deleteNode(BNumber, courseName);
            }
        }
    }

    /**
     * Inserts node into the trees.
     *
     * @param BNumberIn    New node's BNumber.
     * @param courseNameIn New node's courseName
     */
    private void insertNode(int BNumberIn, String courseNameIn) {

        Node node_orig = new Node();
        node_orig.setbNumber(BNumberIn);
        node_orig.getCourseNames().add(courseNameIn);
        Node backup_Node_1 = node_orig.clone();
        Node backup_Node_2 = node_orig.clone();
        node_orig.register(backup_Node_1);
        node_orig.register(backup_Node_2);


        // Binary search tree implementation for inserting a node in the tree.

        if (rootNode == null) {
            rootNode = node_orig;
        } else {
            Node currentNode = rootNode;

            while (true) {
                if (BNumberIn < currentNode.getbNumber()) {
                    if (currentNode.getLeftChild() == null) {
                        currentNode.setLeftChild(node_orig);
                        currentNode.notifyListeners(node_orig, "LEFT");
                        return;
                    } else {
                        currentNode = currentNode.getLeftChild();
                    }
                } else if (BNumberIn == currentNode.getbNumber()) {
                    currentNode.getCourseNames().add(courseNameIn);
                    currentNode.notifyListeners(courseNameIn, Action.INSERT);
                    return;
                } else {
                    if (currentNode.getRightChild() == null) {
                        currentNode.setRightChild(node_orig);
                        currentNode.notifyListeners(node_orig, "RIGHT");
                        return;
                    } else {
                        currentNode = currentNode.getRightChild();
                    }
                }
            }
        }
    }

    /**
     * This method deletes the node from the original tree and uses observer pattern to delete
     * the same node from the back up trees via notifyListeners().
     *
     * @param BNumberIn
     * @param courseName
     */
    private void deleteNode(int BNumberIn, String courseName) {

        Node currentNode = rootNode;

        while (true) {
            /*
             *  The node to be deleted has a BNumber less than the node we are currently looking at.
             *  So we traverse to the left child.
             */
            if (BNumberIn < currentNode.getbNumber()) {
                if (currentNode.getLeftChild() == null) {
                    break;
                } else {
                    currentNode = currentNode.getLeftChild();
                }
                /*
                 *  The node to be deleted has a BNumber greater than the node we are currently looking at.
                 *  So we traverse to the right child.
                 */
            } else if (BNumberIn > currentNode.getbNumber()) {
                if (currentNode.getRightChild() == null) {
                    break;
                } else {
                    currentNode = currentNode.getRightChild();
                }
                /*
                 *  The node to deleted has a BNumber equal to the current node.
                 *  Drop the courses from the current node's set by comparing them to the courses of the node passed
                 *  to the method.
                 */
            } else {
                currentNode.getCourseNames().remove(courseName);
                currentNode.notifyListeners(courseName, Action.DELETE);
                break;
            }
        }
    }

    /**
     * printNodes calls the inOrderTraversal method in the Traversal class and stores the results in resultsIn.
     *
     * @param resultsIn Result of a tree.
     * @param tree      Tree on which in-order traversal for writing to the output file is to be done.
     */
    public void printNodes(Results resultsIn, Node tree) {
        Traversal traversal = new Traversal();
        List<Node> resultNodes = traversal.inOrderTraversal(tree);
        resultsIn.addStudentCourses(resultNodes);
    }

    /**
     * @return returns the root node.
     */
    public Node getRootNode() {
        return rootNode;
    }

    /**
     * @return returns the insertion status. Use while checking if the input file was empty, before deletion operation.
     */
    public static boolean isInsertStatus() {
        return insertStatus;
    }

    /**
     * Insert status is set if the input.txt is not empty and an insert into the tree is made.
     *
     * @param insertStatus
     */
    public static void setInsertStatus(boolean insertStatus) {
        TreeBuilder.insertStatus = insertStatus;
    }

    @Override
    public String toString() {
        return "TreeBuilder{" +
                "rootNode=" + rootNode +
                ", fileProcessor=" + fileProcessor +
                '}';
    }
}
