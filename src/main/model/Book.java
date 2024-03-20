package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;

// Represents a book with title and fields
public class Book implements Writable {
    private String title;
    private final LocalDate dateCreated;
    private ArrayList<Field> fields;


    //EFFECTS: creates a book with given title and date created
    public Book(String title) {
        this.title = title;
        dateCreated = LocalDate.now();
        fields = new ArrayList<>();
    }

    public Book(String title, String date) {
        this.title = title;
        dateCreated = LocalDate.parse(date);
        fields = new ArrayList<>();
    }

    //EFFECT: returns title of book
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    //EFFECTS: finds and returns field by name, null otherwise
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

    //EFFECT: returns list of fields
    public ArrayList<Field> getFields() {
        return fields;
    }

    //EFFECTS: converts Book object into JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("date-created", dateCreated.toString());

        JSONArray fieldsJson = new JSONArray();
        for (Field f : fields) {
            fieldsJson.put(f.toJson());
        }
        json.put("fields", fieldsJson);

        return json;
    }
}
