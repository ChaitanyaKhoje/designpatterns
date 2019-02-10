package genericCheckpointing.util;

public enum FieldName {

    INT("xsd:int"),
    LONG("xsd:long"),
    BOOL("xsd:boolean"),
    DOUBLE("xsd:double"),
    CHAR("xsd:char"),
    STRING("xsd:string"),
    FLOAT("xsd:float"),
    SHORT("xsd:short");

    private String value;

    private FieldName(String value) {
        this.value = value;
    }

    public String toString() {

        return this.value;
    }

}
