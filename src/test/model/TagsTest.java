package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TagsTest {
    private Tags tags;

    @BeforeEach
    void setup() {
        tags = new Tags("genres");
    }

    @Test
    void addTagsTest() {
        assertEquals(0, tags.getData().size());
        tags.addTag("comedy");
        assertEquals(1, tags.getData().size());
    }

    @Test
    void editTagsOneTagTest() {
        tags.addTag("comedy");
        tags.editField("comedy", "horror");
        assertEquals("horror", tags.getData().get(0));
    }

    @Test
    void editTagsMultipleTagsTest() {
        tags.addTag("horror");
        tags.addTag("romance");
        tags.addTag("fantasy");

        tags.editField("horror", "comedy");
        assertEquals("comedy", tags.getData().get(0));

        tags.editField("romance", "manga");
        assertEquals("manga", tags.getData().get(1));
    }

    @Test
    void removeTagsOneTagTest() {
        tags.addTag("romance");
        assertEquals(1, tags.getData().size());
        tags.removeTag("romance");
        assertEquals(0, tags.getData().size());
    }

    @Test
    void removeTagMultipleTagsTest() {
        tags.addTag("romcom");
        tags.addTag("science");
        tags.addTag("horror");

        assertEquals(3, tags.getData().size());
        tags.removeTag("science");
        assertEquals(2, tags.getData().size());
    }
}
