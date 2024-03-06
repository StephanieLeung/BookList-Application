package model;

import persistence.Writable;

import java.util.ArrayList;

// Represents a field for a book
public abstract class Field implements Writable {
    protected String name;
    protected ArrayList<String> data;

    //EFFECTS: sets name to given
    public Field(String name) {
        this.name = name;
        data = new ArrayList<>();
    }

    //EFFECTS: returns name
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: sets name to given
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getData() {
        return data;
    }

    //MODIFIES: this
    //EFFECTS: edits field
    public abstract void editField(String oldVal, String updateVal);



}
