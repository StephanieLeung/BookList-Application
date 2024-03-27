package ui;

import model.User;
import model.UserList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents Login page for GUI
// CREDIT: this class is modelled off of the SmartHome GUI from lecture
public class LoginForm extends Form {

    //EFFECTS: displays GUI of form and saves needed information
    public LoginForm(UserList userList, JPanel panel, CardLayout layout) {
        super(userList, panel, layout);
        createGui();
    }

    //EFFECTS: if user credentials matched, login to BookTracker
    //         else, display fail message
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = this.username.getText();
        String password = String.valueOf(this.password.getPassword());
        User user = userList.findUser(username, password);
        if (user != null) {
            mainPanel.add(new BookPage(user, userList, mainPanel, layout), "3");
            this.username.setText("");
            this.password.setText("");
            layout.show(mainPanel, "" + (3));
        } else {
            JOptionPane.showMessageDialog(null,
                    "User credentials not found.");
        }
    }

    //EFFECTS: creates layout of GUI with necessary components
    public void createGui() {
        JPanel titlePanel = super.createTitle("Login");
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(titlePanel);
        placeFormFields(panel);
        placeButtons(panel);

        add(panel);
    }

    //EFFECTS: places label and text fields for user input fields onto panel
    public void placeFormFields(JPanel panel) {
        placeUserField(panel);
        placePassField(panel);
    }

    //EFFECTS: places login and register buttons onto panel, adds key listener so ENTER also submits
    public void placeButtons(JPanel panel) {
        JButton submit = new JButton("Login");
        submit.addActionListener(this);
        JButton register = new JButton("Register ->");
        register.addActionListener(e -> layout.show(mainPanel, "" + (2)));
        JPanel buttons = createButtonRow(submit);
        buttons.add(register);

        panel.add(buttons);
    }
}
