package ui;

import model.*;
import model.Event;
import persistence.JsonHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

// Represents GUI version of BookTracker
// CREDIT: Learned how to use Swing from https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/
public class BookTrackerUi extends JFrame implements WindowListener {
    private static final String JSON_STORE = "./data/booktracker.json";

    private final UserList userList;
    private final JsonHandler jsonHandler;


    //MODIFIES: this
    //EFFECTS: creates GUI interface and starts with login screen
    public BookTrackerUi() {
        jsonHandler = new JsonHandler(JSON_STORE);
        userList = handleLoad();
        createGui();
        setSize(600,400);
        setVisible(true);
        setLocationRelativeTo(null);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(this);
    }

    //MODIFIES: this
    //EFFECTS: adds all pages to GUI
    public void createGui() {
        CardLayout layout = new CardLayout();
        JPanel panel = new JPanel(layout);
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        EventLog eventLog = EventLog.getInstance();
        for (Event event: eventLog) {
            System.out.println(event.getDescription());
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    //EFFECTS: runs the program
    public static void main(String[] args) {
        new BookTrackerUi();
    }
}
