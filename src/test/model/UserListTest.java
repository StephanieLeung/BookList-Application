package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserListTest {
    private UserList userList;
    private User user1;

    @BeforeEach
    void setup() {
        userList = new UserList();
        user1 = new User("user", "email1", "123");
    }

    @Test
    void addUserTest() {
        assertEquals(0, userList.getSize());
        userList.addUser(user1);
        assertEquals(1, userList.getSize());
    }

    @Test
    void removeUserTest() {
        userList.addUser(user1);
        assertEquals(1, userList.getSize());
        userList.removeUser(user1);
        assertEquals(0, userList.getSize());
    }

    @Test
    void findUserTestNoUser() {
        assertNull(userList.findUser(".", "."));
    }

    @Test
    void findUserTestNoMatchUser() {
        userList.addUser(user1);
        assertNull(userList.findUser("1","123"));
    }

    @Test
    void findUserTestFullMatch() {
        userList.addUser(user1);
        assertEquals(user1, userList.findUser("user", "123"));
        assertEquals(user1, userList.findUser("email1", "123"));
    }

    @Test
    void findUserTestHalfMatch() {
        userList.addUser(user1);
        assertNull(userList.findUser("user","321"));
        assertNull(userList.findUser("email1", "432"));
        assertNull(userList.findUser(".", "123"));
    }

    @Test
    void checkIdTest() {
        assertFalse(userList.checkId("user"));
        userList.addUser(user1);
        assertTrue(userList.checkId("user"));
        assertTrue(userList.checkId("email1"));
        assertFalse(userList.checkId("123"));
    }
}
