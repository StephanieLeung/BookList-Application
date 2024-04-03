package persistence;

import model.Book;
import model.Text;
import model.Tags;
import model.User;
import model.UserList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// Represents a handler to do all Json related actions
// CREDIT: this class is heavily modelled off of the JsonSerializationDemo provided as reference
public class JsonHandler {
    private String data;
    private static final int TAB = 4;
    private PrintWriter writer;

    //EFFECTS: creates a JsonHandler with file name
    public JsonHandler(String data) {
        this.data = data;
    }

    //EFFECTS: reads file from filename and returns it as a UserList
    //         throws IOException if error occurs when reading from file
    public UserList read() throws IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get(data)));
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUserList(jsonObject);
    }

    //MODIFIES: this
    //EFFECTS: opens writer; throws FileNotFoundException if file cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(data));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of userList to file
    public void write(UserList userList) {
        JSONObject jsonObject = userList.toJson();
        writer.print(jsonObject.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    //EFFECTS: parses user list from jsonObj
    public UserList parseUserList(JSONObject jsonObj) {
        UserList userList = new UserList();
        JSONArray users = jsonObj.getJSONArray("users");
        for (Object o : users) {
            JSONObject nextUser = (JSONObject) o;
            addUser(userList, nextUser);
        }
        return userList;
    }

    //MODIFIES: userList
    //EFFECTS: parses User from jsonObject and adds it to userList
    public void addUser(UserList userList, JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        User user = new User(username, email, password);
        JSONArray bookList = jsonObject.getJSONArray("books");
        for (Object json : bookList) {
            JSONObject nextBook = (JSONObject) json;
            addBook(user, nextBook);
        }
        List<User> users = userList.getUserList();
        users.add(user);
    }

    //MODIFIES: user
    //EFFECTS: parses Book from jsonObject and adds it to user booklist
    public void addBook(User user, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String dateCreated = jsonObject.getString("date-created");
        Book book = new Book(title, dateCreated);
        JSONArray fields = jsonObject.getJSONArray("fields");
        for (Object json : fields) {
            JSONObject nextField = (JSONObject) json;
            addField(book, nextField);
        }
        List<Book> userBooks = user.getBookList();
        userBooks.add(book);
    }

    //MODIFIES: book
    //EFFECTS: parses Field from jsonObject and adds it to book
    public void addField(Book book, JSONObject jsonObject) throws JSONException {
        String name = jsonObject.getString("name");
        try {
            String desc = jsonObject.getString("desc");
            Text field = new Text(name, desc);
            book.addField(field);
        } catch (JSONException e) {
            JSONArray tagsData = jsonObject.getJSONArray("tags");
            Tags field = new Tags(name);
            List<Object> tags = tagsData.toList();
            for (Object o : tags) {
                field.addTag(o.toString());
            }
            book.addField(field);
        }
    }
}
