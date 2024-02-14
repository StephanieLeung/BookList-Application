package model;

import java.util.ArrayList;

public class User {
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
}