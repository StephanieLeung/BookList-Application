package ui;

import model.*;
import persistence.JsonHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

// Represents GUI version of BookTracker
public class BookTrackerUi extends JFrame {
    private static final String JSON_STORE = "./data/booktracker.json";

    private UserList userList;
    private JPanel panel;
    private JsonHandler jsonHandler;


    //MODIFIES: this
    //EFFECTS: creates GUI interface and starts with login screen
    public BookTrackerUi() {
        jsonHandler = new JsonHandler(JSON_STORE);
        userList = handleLoad();
        createGui();
        setSize(600,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    //EFFECTS: checks if there is save data and loads save if user chooses to
    public UserList handleLoad() {
        try {
            UserList tempUserList = jsonHandler.read();
            int choice = JOptionPane.showConfirmDialog(null,
                    "Save data found. Would you like to save?",
                    "BookTracker",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                return tempUserList;
            } else {
                return new UserList();
            }
        } catch (IOException e) {
            return new UserList();
        }
    }

    //EFFECTS: runs the program
    public static void main(String[] args) {
        new BookTrackerUi();
    }
}
