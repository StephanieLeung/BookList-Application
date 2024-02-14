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
    public void addTag(String name) {
        tags.add(name);
    }

    //REQUIRES: tags.contains(label)
    //MODIFIES: this
    //EFFECTS: updates label in tags with the newLabel
    public void editTag(String label, String newLabel) {
        for (int i = 0; i <= tags.size(); i++) {
            if (tags.get(i).equals(label)) {
                tags.remove(i);
                tags.add(i, newLabel);
                break;
            }
        }
    }

    //REQUIRES: tags.contains(name)
    //MODIFIES: this
    //EFFECT: removes tag from list of tags
    public void removeTag(String name) {
        tags.remove(name);
    }

    //EFFECTS: returns list of tags
    public ArrayList<String> getTags() {
        return tags;
    }
}
