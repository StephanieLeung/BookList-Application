package model;

public class Text extends Field {
    private String desc;

    //EFFECT: creates text class with a name and contents
    public Text(String name, String desc) {
        super(name);
        this.desc = desc;
    }

    //MODIFIES: this
    //EFFECTS: updates content of text
    public void updateText(String desc) {
        this.desc = desc;
    }

    //EFFECTS: returns content
    public String getDesc() {
        return desc;
    }
}
