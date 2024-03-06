package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private Book book;

    @BeforeEach
    void setup() {
        book = new Book("book");
    }

    @Test
    void setTitleTest() {
        assertEquals("book", book.getTitle());
        book.setTitle("new book");
        assertEquals("new book", book.getTitle());
    }

    @Test
    void getDateCreatedTest() {
        assertEquals(LocalDate.now(), book.getDateCreated());
    }

    @Test
    void addFieldTest() {
        assertEquals(0, book.getFields().size());
        book.addField(new Text("author", "james"));
        assertEquals(1, book.getFields().size());
    }

    @Test
    void findFieldTestNoFields() {
        assertNull(book.findField("author"));
        assertNull(book.findField("anytng"));
    }

    @Test
    void findFieldTest() {
        Field author = new Text("author", "charlie");
        book.addField(author);
        assertEquals(author, book.findField("author"));
        assertNull(book.findField("anytng"));
    }

    @Test
    void removeFieldTest() {
        book.addField(new Tags("genres"));
        assertEquals(1, book.getFields().size());
        book.removeField(book.findField("genres"));
        assertEquals(0, book.getFields().size());
    }
}
