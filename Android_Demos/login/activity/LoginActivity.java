package com.example.a61555.loginsessiondemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
/**
 * Created by Administrator on 2017/5/31 0031.
 */
public class LoginActivity extends AppCompatActivity {

    private Button button_login;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        button_login = (Button) findViewById(R.id.btn_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            /*
            注册按钮事件
             */
            @Override
            public void onClick(View v) {
                /*
                    参数1：上下文对象
                    参数2：目标页面的Actiity
                 */
                Intent intent = new Intent(context, null);
                //跳转到目标页面
                startActivity(intent);
            }
        });
    }
}
