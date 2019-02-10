package genericCheckpointing.util;

public class DeserializeTypes {

    public String getClassName(String fileLine) {

        String className = "";
        String complexTagLine = fileLine;
        String delimiter = "\"";

        String[] tokens = complexTagLine.split(delimiter);
        for (String token : tokens) {
            if (token.startsWith("genericCheckpointing")) {
                className = token;
            }
        }
        return className;
    }

    public String getValue(String fileLine) {

        String value = "";
        // <myDoubleT xsi:type="xsd:double">0.7021802150875418</myDoubleT
        String[] tokens = fileLine.split("\">|</");

        for (String token: tokens) {
            if ((token.contains("myChar")) || token.contains("myString") || token.contains("myBool")) {
                value = tokens[1];
            }
            if (Character.isDigit(token.charAt(0))) {
                value = token;
            }
        }
        return value;
    }

    public String getVariableName(String fileLine) {

        String variableName = "";
        if (!fileLine.contains(Tag.complexType.toString()) && !fileLine.contains(Tag.DPSerialization.toString())) {
            String[] tokens = fileLine.split("</|>");
            variableName = tokens[tokens.length - 1].trim();
        }
        return variableName;
    }

    @Override
    public String toString() {
        return "DeserializeTypes{}";
    }
}
