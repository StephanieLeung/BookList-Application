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
        assertEquals(0, tags.getTags().size());
        tags.addTag("comedy");
        assertEquals(1, tags.getTags().size());
    }

    @Test
    void editTagsOneTagTest() {
        tags.addTag("comedy");
        tags.editTag("comedy", "horror");
        assertEquals("horror", tags.getTags().get(0));
    }

    @Test
    void editTagsMultipleTagsTest() {
        tags.addTag("horror");
        tags.addTag("romance");
        tags.addTag("fantasy");

        tags.editTag("horror", "comedy");
        assertEquals("comedy", tags.getTags().get(0));

        tags.editTag("romance", "manga");
        assertEquals("manga", tags.getTags().get(1));
    }

    @Test
    void removeTagsOneTagTest() {
        tags.addTag("romance");
        assertEquals(1, tags.getTags().size());
        tags.removeTag("romance");
        assertEquals(0, tags.getTags().size());
    }

    @Test
    void removeTagMultipleTagsTest() {
        tags.addTag("romcom");
        tags.addTag("science");
        tags.addTag("horror");

        assertEquals(3, tags.getTags().size());
        tags.removeTag("science");
        assertEquals(2, tags.getTags().size());
    }
}
