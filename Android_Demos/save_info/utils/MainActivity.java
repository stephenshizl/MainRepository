package com.example.a61555.sharedpreferencedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 实现了一个登录的界面
 * 可将用户信息存放在File里面
 * 可将用户信息存放在Shared Preference 里面
 * 可将用户信息存放在SQLite数据库 里面
 * 并且可以在跳转之后在下一个页面取出用户信息
 */
public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox saveInfoCheckBox;
    private boolean saveInfoFlag = false;
    private String username;
    private String password;
    private SaveInfoUtils saveInfoUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        saveInfoUtils = new SaveInfoUtils(this);
        //
        loginBtn = (Button) findViewById(R.id.login_btn);
        usernameEditText = (EditText) findViewById(R.id.edit_text_username);
        passwordEditText = (EditText) findViewById(R.id.edit_text_password);
        saveInfoCheckBox = (CheckBox) findViewById(R.id.save_info_checkbox);
        //获取checkbox的选中状态
        saveInfoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveInfoFlag = isChecked;
            }
        });
        //
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户信息
                username = usernameEditText.getText()+"";
                password = passwordEditText.getText()+"";
                //判断是否保存用户信息
                if (saveInfoFlag) {
                    //将info存入File中
                    //saveInfoUtils.saveUserInfo2File(new User(username, password));
                    //将info存入SharedPreference中
                    //saveInfoUtils.saveUserInfo2Pref(new User(username, password));
                    //将数据存入数据库中
                    saveInfoUtils.saveUserInfo2DB(new User(username, password));
                } else {
                    Toast.makeText(MainActivity.this, "dont save user info", Toast.LENGTH_SHORT).show();
                }
                //跳转到目标页面
                Intent intent = new Intent(MainActivity.this, NextPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
