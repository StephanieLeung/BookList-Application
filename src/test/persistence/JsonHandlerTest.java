package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonHandlerTest {

    @Test
    void readNoFileTest() {
        try {
            JsonHandler json = new JsonHandler("./data/readTest.json");
            json.read();
            fail();
        } catch (IOException e) {
            // do nothing
        }
    }

    @Test
    void readEmptyFileTest() {
        try {
            JsonHandler json = new JsonHandler("./data/readEmptyTest.json");
            UserList userList = json.read();
            assertEquals(0, userList.getSize());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void readNormalFileTest() {
        try {
            JsonHandler json = new JsonHandler("./data/readNormalUserList.json");
            UserList userList = json.read();

            assertEquals(1, userList.getSize());
            User user = userList.findUser("username", "password");
            assertEquals("username", user.getUsername());
            assertEquals("email", user.getEmail());
            Book book = user.getBookList().get(0);
            assertEquals("title", book.getTitle());
            assertEquals(LocalDate.now(), book.getDateCreated());
            List<Field> fields = book.getFields();
            assertEquals(2, fields.size());
            assertEquals("author", fields.get(0).getName());
            assertEquals("kim", fields.get(0).getData().get(0));
            assertEquals("genre", fields.get(1).getName());
            assertEquals("horror", fields.get(1).getData().get(0));
            assertEquals(1, fields.get(1).getData().size());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void writeInvalidFilenameTest() {
        try {
            JsonHandler json = new JsonHandler("./data/writeTest\0.json");
            json.open();
            json.write(new UserList());
            json.close();
            fail();
        } catch (FileNotFoundException e) {
            // do nothing
        }
    }

    @Test
    void writeEmptyFileTest() {
        try {
            JsonHandler json = new JsonHandler("./data/writeEmptyTest.json");
            json.open();
            json.write(new UserList());
            json.close();

            UserList userList = json.read();
            assertEquals(0, userList.getSize());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void writeNormalFileTest() {
        try {
            UserList userList = new UserList();
            User user = new User("username", "email", "password");
            List<Book> bookList = user.getBookList();
            Book book = new Book("title");
            book.addField(new Text("author", "kim"));
            Tags tags = new Tags("genre");
            tags.addTag("horror");
            book.addField(tags);
            bookList.add(book);
            userList.addUser(user);

            JsonHandler json = new JsonHandler("./data/writeNormalUserList.json");
            json.open();
            json.write(userList);
            json.close();

            UserList checkTest = json.read();
            assertEquals(userList.getSize(), checkTest.getSize());
            User checkUser = checkTest.findUser("username", "password");
            assertEquals(checkUser.getUsername(), user.getUsername());
            assertEquals(checkUser.getEmail(), user.getEmail());
            Book checkBook = checkUser.getBookList().get(0);
            assertEquals(checkBook.getTitle(), book.getTitle());
            assertEquals(checkBook.getDateCreated(), book.getDateCreated());
            List<Field> fields = checkBook.getFields();
            assertEquals("author", fields.get(0).getName());
            assertEquals("kim", fields.get(0).getData().get(0));
            assertEquals("genre", fields.get(1).getName());
            assertEquals("horror", fields.get(1).getData().get(0));
            assertEquals(2, fields.size());
        } catch (IOException e) {
            fail();
        }
    }
}