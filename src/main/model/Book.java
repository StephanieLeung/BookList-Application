package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Book {
    private String title;
    private final LocalDate dateCreated;
    private ArrayList<Field> fields;


    //EFFECTS: creates a book with given title and date created
    public Book(String title) {
        this.title = title;
        dateCreated = LocalDate.now();
    }

    //EFFECT: returns title of book
    public String getTitle() {
        return title;
    }

    //EFFECT: returns date created
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    //MODIFIES: this
    //EFFECT: adds field to the book
    public void addField(Field field) {
        this.fields.add(field);
    }

    //REQUIRES: fields contains a field with given name
    //EFFECTS: finds field by name
    public Field findField(String name) {
        for (Field f : fields) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    //REQUIRES: this.fields.contains(field)
    //MODIFIES: this
    //EFFECT: remove field from book
    public void removeField(Field field) {
        this.fields.remove(field);
    }
}
