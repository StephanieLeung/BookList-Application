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
    void editTagsTest() {
        tags.addTag("comedy");
        tags.editTag("comedy", "horror");
        assertEquals("horror", tags.getTags().get(0));
    }

    @Test
    void removeTagsTest() {
        tags.addTag("romance");
        assertEquals(1, tags.getTags().size());
        tags.removeTag("romance");
        assertEquals(0, tags.getTags().size());
    }
}
