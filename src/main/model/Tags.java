package model;

import java.util.ArrayList;

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
    private void addTag(String name) {
        tags.add(name);
    }

    //REQUIRES: tags.contains(name)
    //MODIFIES: this
    //EFFECTS: edits tag with given name
    private void editTag(int index, String name) {
        tags.remove(index);
        tags.add(index, name);
    }

    //REQUIRES: tags.contains(name)
    //MODIFIES: this
    //EFFECT: removes tag from list of tags
    private void removeTag(String name) {
        tags.remove(name);
    }
}
