package genericCheckpointing.util;

import genericCheckpointing.server.SerStrategy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XMLSerialization implements SerStrategy {

    private SerializeTypes serializeTypes = new SerializeTypes();
    private FileProcessor fileProcessor = new FileProcessor();

    @Override
    public void processInput(SerializableObject sObject) {

        Class<?> cls = sObject.getClass();
        Results.resultToFile.append("<DPSerialization>");
        Results.resultToFile.append(System.getProperty("line.separator"));

        Results.resultToFile.append(serializeTypes.getComplexTypeTag(sObject));
        try {
            for (Field field : cls.getDeclaredFields()) {
                String fieldName;
                Method method;
                Object invokeObj;

                if (field.getType() == int.class) {
                    fieldName = AccessorMutator.get.name() + field.getName();
                    method = cls.getMethod(fieldName);
                    invokeObj = method.invoke(sObject);
                    String fieldTag;
                    int integerValue = Integer.parseInt(invokeObj.toString());
                    if (10 <= integerValue) {                                   // Serialize only if the randomized integer value is equal/above 10.
                        fieldTag = serializeTypes.getFieldTag(field, invokeObj.toString(), "int");
                    } else {
                        /*  The value is below 10, so changing the value to be written to 0 as default.
                         *  But the object has the actual random value.
                         */
                        fieldTag = serializeTypes.getFieldTag(field, "0", "int");
                    }
                    Results.resultToFile.append(fieldTag);
                } else if (field.getType() == long.class) {
                    fieldName = AccessorMutator.get.name() + field.getName();
                    method = cls.getMethod(fieldName);
                    invokeObj = method.invoke(sObject);
                    long longValue = Long.parseLong(invokeObj.toString());
                    if (10 <= longValue) {                                   // Serialize only if the randomized long value is equal/above 10.
                        Results.resultToFile.append(serializeTypes.getFieldTag(field, invokeObj.toString(), "long"));
                    } else {
                        Results.resultToFile.append(serializeTypes.getFieldTag(field, "0", "long"));
                    }
                } else if (field.getType() == short.class) {
                    fieldName = AccessorMutator.get.name() + field.getName();
                    method = cls.getMethod(fieldName);
                    invokeObj = method.invoke(sObject);
                    Results.resultToFile.append(serializeTypes.getFieldTag(field, invokeObj.toString(), "short"));
                } else if (field.getType() == boolean.class) {
                    fieldName = AccessorMutator.is.name() + field.getName();
                    method = cls.getMethod(fieldName);
                    invokeObj = method.invoke(sObject);
                    Results.resultToFile.append(serializeTypes.getFieldTag(field, invokeObj.toString(), "boolean"));
                } else if (field.getType() == String.class) {
                    fieldName = AccessorMutator.get.name() + field.getName();
                    method = cls.getMethod(fieldName);
                    invokeObj = method.invoke(sObject);
                    Results.resultToFile.append(serializeTypes.getFieldTag(field, invokeObj.toString(), "string"));
                } else if (field.getType() == char.class) {
                    fieldName = AccessorMutator.get.name() + field.getName();
                    method = cls.getMethod(fieldName);
                    invokeObj = method.invoke(sObject);
                    Results.resultToFile.append(serializeTypes.getFieldTag(field, invokeObj.toString(), "char"));
                } else if (field.getType() == float.class) {
                    fieldName = AccessorMutator.get.name() + field.getName();
                    method = cls.getMethod(fieldName);
                    invokeObj = method.invoke(sObject);
                    Results.resultToFile.append(serializeTypes.getFieldTag(field, invokeObj.toString(), "float"));
                } else if (field.getType() == double.class) {
                    fieldName = AccessorMutator.get.name() + field.getName();
                    method = cls.getMethod(fieldName);
                    invokeObj = method.invoke(sObject);
                    double doubleValue = Double.parseDouble(invokeObj.toString());
                    if (doubleValue >= 10) {
                        Results.resultToFile.append(serializeTypes.getFieldTag(field, invokeObj.toString(), "double"));
                    } else {
                        Results.resultToFile.append(serializeTypes.getFieldTag(field, "0", "double"));
                    }
                }
            }
            Method method = sObject.getClass().getMethod("toString");
            Results.resultToConsole.append(method.invoke(sObject));
            Results.resultToConsole.append(System.getProperty("line.separator"));
        } catch (NoSuchMethodException e) {
            System.out.print("");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Results.resultToFile.append(" </complexType>");
        Results.resultToFile.append(System.getProperty("line.separator"));
        Results.resultToFile.append("</DPSerialization>");
        Results.resultToFile.append(System.getProperty("line.separator"));
        FileProcessor.write(Results.resultToFile.toString(), fileProcessor.getFile());
    }

    @Override
    public SerializableObject processFile() {
        return null;
    }

    @Override
    public String toString() {
        return "XMLSerialization{" +
                "serializeTypes=" + serializeTypes +
                ", fileProcessor=" + fileProcessor +
                '}';
    }
}
