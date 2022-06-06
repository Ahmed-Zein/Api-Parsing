package main;

public class Field {
    private String FieldName;
    private String type;
    private String allowedVals;
    private String mandatory;

    
    // constructors
    public Field(String FieldName, String type, String allowedVals, String mandatory) {
        this.FieldName = FieldName;
        this.type = type;
        this.allowedVals = allowedVals;
        this.mandatory = mandatory;
    }

    public Field(String FieldName) {
        this.FieldName = FieldName;
    }
    
    public String getFieldName() {
        return this.FieldName;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getAllowedVals() {
        return this.allowedVals;
    }
    
    public String getMandatory() {
        return this.mandatory;
    }
    @Override
    public String toString() {
        return "Field [allowedVals=" + allowedVals + ", mandatory=" + mandatory + ", FieldName=" + FieldName + ", type=" + type
                + "]";
    }
}
