package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Represents GUI version of BookTracker
public class BookTrackerUi extends JFrame {
    private User currentUser;
    private UserList userList;
    private List<Book> bookList;
    private JPanel panel;

    //MODIFIES: this
    //EFFECTS: creates GUI interface and starts with login screen
    public BookTrackerUi() {
        userList = new UserList();
        createGui();
        setSize(600,400);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds all pages to GUI
    public void createGui() {
        CardLayout layout = new CardLayout();
        panel = new JPanel(layout);
        setTitle("BookTracker");
        panel.add(new LoginForm(userList, panel, layout), "1");
        panel.add(new RegisterForm(userList, panel, layout), "2");

        add(panel);

    }

    //EFFECTS: runs the program
    public static void main(String[] args) {
        new BookTrackerUi();
    }
}
