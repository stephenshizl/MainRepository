package com.example.a61555.sqliteoperatedemo;

/**
 * Created by 61555 on 2017/6/27.
 */

public class User {
    public int _id;
    public String name;
    public int age;
    public String info;

    public User() {
    }

    public User(String name, int age, String info) {
        this.name = name;
        this.age = age;
        this.info = info;
    }
}