# BookList Application

The application will be a reading tracker that allows users to __maintain a reading list of books that they have read__, with each book having its individual features or tags such as rating, genre, and comments. Customization of such features can be made possible. These features can be updated anytime as well. Additionally, images can be uploaded as icons for the books. The application will also implement a __search and filter__ function based on a book's features as well to enhance navigability.

### Personal Interest
As an avid reader myself, I've always wanted a more personalized book tracker as I delve into lots of different genres of books and comics especially. Using this opportunity, I wanted to develop an application that gave more freedom on the list of books I can add, and track them based off common or even custom fields. This type of program is also an opportunity for me to learn how to implement applications with multiple users so that the data for each user is kept and accessed independently.

## User Stories
- As a user, I want to be able to create and register an account
- As a user, I want to be able to have the option to load user data from file
- As a user, I want to be able to have the option to save user data and access it after quitting and logging back into my account
- As a user, I want to be able to add a book to my reading list
- As a user, I want to be able to view the list of books I've added to my reading list
- As a user, I want to be able to edit any feature of a book I've added to my reading list
- As a user, I want to be able to delete a book from my reading list
- As a user, I want to be able to search for a book in my reading list
- As a user, I want to be able to filter my reading list and view books with the selected feature

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by logging in, clicking the "Add Book" button, and filling the prompt asking for title of the book
- You can generate the second required action related to the user story "removing X from Y" by clicking the button with the trash icon beside each book title
- You can locate my visual component by: Stock image of a book cover for every book added
- You can save the state of my application by clicking the "Save" button at the top right corner of the user's page
- You can reload the state of my application by clicking "Yes" when prompted to before the start of the whole program

## Phase 4: Task 2
```
Username registered as a user. 
Username added book: BookTitle 
Username added book: BookTitle2 
Username added book: BookTitle3 
All user data saved.  
Username logged in. 
Username removed book: BookTitle3 
All user data saved.
```

## Phase 4: Task 3
A refactor I could implement if I had more time to improve the design would be making the UserList also use the Singleton Pattern, similar to EventLog. This way, the program for the GUI wouldn't have to pass UserList as a parameter to every class that represents a page as the Singleton Pattern would provide global access instead. As the UserList class is only instantiated once through each run of the program, the Singleton Pattern would work well. Similarly, as seen in the UML diagram, many classes have one instance of UserList and since all of them are same instance of UserList passed around as a parameter, I think refactoring it to implement a Singleton Pattern could improve my design.