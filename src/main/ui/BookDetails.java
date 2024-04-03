package ui;

import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents JFrame that shows book cover, title and author
// CREDIT: Uses other mentioned links as well as
//             - https://stackoverflow.com/questions/5328405/jpanel-padding-in-java
public class BookDetails extends JFrame {
    private JPanel panel;
    private Book book;
    private ImageIcon cover;
    private BookPage controller;

    //MODIFIES: this
    //EFFECTS: initializes all values and creates GUI for the JFrame
    public BookDetails(Book book, ImageIcon cover, BookPage page) {
        this.panel = new JPanel();
        this.book = book;
        this.cover = cover;
        controller = page;
        BoxLayout layout = new BoxLayout(this.panel, BoxLayout.X_AXIS);
        this.panel.setLayout(layout);
        createGui();
        setVisible(true);
        setSize(400,350);
        setLocationRelativeTo(null);
    }

    //EFFECTS: places all necessary components onto JFrame
    public void createGui() {
        panel.setBorder(new EmptyBorder(50, 10, 10, 10));
        placeCover();
        placeEditFields();
        add(panel);
    }

    //MODIFIES: this
    //EFFECTS: places cover image on left side of window
    public void placeCover() {
        cover = new ImageIcon(cover.getImage().getScaledInstance(150, 200, Image.SCALE_DEFAULT));
        JLabel coverLabel = new JLabel(cover);
        panel.add(coverLabel);
    }

    //EFFECTS: places title, author, and submit changes button to right side of window
    public void placeEditFields() {
        JPanel fieldPanel = new JPanel(new GridLayout(3,1));
        fieldPanel.setBorder(new EmptyBorder(50, 0, 100, 0));

        JTextField titleField = new JTextField(13);
        JTextField authorField = new JTextField(13);
        Field field = book.findField("Author");


        placeTitleField(fieldPanel, titleField);
        placeAuthorField(fieldPanel, field, authorField);

        JButton submit = new JButton("Submit changes");
        submit.addActionListener(e -> {
            String newTitle = titleField.getText();
            String newAuthor = authorField.getText();
            book.setTitle(newTitle);
            if (field == null) {
                book.addField(new Text("Author", newAuthor));
            } else {
                field.editField(field.getData().get(0), newAuthor);
            }
            dispose();
            controller.updatePanel();
        });

        fieldPanel.add(submit);
        panel.add(fieldPanel);
    }

    //EFFECTS: places title field onto given panel
    public void placeTitleField(JPanel panel, JTextField titleField) {
        JLabel title = new JLabel("Title");
        titleField.setText(book.getTitle());
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(title);
        titlePanel.add(titleField);
        panel.add(titlePanel);
    }

    //EFFECTS: places author field onto given panel
    public void placeAuthorField(JPanel panel, Field field, JTextField authorField) {
        JLabel author = new JLabel("Author");
        JPanel authorPanel = new JPanel(new FlowLayout());
        authorPanel.add(author);
        authorPanel.add(authorField);
        if (field != null) {
            authorField.setText(field.getData().get(0));
        }
        panel.add(authorPanel);
    }
}
