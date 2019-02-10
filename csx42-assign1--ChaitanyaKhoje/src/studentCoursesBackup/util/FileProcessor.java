package studentCoursesBackup.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor {

    private Scanner scanner = null;

    FileProcessor() {
    }

    /**
     * Constructor for FileProcessor; takes in the file path and initializes the scanner with it.
     *
     * @param filePathIn input.txt/delete.txt path is passed for Scanner.
     */
    public FileProcessor(String filePathIn) {
        File file = null;
        if (!filePathIn.isEmpty() || !filePathIn.equals("")) {
            file = new File(filePathIn);
        }
        try {
            if (file != null) this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (NullPointerException npe){
            System.out.println("Seems like a file was missing while passing the arguments to the program.");
        } finally {
            System.out.println("");
        }
    }

    /**
     * This method is called from Results class. It writes to the three output files.
     *
     * @param resultIn   The result to be written to the file.
     * @param filePathIn The file to write the result in.
     */
    public static void write(String resultIn, String filePathIn) {

        File file;
        BufferedWriter bufferedWriter = null;
        try {
            file = new File(filePathIn);
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(resultIn);
        } catch (IOException ioe) {
            System.out.println("One or more output files were not found!");
        } finally {
            try {
                if (bufferedWriter != null) bufferedWriter.close();
            } catch (IOException e) {
                System.out.println();
            }
        }
    }

    /**
     * Gets a single line from a file.
     *
     * @return returns a single file line.
     */
    public String getNextLine() {

        String fileLine;
        try {
            fileLine = scanner.nextLine();
        } finally {
            if (scanner == null) {
                System.out.println("The file could not be found!");
            }
        }
        return fileLine;
    }

    /**
     * Used while iterating over input.txt and delete.txt
     *
     * @return returns true if there is another line in the input.
     */
    public boolean hasNextLine() {

        return this.scanner.hasNextLine();
    }

    @Override
    public String toString() {
        return "FileProcessor{}";
    }
}
