package main;

import java.util.ArrayList;

public class BigObject {
    private String name;
    private String io;
    ArrayList<Field> fields = new ArrayList<Field>();

    // constructors
    public BigObject(String name, String io) {
        this.name = name;
        this.io = io;
    }

    public String getName() {
        return name;
    }

    public String getIo() {
        return io;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public Field getField(int i) {
        return fields.get(i);
    }

    public void addField(Field f) {
        fields.add(f);

    }
}
