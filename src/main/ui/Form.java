package ui;

import model.UserList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents generic form using Swing
public abstract class Form extends JPanel implements ActionListener {
    protected UserList userList;
    protected JTextField username;
    protected JPasswordField password;
    protected CardLayout layout;
    protected JPanel mainPanel;

    //MODIFIES: this
    //EFFECTS: creates form with saved panel, layout and user list
    public Form(UserList userList, JPanel panel, CardLayout layout) {
        this.userList = userList;
        this.mainPanel = panel;
        this.layout = layout;
    }

    //EFFECTS: creates title panel
    public JPanel createTitle(String title) {
        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        Font font = new Font("SansSerif", Font.BOLD, 20);
        titleLabel.setFont(font);
        titlePanel.add(titleLabel);

        return titlePanel;
    }

    //EFFECTS: creates generic input panel from given label and field
    public JPanel createInputPanel(String label, JComponent field) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel(label));
        panel.add(field);
        return panel;
    }

    //EFFECTS: creates panel with row of button
    public JPanel createButtonRow(JButton button) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(button);
        return panel;
    }

    //MODIFIES: this
    //EFFECTS: places user field onto given panel
    public void placeUserField(JPanel panel) {
        username = new JTextField(25);
        JPanel userPanel = createInputPanel("Username", username);
        panel.add(userPanel);
    }

    //MODIFIES: this
    //EFFECTS: places password field onto given panel
    public void placePassField(JPanel panel) {
        password = new JPasswordField(25);
        JPanel passPanel = createInputPanel("Password", password);
        panel.add(passPanel);
    }

    //EFFECTS: performs action based on button clicked
    @Override
    public abstract void actionPerformed(ActionEvent e);
}
