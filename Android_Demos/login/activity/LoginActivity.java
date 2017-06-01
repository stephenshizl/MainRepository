package com.maintain.zhzy.maintain_zhzy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.maintain.zhzy.maintain_zhzy.R;
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
                //参数1：上下文对象 参数2：目标页面的Actiityv
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
