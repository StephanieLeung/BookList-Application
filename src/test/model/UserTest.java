package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    User user1;

    @BeforeEach
    void setup() {
        user1 = new User("user", "user@gmail.com", "123");
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