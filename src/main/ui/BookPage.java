package ui;

import model.*;
import persistence.JsonHandler;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

// Represents page with user's book list and all relevant actions
public class BookPage extends JPanel {
    private static final String JSON_STORE = "./data/booktracker.json";
    private final JsonHandler jsonHandler = new JsonHandler(JSON_STORE);

    private final User currentUser;
    private final List<Book> bookList;
    private final UserList userList;
    private final JPanel mainPanel;
    private final CardLayout layout;

    //MODIFIES: this
    //EFFECTS: creates page GUI with current user
    public BookPage(User u, UserList userList, JPanel panel, CardLayout layout) {
        currentUser = u;
        this.userList = userList;
        bookList = u.getBookList();
        this.mainPanel = panel;
        this.layout = layout;
        createGui();
    }

    //EFFECTS: adds top menu and book list onto window
    public void createGui() {
        JPanel panel = new JPanel(new GridLayout(2,1));
        placeTopRow(panel);
        placeBookList(panel);
        add(panel);
    }

    //EFFECTS: creates and places top menu
    public void placeTopRow(JPanel panel) {
        JPanel topPanel = new JPanel(new GridLayout(1,2));
        JLabel title = new JLabel(currentUser.getUsername() + "'s Book List");
        topPanel.add(title);
        placeButtons(topPanel);
        panel.add(topPanel);
    }

    //EFFECTS: places buttons on menu with action listeners for appropriate buttons
    public void placeButtons(JPanel panel) {
        JPanel row = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Book");
        JButton saveButton = new JButton("Save");
        JButton logoutButton = new JButton("Logout");
        saveButton.addActionListener(e -> saveToJson());
        logoutButton.addActionListener(e -> layout.show(mainPanel, "" + (1)));

        row.add(addButton);
        row.add(saveButton);
        row.add(logoutButton);
        panel.add(row);
    }

    //EFFECTS: places book list onto given panel
    public void placeBookList(JPanel panel) {
        JPanel bookPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(bookPanel);
        bookPanel.setAutoscrolls(true);
        panel.add(scroll);
    }

    //EFFECTS: saves book list to JSON file
    public void saveToJson() {
        try {
            jsonHandler.open();
            jsonHandler.write(userList);
            jsonHandler.close();
            JOptionPane.showMessageDialog(null, "All data saved.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found.");
        }
    }
}
