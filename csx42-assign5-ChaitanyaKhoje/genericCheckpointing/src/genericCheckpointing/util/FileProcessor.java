package genericCheckpointing.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor {

    private static Scanner scanner = null;
    private String fileLine = "";
    private static File file;

    public FileProcessor() { }

    /**
     * Method for openFile; takes in the file path and initializes the scanner with it.
     *
     * @param filePathIn input.txt/delete.txt path is passed for Scanner.
     */
    public void openFile(String filePathIn) {
        File file1 = null;
        if (!filePathIn.isEmpty() || !filePathIn.equals("")) {
            file1 = new File(filePathIn);
            this.file = file1;
        }
        try {
            if (file1 != null) this.scanner = new Scanner(file1);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.out.println("Creating a new file, please run the program again for serdeser mode!");
            try {
                file1.createNewFile();
            } catch (IOException e1) {
                System.out.println("");
            }
            System.exit(0);
        } catch (NullPointerException npe){
            System.out.println("Seems like a file was missing while passing the arguments to the program.");
            System.exit(0);
        } finally {
            System.out.println("");
        }
    }

    /**
     * This method is called from Results class. It writes to the three output files.
     *
     * @param resultIn   The result to be written to the file.
     * @param file The file to write the result in.
     */
    public static void write(String resultIn, File file) {

        BufferedWriter bufferedWriter = null;
        try {
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
                System.err.println("The file could not be found or the file is empty!");
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

    public File getFile() {
        return file;
    }

    public void setFile(File fileIn) {
        file = fileIn;
    }

    @Override
    public String toString() {
        return "openFile{}";
    }
}
