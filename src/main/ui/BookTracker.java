package ui;

import model.Book;
import model.User;
import model.UserList;
import persistence.JsonHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Represents the BookTracker application
public class BookTracker {
    private static final String JSON_STORE = "./data/booktracker.json";
    private Scanner scanner;
    private UserList userList;
    private User currentUser;
    private ArrayList<Book> bookList;

    private JsonHandler jsonHandler;

    public BookTracker() throws FileNotFoundException {
        runBookTracker();
    }

    //EFFECTS: runs main program
    public void runBookTracker() {
        boolean keepRunning = true;
        int choice;
        init();

        while (keepRunning) {
            if (currentUser == null) {
                handleLoad();
                login();
            }
            menu();
            choice = scanner.nextInt();
            if (choice == 6) {
                currentUser = null;
                bookList = null;
            } else if (choice == 7) {
                keepRunning = false;
            } else {
                handleMenu(choice);
            }
        }
    }

    //EFFECTS: instantiates users and scanner for user input
    public void init() {
        scanner = new Scanner(System.in);
        userList = new UserList();
        jsonHandler = new JsonHandler(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: handles login for users; if user is not registered, registers user
    public void login() {
        System.out.println("Login to BookTracker");
        System.out.print("Username or email: ");
        String id = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        currentUser = userList.findUser(id, password);
        if (currentUser == null) {
            if (userList.checkId(id)) {
                System.out.println("Wrong password. Try again.");
                login();
            } else {
                System.out.println("User login does not exist.");
                createUser(id, password);
            }
        }

        bookList = currentUser.getBookList();
        System.out.println("\nWelcome, " + currentUser.getUsername() + ".");
    }

    //MODIFIES: this
    //EFFECTS: creates a user with option to use previous id and password, otherwise
    //         updates to new input
    private void createUser(String id, String password) {
        System.out.println("Register new user");
        String username = checkUserLoginChoice("username", id);
        String email = checkUserLoginChoice("email", id);
        String newPassword = checkUserLoginChoice("password", password);

        User user = new User(username, email, newPassword);
        userList.addUser(user);
        currentUser = user;
    }

    //EFFECTS: template for asking whether user wants to keep previous login
    private String checkUserLoginChoice(String choice, String preset) {
        System.out.print("Use " + preset + " as " + choice + " ? (Y/n) ");
        String input = scanner.next();
        if (input.equalsIgnoreCase("n")) {
            choice = choice.substring(0, 1).toUpperCase() + choice.substring(1);
            System.out.print(choice + ": ");
            return scanner.next();
        } else {
            return preset;
        }
    }

    //EFFECTS: prints out menu
    private void menu() {
        System.out.println("Choose one of the following: ");
        System.out.println("\t1. Add book to list");
        System.out.println("\t2. Remove book from list");
        System.out.println("\t3. View all books");
        System.out.println("\t4. Edit book");
        System.out.println("\t5. Save list");
        System.out.println("\t6. Logout");
        System.out.println("\t7. Quit");
    }

    //EFFECTS: handles user choice from menu
    @SuppressWarnings("methodlength")
    private void handleMenu(int choice) {
        switch (choice) {
            case 1:
                addBook();
                System.out.println("New book has been added.");
                break;
            case 2:
                Book deleteBook = chooseBook();
                if (deleteBook != null) {
                    removeBook(deleteBook);
                    System.out.println(deleteBook.getTitle() + " has been removed.");
                } else {
                    System.out.println("No book found");
                }
                break;
            case 3:
                displayBooks();
                break;
            case 4:
                System.out.println("This function hasn't been implemented yet.");
                break;
            case 5:
                saveUserData();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    //MODIFIES: this
    //EFFECT: adds book to list
    private void addBook() {
        System.out.println("Title of book? ");
        scanner.nextLine();
        String title = scanner.nextLine();
        Book newBook = new Book(title);
        bookList.add(newBook);
    }

    //REQUIRES: bookList.contains(book)
    //MODIFIES: this
    //EFFECT: removes book from list
    private void removeBook(Book book) {
        bookList.remove(book);
    }

    //EFFECT: displays list of books
    private void displayBooks() {
        System.out.println("Current books in list: ");
        for (int i = 0; i < bookList.size(); i++) {
            if (i % 10 == 0 && i > 0) {
                System.out.println("Go to next page? (Y/n)");
                scanner.nextLine();
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("n")) {
                    break;
                }
            }
            System.out.println("\t" + (i + 1) + ". " + bookList.get(i).getTitle());
        }
        System.out.println();
    }

    //REQUIRES: bookList.size() > 0
    //EFFECTS: user chooses a book in bookList
    private Book chooseBook() {
        displayBooks();
        if (bookList.size() > 0) {
            System.out.println("Choose a book:");
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= bookList.size()) {
                return bookList.get(choice - 1);
            }
            System.out.println("Invalid book choice.");
            return chooseBook();
        } else {
            return null;
        }
    }

    //MODIFIES: this
    //EFFECTS: handles whether user wants to load saved data
    private void handleLoad() {
        try {
            UserList tempUserList = jsonHandler.read();
            System.out.println("Save data found.");
            System.out.println("Load saved data? (Y/n) ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("n")) {
                userList = tempUserList;
                System.out.println("Save data successfully loaded!\n");
            }
        } catch (IOException e) {
            // do nothing
        }
    }

    //EFFECTS: saves the userList to file
    private void saveUserData() {
        try {
            jsonHandler.open();
            jsonHandler.write(userList);
            jsonHandler.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file.");
        }
    }
}
