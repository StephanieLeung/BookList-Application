package ui;

import model.*;
import persistence.JsonHandler;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

// Represents page with user's book list and all relevant actions
// CREDIT: Used these links to build this class:
//            - https://stackoverflow.com/questions/36322193/how-to-display-images-using-gridlayout-on-jpanel
//            - https://stackoverflow.com/questions/4585867/transparent-jbutton
//            - https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/
public class BookPage extends JPanel {
    private static final String JSON_STORE = "./data/booktracker.json";

    private final JsonHandler jsonHandler;
    private final User currentUser;
    private final List<Book> bookList;
    private final UserList userList;
    private final JPanel mainPanel;
    private final CardLayout layout;

    //MODIFIES: this
    //EFFECTS: creates page GUI with current user
    public BookPage(User u, UserList userList, JPanel panel, CardLayout layout) {
        jsonHandler = new JsonHandler(JSON_STORE);
        currentUser = u;
        this.userList = userList;
        bookList = u.getBookList();
        this.mainPanel = panel;
        this.layout = layout;
        createGui();
    }

    //EFFECTS: adds top menu and book list onto window
    public void createGui() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        placeTopRow(panel);
        placeBookList(panel);
        add(panel);
    }

    //EFFECTS: creates and places top menu
    public void placeTopRow(JPanel panel) {
        JPanel topPanel = new JPanel(new FlowLayout());
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
        addButton.addActionListener(e -> addBook());
        saveButton.addActionListener(e -> saveToJson());
        logoutButton.addActionListener(e -> layout.show(mainPanel, "" + (1)));

        row.add(addButton);
        row.add(saveButton);
        row.add(logoutButton);
        panel.add(row);
    }

    //EFFECTS: places book list onto given panel
    public void placeBookList(JPanel panel) {
        int rows = (int) Math.ceil((double) bookList.size() / 3);
        JPanel bookPanel = new JPanel(new GridLayout(rows, 3));
        for (Book b : bookList) {
            placeBook(b, bookPanel);
        }
        JScrollPane scroll = new JScrollPane(bookPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.setBounds(bookPanel.getX(), bookPanel.getY(), 300, 100);
        panel.setPreferredSize(new Dimension(500, 300));

        panel.add(scroll);
    }

    //EFFECTS: places given book onto given panel with cover, title, and delete button
    public void placeBook(Book b, JPanel panel) {
        JPanel bookPanel = new JPanel(new GridLayout(2,1));
        ImageIcon cover = new ImageIcon(new ImageIcon("./data/book_cover_stock.jpg")
                .getImage().getScaledInstance(50, 80, Image.SCALE_DEFAULT));
        JPanel titleButtonPanel = new JPanel();
        JLabel titleLabel = new JLabel(b.getTitle(), SwingConstants.CENTER);
        ImageIcon trashIcon = new ImageIcon(new ImageIcon("./data/trash.png")
                .getImage().getScaledInstance(15,20, Image.SCALE_DEFAULT));
        JButton deleteButton = new JButton(trashIcon);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setSize(20,10);
        deleteButton.addActionListener(e -> {
            bookList.remove(b);
            updatePanel();
        });
        titleButtonPanel.add(titleLabel);
        titleButtonPanel.add(deleteButton);

        bookPanel.add(new JLabel(cover));
        bookPanel.add(titleButtonPanel);
        panel.add(bookPanel);
    }

    //EFFECTS: adds book to user's list
    public void addBook() {
        String title = JOptionPane.showInputDialog("Enter book title: ");
        bookList.add(new Book(title));
        updatePanel();
    }

    //EFFECTS: updates user page
    public void updatePanel() {
        mainPanel.remove(this);
        mainPanel.add(new BookPage(currentUser, userList, mainPanel, layout), "3");
        layout.show(mainPanel, "" + (3));
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
