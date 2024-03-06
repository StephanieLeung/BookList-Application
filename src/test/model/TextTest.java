package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextTest {
    private Text text;

    @BeforeEach
    void setup() {
        text = new Text("author", "james");
    }

    @Test
    void updateTextTest() {
        text.setName("Author");
        assertEquals("Author", text.getName());

        text.editField("james", "charles");
        assertEquals("charles", text.getDesc());
    }
}
