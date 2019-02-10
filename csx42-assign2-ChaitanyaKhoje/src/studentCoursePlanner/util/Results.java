package studentCoursePlanner.util;

import studentCoursePlanner.state.StudentManager;

import java.util.ArrayList;
import java.util.List;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    private List<String> courses = new ArrayList<String>(StudentManager.getCoursesTaken());
    private StringBuilder sb = new StringBuilder();
    private String result = "";

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

    @Override
    public String display(boolean graduated) {

        result = buildOutput(graduated);
        System.out.println("Output: " + result);
        return result;
    }


    /**
     * String builder is used to append output.
     * Used to display in the console.
     */
    private String buildOutput(boolean graduated) {

        //sb.setLength(0);    // Clearing the stringbuilder.
        sb.append(StudentManager.getbNumber());
        sb.append(": ");
        for (String course: courses) {
            sb.append(course);
            sb.append(" ");
        }
        if (!graduated) {
            sb.append(" Cannot Graduate!");
        }
        sb.append("[Semester(s): " + StudentManager.getSemesterCount() + "]");
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> coursesIn) {
        courses = courses;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String resultIn) {
        result = result;
    }
}
