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
                db.execSQL("INSERT INTO user VALUES(null, ?, ?)", new Object[]{user.getName(), user.getPassword()});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * query all users, return list
     * 一个最需要注意的是，在我们的结果集中必须要包含一个“_id”的列，
     * 否则SimpleCursorAdapter就会翻脸不认人，因为这源于SQLite的规范，主键以“_id”为标准。
     * @return List<User>
     */
    public List<User> query() {
        ArrayList<User> users = new ArrayList<User>();
        Cursor cursor = queryTheCursor();
        cursor.moveToLast();//只取最新的数据
        User user = new User();
        user.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
        user.setName(cursor.getString(cursor.getColumnIndex("name")));
        user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        users.add(user);
        /*while (cursor.moveToNext()) {
            User user = new User();
            user.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            users.add(user);
        }*/
        cursor.close();
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