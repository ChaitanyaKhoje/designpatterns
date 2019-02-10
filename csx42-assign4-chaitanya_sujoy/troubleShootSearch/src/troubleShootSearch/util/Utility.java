package troubleShootSearch.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utility {

    public static Map<String, String> synonymsMap = new HashMap<String, String>();
    public static List<String> keywordList = new ArrayList<String>();

    public Utility() {}
    /**
     * This method is used to validate the command line arguments and handle any errors related to it.
     *
     * @param argsIn Program arguments are passed to validate method for error handling purposes.
     * @return files;String array of input and delete file paths.
     */
	public void validate(String[] argsIn) {

		if (argsIn.length > 1 || null == argsIn) {
			System.err.println("Arguments passed were either less/more than expected!\nThe program can accepts 1 arguments.");
			System.exit(0);
		}
		if(argsIn.length != 1 || argsIn[0].equals("${arg0}") || (Integer.parseInt(argsIn[0]) > 2)) {
			System.err.println("Debug arguments can take values from 0-2");
			System.exit(0);
		}
		buildInput(argsIn);
		if (argsIn.length == 1) {
			int debugValue = 0;
			try {
				debugValue = Integer.parseInt(argsIn[0]);
				MyLogger.setDebugValue(debugValue);

			} catch (NumberFormatException e) {
				System.out.println("Error while parsing debug level value");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
    
    public void buildInput(String[] args) {

		InputStream keywordsPathIS = getClass().getResourceAsStream("userInputs.txt");
		InputStream inputPathIS = getClass().getResourceAsStream("input.txt");
		InputStream synonymPathIS = getClass().getResourceAsStream("synonyms.txt");

        FileProcessor fileProcessorKeywords = new FileProcessor(keywordsPathIS);
        fileProcessorKeywords.fillLists(FileType.KEYWORD);
        FileProcessor fileProcessorInputTxt = new FileProcessor(inputPathIS);   //args[1] is input1.txt
        fileProcessorInputTxt.fillLists(FileType.INPUT);
        FileProcessor fileProcessorSynonyms = new FileProcessor(synonymPathIS);
        fileProcessorSynonyms.fillLists(FileType.SYNONYM);
    }

    public String getStringFromInputStream(InputStream inputStream) {

		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();

		String path;
		try{
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			if ((path = bufferedReader.readLine()) != null) {
				stringBuilder.append(path);
			}
		} catch (IOException ioe) {
			System.out.println("Invalid file path");
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					System.out.println("");
				}
			}
		}
		return stringBuilder.toString();
	}
    
	/**
	 * This method is used to split the keyword
	 *
	 * @param keyword
	 * @return tokens
	 */
    public static String[] tokenizeKeyword(String keyword) {

        String[] tokens = new String[0];
        if(!keyword.equals("") && !keyword.equals(" ")) {
            tokens = keyword.split(" ");
        }
        return tokens;
    }
	/**
	 * This method is used to split the line
	 *
	 * @param line
	 * @return lineTokens
	 */
    public static String[] tokenizeWords(String line) {

        String[] lineTokens = new String[0];
        if (!line.equals("") && !line.equals(" ")) {
            lineTokens = line.split(":| :|: | : ");
        }
        return lineTokens;
    }
}
