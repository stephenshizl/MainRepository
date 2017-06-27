package com.example.a61555.sqliteoperatedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 61555 on 2017/6/27.
 */

public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DBManager(Context context) {
        this.helper = new DBHelper(context);
        this.db = helper.getWritableDatabase();
        this.context = context;
    }

    /**
     * add users
     * @param users
     */
    public void add(List<User> users) {
        db.beginTransaction();  //开始事务
        try {
            for (User user : users) {
                db.execSQL("INSERT INTO user VALUES(null, ?, ?, ?)", new Object[]{user.name, user.age, user.info});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * update user's age
     * @param user
     */
    public void insertUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put("age", user.age);
        cv.put("name", user.name);
        cv.put("info", user.info);
        long rowId = db.insert("user", null, cv);//返回插入数据的id
        Toast.makeText(context, "已插入一条测试数据，id =" + rowId, Toast.LENGTH_SHORT).show();
    }

    /**
     * update user's age
     * @param user
     */
    public void updateAge(User user) {
        ContentValues cv = new ContentValues();
        cv.put("age", user.age);
        /*
            Param1：表名；
            Param2：含有修改数据ContentValues；
            Param3：条件；
            Param4：条件的值
         */
        long rowId = db.update("user", cv, "name = ?", new String[]{user.name});
        Toast.makeText(context, "已更新一条测试数据，id =" + rowId, Toast.LENGTH_SHORT).show();
    }
    /**
     * delete old user
     * @param user
     */
    public void deleteOldUser(User user) {

        int lastId = getTableRow();//获得末尾数据的id
        user._id = lastId;
        if(user._id > 0)
            Toast.makeText(context, "已删除第 "+ lastId +" 行（最末行）数据", Toast.LENGTH_SHORT).show();
        long rowId = db.delete("user", "_id = ?", new String[]{String.valueOf(user._id)});
        Toast.makeText(context, "已删除一条测试数据，id =" + rowId, Toast.LENGTH_SHORT).show();
        if(user._id == 0)
            Toast.makeText(context, "数据库已清空", Toast.LENGTH_SHORT).show();
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
            user.age = c.getInt(c.getColumnIndex("age"));
            user.info = c.getString(c.getColumnIndex("info"));
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

    /**
     * move cursor to last
     * @return
     */
    public int getTableRow(){
        Cursor c = queryTheCursor();
        if (c.moveToLast() == false)
        {
            //为空的Cursor
            return 0;
        }
        else
        {
            int user_id = c.getInt(c.getColumnIndex("_id"));
            return user_id;
        }
    }
}