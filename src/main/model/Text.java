package model;

import org.json.JSONObject;

// Represents the text field type
public class Text extends Field {

    //EFFECT: creates text class with a name and contents
    public Text(String name, String desc) {
        super(name);
        this.data.add(desc);
    }

    //MODIFIES: this
    //EFFECTS: updates content of text
    @Override
    public void editField(String old, String desc) {
        this.data.remove(0);
        this.data.add(desc);
    }

    //EFFECTS: returns content
    public String getDesc() {
        return this.data.get(0);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("desc", data.get(0));
        return jsonObject;
    }
}
