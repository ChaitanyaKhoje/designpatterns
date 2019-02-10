package genericCheckpointing.driver;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.Comparator;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Driver {

    public static void main(String[] args) {

        if (validate(args)) {
            String mode = args[0];
            int NUM_OF_OBJECTS = Integer.parseInt(args[1]);
            String file = args[2];
            int mismatchedObjects = 0;
            FileProcessor fileProcessor = new FileProcessor();

            ProxyCreator proxyCreator = new ProxyCreator();
            // create an instance of StoreRestoreHandler (which implements
            // the InvocationHandler
            StoreRestoreHandler storeRestoreHandler = new StoreRestoreHandler();

            // create a proxy
            StoreRestoreI cpointRef = (StoreRestoreI) proxyCreator.createProxy(
                    new Class[]{StoreI.class, RestoreI.class},
                    storeRestoreHandler);

            MyAllTypesFirst myFirst;
            MyAllTypesSecond mySecond;
            Random randomize = new Random();
            int randomInteger = randomize.nextInt((30 - 1) + 1) + 1;
            long randLong = randomInteger;
            double randDouble = randomInteger;
            float randFloat = randomInteger;

            List<SerializableObject> serializableObjects = new ArrayList<SerializableObject>();
            List<SerializableObject> deserializedObjects = new ArrayList<SerializableObject>();
            fileProcessor.openFile(file);

            switch (mode) {
                case "deser":
                    //System.out.println("Deserialization mode\n");
                    for (int j = 0; j < 2 * NUM_OF_OBJECTS; j++) {
                        SerializableObject myRecordRet = ((RestoreI) cpointRef).readObj("XML");
                        if (myRecordRet != null) deserializedObjects.add(myRecordRet);
                    }
                    System.out.println("");
                    System.out.println(Results.resultToConsole);
                    break;
                case "serdeser":
                    Results.resultToConsole.append("Objects with random data: ");
                    Results.resultToConsole.append(System.getProperty("line.separator"));
                    for (int i = 0; i < NUM_OF_OBJECTS; i++) {
                        myFirst = new MyAllTypesFirst(randomInteger, randLong, "abd" + i, randomize.nextBoolean(), randomInteger, randLong);
                        mySecond = new MyAllTypesSecond(randDouble, randDouble, randFloat, (short) (randomInteger & Short.MAX_VALUE), (char) (randomize.nextInt(26) + 'a'));

                        serializableObjects.add(myFirst);
                        serializableObjects.add(mySecond);
                        ((StoreI) cpointRef).writeObj(myFirst, randomize.nextInt(), "XML");
                        ((StoreI) cpointRef).writeObj(mySecond, randomize.nextInt(), "XML");
                    }
                    Results.resultToConsole.append(System.getProperty("line.separator"));

                    Results.resultToConsole.append("Deserialized objects: ");
                    Results.resultToConsole.append(System.getProperty("line.separator"));
                    for (int j = 0; j < 2 * NUM_OF_OBJECTS; j++) {
                        SerializableObject myRecordRet = ((RestoreI) cpointRef).readObj("XML");
                        if (myRecordRet != null) deserializedObjects.add(myRecordRet);
                    }
                    Results.resultToConsole.append(System.getProperty("line.separator"));

                    Comparator comparator = new Comparator();
                    mismatchedObjects = comparator.getMismatchedCount(serializableObjects, deserializedObjects);
                    System.out.println("");
                    System.out.println(Results.resultToConsole);
                    System.out.println("Mismatched objects: " + mismatchedObjects);
                    break;
            }
        }
    }

    /**
     * This method is used to validate the command line arguments and handle any errors related to it.
     * @param argsIn Program arguments are passed to validate method for error handling purposes.
     * @return files;String array of input and delete file paths.
     */
    private static boolean validate(String[] argsIn) {

        try {
            if (argsIn.length != 3 || argsIn[0].equals("${arg0}") || argsIn[1].equals("${arg1}") || argsIn[2].equals("${arg2}")) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException iae) {
            System.err.println("Arguments passed were either less/more than expected. The program takes 3 arguments; \n");
            System.out.println("[mode] eg. serdeser/deser");
            System.out.println("[NUM_OF_OBJECTS] eg. 10");
            System.out.println("[checkpoint.txt] file-path.");
            return false;
        } finally {
        }
        return true;
    }
}