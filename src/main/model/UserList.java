package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of users
public class UserList implements Writable {
    private List<User> userList;

    //EFFECT: creates an empty list of users
    public UserList() {
        userList = new ArrayList<>();
    }

    //REQUIRES: !userList.contains(user)
    //MODIFIES: this
    //EFFECTS: add user to list
    public void addUser(User user) {
        userList.add(user);
    }

    //REQUIRES: userList.contains(user)
    //MODIFIES: this
    //EFFECTS: removes user from list
    public void removeUser(User user) {
        userList.remove(user);
    }


    //EFFECT: returns size of userList
    public int getSize() {
        return userList.size();
    }

    //EFFECT: finds user with given credentials
    //        return null if not found
    public User findUser(String id, String password) {
        for (User user : userList) {
            if (user.checkLogin(id, password)) {
                return user;
            }
        }
        return null;
    }

    //EFFECTS: returns true if id is in registered users
    public boolean checkId(String id) {
        for (User user : userList) {
            if (user.getUsername().equals(id) || user.getEmail().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (User u : userList) {
            jsonArray.put(u.toJson());
        }
        return json.put("users", jsonArray);
    }

}
