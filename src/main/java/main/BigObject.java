package main;

import java.util.ArrayList;

public class BigObject {
    private String name;
    private String io;
    private ArrayList<Field> fields = new ArrayList<Field>();

    // constructors
    public BigObject() {

    }

    public BigObject(String name, String io) {
        this.name = name;
        this.io = io;
    }

    // methods
    public String getName() {
        return name;
    }

    public String getIo() {
        return io;
    }

    /**
     * @return array of the field and obj contained in this object
     */
    public ArrayList<Field> getFields() {
        return fields;
    }

    /**
     * @return array of the field or obj at indx i
     */
    public Field getField(int indx) {
        return fields.get(indx);
    }

    public void addField(Field f) {
        fields.add(f);

    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIo(String io) {
        this.io = io;
    }
}
