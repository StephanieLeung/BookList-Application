package model;

import java.util.ArrayList;

public abstract class Field {
    protected String name;

    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
