package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents the tags field type
public class Tags extends Field {
    private ArrayList<String> tags;

    //EFFECTS: creates an empty list of tags
    public Tags(String name) {
        super(name);
        this.tags = new ArrayList<>();
    }

    //REQUIRES: !tags.contain(name)
    //MODIFIES: this
    //EFFECT: add tag to list of tags
    public void addTag(String name) {
        tags.add(name);
    }

    //REQUIRES: tags.contains(label)
    //MODIFIES: this
    //EFFECTS: updates label in tags with the newLabel
    @Override
    public void editField(String label, String newLabel) {
        int index = tags.indexOf(label);
        tags.remove(index);
        tags.add(index, newLabel);
    }

    //REQUIRES: tags.contains(name)
    //MODIFIES: this
    //EFFECT: removes tag from list of tags
    public void removeTag(String name) {
        tags.remove(name);
    }

    @Override
    //EFFECTS: returns list of tags
    public ArrayList<String> getData() {
        return tags;
    }

    @Override
    //EFFECTS: returns Tags as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tags", new JSONArray(tags));
        return json;
    }
}
