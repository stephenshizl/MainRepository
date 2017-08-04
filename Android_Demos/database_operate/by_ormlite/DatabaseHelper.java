package com.example.a61555.ormlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by 61555 on 2017/8/3.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "test_db";
    private static final int DATABASE_VERSION = 1;
    private static Dao daoInstance = null;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static Dao<UserInfo,Integer> getDaoInstance(Context context) {
        if (daoInstance == null) {
            try {
                daoInstance = new DatabaseHelper(context).getDao(UserInfo.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return daoInstance;
    }

    /**
     * 数据库创建操作
     * @param database
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserInfo.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库更新操作
     * @param database
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @Override
    public void close() {
        super.close();
    }
}
