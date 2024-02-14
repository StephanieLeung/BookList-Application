package model;

import java.util.ArrayList;

public abstract class Field {
    protected String name;

    //EFFECT: sets name to given
    public Field(String name) {
        this.name = name;
    }

    //EFFECT: returns name
    public String getName() {
        return name;
    }

    //EFFECT: sets name to given
    public void setName(String name) {
        this.name = name;
    }

}
