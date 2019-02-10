package genericCheckpointing.util;

import genericCheckpointing.server.SerStrategy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XMLDeserialization implements SerStrategy {

    private DeserializeTypes deserializeTypes = new DeserializeTypes();
    private FileProcessor fileProcessor = new FileProcessor();

    @Override
    public void processInput(SerializableObject sObject) {

    }

    @Override
    public SerializableObject processFile() {

        SerializableObject deserializedObject = null;
        String fileLine = "";
        String className;
        Method method;
        Object classObject = null;
        String variableName = "";

        try {
            while (fileProcessor.hasNextLine()) {
                fileLine = fileProcessor.getNextLine();

                /** Check if we come across a line which has complexType as a word. This means we make a new instance of MyAllTypes.
                 *  Fetch that line and identify which class's object to create.
                 */
                if (fileLine.contains(Tag.complexType.toString()) && !fileLine.contains("</complexType>")) {
                    className = deserializeTypes.getClassName(fileLine);
                    Class<?> cls = Class.forName(className);
                    classObject = cls.newInstance();
                    Results.resultToConsole.append(System.getProperty("line.separator"));
                }

                /** Variable name is fetched from the file line to check if there's a setter method for it.
                 *  If there exists a method for it in the MyAllTypes class then invoke it else throw exception.
                 */
                variableName = deserializeTypes.getVariableName(fileLine);

                if (fileLine.matches("(.*)" + FieldName.BOOL + "(.*)")) {
                    Class[] boolParameter = new Class[1];
                    boolParameter[0] = Boolean.TYPE;
                    method = classObject.getClass().getMethod(AccessorMutator.set + variableName, boolParameter);
                    String value = deserializeTypes.getValue(fileLine);
                    method.invoke(classObject, Boolean.parseBoolean(value));
                } else if (fileLine.matches("(.*)" + FieldName.INT + "(.*)")) {
                    Class[] integerParameter = new Class[1];
                    integerParameter[0] = Integer.TYPE;
                    method = classObject.getClass().getMethod(AccessorMutator.set + variableName, integerParameter);
                    String value = deserializeTypes.getValue(fileLine);
                    if (Integer.parseInt(value) > 10) method.invoke(classObject, Integer.parseInt(value));
                }  else if (fileLine.matches("(.*)" + FieldName.DOUBLE + "(.*)")) {
                    Class[] doubleParameter = new Class[1];
                    doubleParameter[0] = Double.TYPE;
                    method = classObject.getClass().getMethod(AccessorMutator.set + variableName, doubleParameter);
                    String value = deserializeTypes.getValue(fileLine);
                    if (Double.parseDouble(value) > 10) method.invoke(classObject, Double.parseDouble(value));
                }  else if (fileLine.matches("(.*)" + FieldName.LONG + "(.*)")) {
                    Class[] longParameter = new Class[1];
                    longParameter[0] = Long.TYPE;
                    method = classObject.getClass().getMethod(AccessorMutator.set + variableName, longParameter);
                    String value = deserializeTypes.getValue(fileLine);
                    if (Long.parseLong(value) > 10) method.invoke(classObject, Long.parseLong(value));
                } else if (fileLine.matches("(.*)" + FieldName.SHORT + "(.*)")) {
                    Class[] shortParameter = new Class[1];
                    shortParameter[0] = Short.TYPE;
                    method = classObject.getClass().getMethod(AccessorMutator.set + variableName, shortParameter);
                    String value = deserializeTypes.getValue(fileLine);
                    method.invoke(classObject, Short.parseShort(value));
                } else if (fileLine.matches("(.*)" + FieldName.STRING + "(.*)")) {
                    Class[] stringParameter = new Class[1];
                    stringParameter[0] = String.class;
                    method = classObject.getClass().getMethod(AccessorMutator.set + variableName, stringParameter);
                    String value = deserializeTypes.getValue(fileLine);
                    method.invoke(classObject, value);
                } else if (fileLine.matches("(.*)" + FieldName.FLOAT + "(.*)")) {
                    Class[] floatParameter = new Class[1];
                    floatParameter[0] = Float.TYPE;
                    method = classObject.getClass().getMethod(AccessorMutator.set + variableName, floatParameter);
                    String value = deserializeTypes.getValue(fileLine);
                    method.invoke(classObject, Float.parseFloat(value));
                } else if (fileLine.matches("(.*)" + FieldName.CHAR + "(.*)")) {
                    Class[] charParameter = new Class[1];
                    charParameter[0] = char.class;
                    method = classObject.getClass().getMethod(AccessorMutator.set + variableName, charParameter);
                    String value = deserializeTypes.getValue(fileLine);
                    method.invoke(classObject, value.charAt(0));
                }
                if (fileLine.matches("</DPSerialization>")) {
                    Method printMethod = classObject.getClass().getMethod("toString");
                    Results.resultToConsole.append(printMethod.invoke(classObject));
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("Data member with this name does not exist: " + variableName + " in " + classObject.getClass().getName());
            while(fileProcessor.hasNextLine()) {
                if (fileProcessor.getNextLine().contains(Tag.DPSerialization.toString())) {
                    classObject = null;
                    break;
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        deserializedObject = (SerializableObject) classObject;
        return deserializedObject;
    }

    @Override
    public String toString() {
        return "XMLDeserialization{" +
                "deserializeTypes=" + deserializeTypes +
                ", fileProcessor=" + fileProcessor +
                '}';
    }
}
