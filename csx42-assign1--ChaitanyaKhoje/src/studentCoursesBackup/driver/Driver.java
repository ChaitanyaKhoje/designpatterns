package studentCoursesBackup.driver;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.Action;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;

public class Driver {

    private static TreeBuilder treeBuilder = new TreeBuilder();

    /**
     * Main method:
     * Validates arguments
     * Builds trees
     * Gets root node and sends it to a method for displaying the results.
     * The results are also written to the output files.
     * <p>
     * Since no other classes are going to create Driver's instances, all methods in the Driver are made static
     * to handle non static methods being called in a static method (main method).
     *
     * @param args Program arguments
     */
    public static void main(String[] args) {

        if (validate(args)) {
            buildTrees(args[0], args[1]);
            Node originalTree = treeBuilder.getRootNode();
            displayResults(originalTree, args);
        }
    }

    /**
     * This method is used to validate the command line arguments and handle any errors related to it.
     *
     * @param argsIn Program arguments are passed to validate method for error handling purposes.
     * @return files;String array of input and delete file paths.
     */
    private static boolean validate(String[] argsIn) {

        try {

            if (argsIn.length != 5 || argsIn[0].equals("${arg0}") || argsIn[1].equals("${arg1}") || argsIn[2].equals("${arg2}")
                    || argsIn[3].equals("${arg3}") || argsIn[4].equals("${arg4}")) {

                System.err.println("Arguments passed were either less/more than expected!\nThe program accepts 5 arguments.");
                return false;
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("Arguments passed were either less/more than expected!");
        } finally { }
        return true;
    }

    /**
     * buildTrees builds the 3 trees (original, backup1, backup2) in TreeBuilder via processNode.
     * The processNode method invokes insert and delete methods depending on desired Action.
     *
     * @param inputTxtPath  input.txt file path
     * @param deleteTxtPath delete.txt file path
     */
    private static void buildTrees(String inputTxtPath, String deleteTxtPath) {

        FileProcessor fileProcessorInput = new FileProcessor(inputTxtPath);
        FileProcessor fileProcessorDelete = new FileProcessor(deleteTxtPath);
        String fileLine;

        while (fileProcessorInput.hasNextLine()) {
            fileLine = fileProcessorInput.getNextLine();
            treeBuilder.processNode(fileLine, Action.INSERT);
        }

        if (TreeBuilder.isInsertStatus()) {
            while (fileProcessorDelete.hasNextLine()) {
                fileLine = fileProcessorDelete.getNextLine();
                treeBuilder.processNode(fileLine, Action.DELETE);
            }
        }
    }

    /**
     * displayResults method prints the results.
     *
     * @param originalNodeIn Original tree's node.
     * @param filesIn        String array of files.
     */
    private static void displayResults(Node originalNodeIn, String[] filesIn) {

        Node backupTree1 = null;
        Node backupTree2 = null;
        if (originalNodeIn != null && originalNodeIn.getObservers().size() > 0) {
            backupTree1 = (Node) originalNodeIn.getObservers().get(0);
            backupTree2 = (Node) originalNodeIn.getObservers().get(1);
        }
        Results resultsNodeOrig = new Results();
        treeBuilder.printNodes(resultsNodeOrig, originalNodeIn);
        System.out.println("Original Tree: ");
        String originalTreeResult = resultsNodeOrig.display();
        System.out.println(originalTreeResult);
        resultsNodeOrig.writeOutput(originalTreeResult, filesIn[2]);

        Results resultsBackupNode1 = new Results();
        treeBuilder.printNodes(resultsBackupNode1, backupTree1);
        System.out.println("Backup Tree 1: ");
        String backup1TreeResult = resultsBackupNode1.display();
        System.out.println(backup1TreeResult);
        resultsNodeOrig.writeOutput(backup1TreeResult, filesIn[3]);

        Results resultsBackupNode2 = new Results();
        treeBuilder.printNodes(resultsBackupNode2, backupTree2);
        System.out.println("Backup Tree 2: ");
        String backup2TreeResult = resultsBackupNode2.display();
        System.out.println(backup2TreeResult);
        resultsNodeOrig.writeOutput(backup2TreeResult, filesIn[4]);
    }
}
