package com.example.a61555.debugapplication;

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
 * 将信息存放在Shared Preference 里面
 */
public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox saveInfoCheckBox;
    private boolean saveInfoFlag = false;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
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
                //跳转到目标页面
                Intent intent = new Intent(MainActivity.this, NextPageActivity.class);
                //获取用户信息
                username = usernameEditText.getText()+"";
                password = passwordEditText.getText()+"";
                //判断是否保存用户信息
                if (saveInfoFlag) {
                    Toast.makeText(MainActivity.this, "save user info", Toast.LENGTH_SHORT).show();
                    //保存用户信息
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    bundle.putString("password", password);
                    intent.putExtra("info", bundle);
                } else {
                    Toast.makeText(MainActivity.this, "dont save user info", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
            }
        });
    }
}
