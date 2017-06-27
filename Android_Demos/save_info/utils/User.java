package com.example.a61555.sharedpreferencedemo;

/**
 * Created by 61555 on 2017/6/27.
 */

public class User {
    public int _id;
    public String name;
    public String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}