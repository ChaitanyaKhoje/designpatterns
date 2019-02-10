package genericCheckpointing.util;

import java.lang.reflect.Field;

public class SerializeTypes {

    /**
     * Builds the complexType tag line to be written to the file.
     * @param sObject
     * @return tag
     */
    public String getComplexTypeTag(Object sObject) {

        //  <complexType xsi:type="genericCheckpointing.util.MyAllTypesFirst">
        String tag = " <complexType xsi:type=" + "\"" + sObject.getClass().getName() + "\">\n";
        return tag;
    }

    /**
     * Builds the data member line to be written to the file.
     * @param field
     * @param value
     * @param type
     * @return tag
     */
    public String getFieldTag(Field field, String value, String type) {

        //  <myInt xsi:type="xsd:int">314</myInt>
        String tag = "  <" + field.getName() + " xsi:type=" + "\"xsd:" + type + "\">" + value + "</" + field.getName() + ">\n";
        return tag;
    }

    @Override
    public String toString() {
        return "SerializeTypes{}";
    }
}
