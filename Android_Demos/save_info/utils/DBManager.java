package com.example.a61555.sharedpreferencedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 61555 on 2017/6/27.
 */

public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        this.helper = new DBHelper(context);
        this.db = helper.getWritableDatabase();
    }

    /**
     * add users
     * @param users
     */
    public void add(List<User> users) {
        db.beginTransaction();  //开始事务
        try {
            for (User user : users) {
                db.execSQL("INSERT INTO user VALUES(null, ?, ?)", new Object[]{user.name, user.password});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * query all users, return list
     * @return List<User>
     */
    public List<User> query() {
        ArrayList<User> users = new ArrayList<User>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            User user = new User();
            user._id = c.getInt(c.getColumnIndex("_id"));
            user.name = c.getString(c.getColumnIndex("name"));
            user.password = c.getString(c.getColumnIndex("password"));
            users.add(user);
        }
        c.close();
        return users;
    }
    /**
     * query all users, return cursor
     * @return  Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM user", null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}