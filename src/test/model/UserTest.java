package model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user1;

    @BeforeEach
    void setup() {
        user1 = new User("user", "user@gmail.com", "123");
    }

    @Test
    void getUsernameTest() {
        assertEquals("user", user1.getUsername());
    }

    @Test
    void getEmailTest() {
        assertEquals("user@gmail.com", user1.getEmail());
    }

    @Test
    void getBookListTest() {
        assertEquals(new ArrayList<Book>(), user1.getBookList());
    }

    @Test
    void setUsernameTest() {
        user1.setUsername("user2");
        assertTrue(user1.checkLogin("user2", "123"));
    }

    @Test
    void setPasswordTest() {
        user1.setPassword("321");
        assertTrue(user1.checkLogin("user", "321"));
    }

    @Test
    void checkLoginTest() {
        assertTrue(user1.checkLogin("user", "123"));
        assertTrue(user1.checkLogin("user@gmail.com", "123"));
        assertFalse(user1.checkLogin("User", "123"));
        assertFalse(user1.checkLogin("user", "321"));
        assertFalse(user1.checkLogin("user@gmail.com", "321"));
    }
}