package ui;

import model.User;
import model.UserList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents GUI of register page for BookTracker
public class RegisterForm extends Form {
    private JTextField email;

    //EFFECTS: displays GUI of form and saves necessary information
    public RegisterForm(UserList userList, JPanel panel, CardLayout layout) {
        super(userList, panel, layout);
        createGui();
    }

    //EFFECTS: if user credentials don't exist, add to userList and login
    //         else, display "Already exists" message
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = this.username.getText();
        String email = this.email.getText();
        String password = String.valueOf(this.password.getPassword());
        if (userList.checkId(username) || userList.checkId(email)) {
            JOptionPane.showMessageDialog(null,
                    "User already exists. Go to login page.");
        } else {
            User u = new User(username, email, password);
            userList.addUser(u);
            mainPanel.add(new BookPage(u, userList, mainPanel, layout), "3");
            layout.show(mainPanel, "" + (3));
        }
    }

    // EFFECTS: places all components onto panel for form
    public void createGui() {
        JPanel titlePanel = super.createTitle("Register");
        JPanel panel = new JPanel(new GridLayout(5,1));

        panel.add(titlePanel);
        placeFormFields(panel);
        placeButtonRow(panel);

        add(panel);
    }

    //MODIFIES: this
    //EFFECTS: places panels for all user input related components
    public void placeFormFields(JPanel panel) {
        placeUserField(panel);
        email = new JTextField(25);
        JPanel emailPanel = createInputPanel("Email", email);
        panel.add(emailPanel);
        placePassField(panel);
    }

    //EFFECTS: places button row onto panel
    public void placeButtonRow(JPanel panel) {
        JButton submit = new JButton("Register");
        submit.addActionListener(this);
        JButton login = new JButton("Login ->");
        login.addActionListener(e -> layout.show(mainPanel, "" + (1)));
        JPanel buttons = createButtonRow(submit);
        buttons.add(login);

        panel.add(buttons);
    }
}
