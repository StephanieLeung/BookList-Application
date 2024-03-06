package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a user with a book list
public class User implements Writable {
    private String username;
    private String email;
    private String password;
    private ArrayList<Book> bookList;

    //EFFECTS: creates a user with username, email, and password
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.bookList = new ArrayList<>();
    }

    //EFFECTS: returns username
    public String getUsername() {
        return username;
    }

    //EFFECTS: returns email
    public String getEmail() {
        return email;
    }

    //MODIFIES: this
    //EFFECTS: sets username to given
    public void setUsername(String username) {
        this.username = username;
    }

    //MODIFIES: this
    //EFFECTS: sets password to given
    public void setPassword(String password) {
        this.password = password;
    }

    //EFFECTS: returns user's book list
    public ArrayList<Book> getBookList()  {
        return bookList;
    }

    //EFFECTS: returns true if login credentials match user; false otherwise
    public boolean checkLogin(String id, String password) {
        if (id.equals(username) || id.equals(email)) {
            return password.equals(this.password);
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("email", email);
        json.put("password", password);

        JSONArray booksJson = new JSONArray();
        for (Book b : bookList) {
            booksJson.put(b.toJson());
        }
        json.put("books", booksJson);

        return json;
    }
}