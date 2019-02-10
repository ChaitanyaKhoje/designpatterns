package studentCoursePlanner.driver;

import studentCoursePlanner.state.CourseManager;
import studentCoursePlanner.util.Results;

public class Driver {

    public static void main(String[] args) {

        if (validate(args)) {
            CourseManager courseManager = new CourseManager();
            boolean graduated = courseManager.processStudentGraduation(args[0]);
            displayResults(graduated, args);
        }
    }

    /**
     * This method is used to validate the command line arguments and handle any errors related to it.
     * @param argsIn Program arguments are passed to validate method for error handling purposes.
     * @return files;String array of input and delete file paths.
     */
    private static boolean validate(String[] argsIn) {

        try {
            if (argsIn.length != 2 || argsIn[0].equals("${arg0}") || argsIn[1].equals("${arg1}")) {
                System.err.println("Arguments passed were either less/more than expected!\nThe program accepts 2 arguments.");
                return false;
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("Arguments passed were either less/more than expected!");
        } finally { }
        return true;
    }

    private static void displayResults(boolean graduatedIn, String[] argsIn) {

        Results results = new Results();
        String result = results.display(graduatedIn);
        results.writeOutput(result, argsIn[1]);
    }
}

