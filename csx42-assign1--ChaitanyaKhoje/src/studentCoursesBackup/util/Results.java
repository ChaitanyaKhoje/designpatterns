package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;
import java.util.ArrayList;
import java.util.List;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    private List<Node> studentCourses = new ArrayList<Node>();

    public void addStudentCourses(List<Node> nodes) {

        if (nodes != null) {
            studentCourses = nodes;
        }
    }

    /**
     * Writes the output to the three output files.
     *
     * @param resultIn   The result to be written to the file.
     * @param filePathIn The file to write the result in.
     */
    @Override
    public void writeOutput(String resultIn, String filePathIn) {
        FileProcessor.write(resultIn, filePathIn);
    }

    /**
     * String builder is used to append output.
     * Used to display in the console.
     */
    @Override
    public String display() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Node node : studentCourses) {
            sb.append(node.getbNumber());
            sb.append(": ");
            for (String course : node.getCourseNames()) {
                sb.append(course);
                sb.append(" ");
            }
            sb.append(System.getProperty("line.separator"));
        }
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }
}
