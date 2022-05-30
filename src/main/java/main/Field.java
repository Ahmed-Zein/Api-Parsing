package main;

public class Field {
    private String name;
    private String type;
    private String allowedVals;
    private String mandatory;

    // constructors
    public Field(String name, String type, String allowedVals, String mandatory) {
        this.name = name;
        this.type = type;
        this.allowedVals = allowedVals;
        this.mandatory = mandatory;
    }

    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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
}
