package com.example.a61555.ormlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * 使用 ORMLite 来对数据库进行操作
 * 需引入 ormlite-android & ormlite-core 库
 */
public class MainActivity extends AppCompatActivity {

    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (TextView) findViewById(R.id.text_show);

        UserInfo user = new UserInfo("latou", "earth", "female");
        UserInfo user2 = new UserInfo("latou2", "moon", "male");
        try {
            DatabaseHelper.getDaoInstance(this).create(user);//添加数据
            DatabaseHelper.getDaoInstance(this).create(user2);

            UserInfo userInfo = DatabaseHelper.getDaoInstance(this).queryForId(1);//查找数据
            view.setText(userInfo.toString());

            DatabaseHelper.getDaoInstance(this).deleteById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
