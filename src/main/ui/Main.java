package ui;

import java.io.FileNotFoundException;

// Represents main program class
public class Main {
    public static void main(String[] args) {
        try {
            new BookTracker();
        } catch (FileNotFoundException e) {
            // do nothing
        }
    }
}
