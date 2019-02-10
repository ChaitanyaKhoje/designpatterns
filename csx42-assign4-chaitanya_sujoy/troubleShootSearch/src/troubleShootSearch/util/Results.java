package troubleShootSearch.util;

import java.io.InputStream;

/**
 * Results class just holds the StringBuilder in which strings are appended throughout the process of the program.
 */
public class Results {

    public static StringBuilder sb = new StringBuilder();

    public void writeOutput(String[] args) {

        FileProcessor fileProcessor = new FileProcessor();
        Utility utility = new Utility();
        InputStream outputPathIS = getClass().getResourceAsStream("output.txt");
        fileProcessor.write(Results.sb.toString(), outputPathIS);
    }
}
