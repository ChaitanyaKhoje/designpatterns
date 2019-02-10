package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;
import java.util.ArrayList;
import java.util.List;

public class Traversal {

    /**
     * In-order traversal method for printing.
     * @param root
     * @return results; a list of Nodes.
     */
    public List<Node> inOrderTraversal(Node root) {

        List<Node> results = new ArrayList<Node>();
        helper(root, results);
        return results;
    }

    /**
     * Recursive traversal through the tree.
     * @param node
     * @param results
     */
    private void helper(Node node, List<Node> results) {

        if (node == null) return;
        helper(node.getLeftChild(), results);
        results.add(node);
        helper(node.getRightChild(), results);
    }
}
