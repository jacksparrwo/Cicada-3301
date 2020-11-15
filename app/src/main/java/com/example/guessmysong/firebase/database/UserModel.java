package com.example.guessmysong.firebase.database;

import java.util.List;

public class UserModel {

    private String username;
    private String password;
    private String gender;
    private int level;
    private List<String> achievements;

    public UserModel() {
        // default constructor, in case it is used but should be not
    }

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
