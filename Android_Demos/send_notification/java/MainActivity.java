package com.example.a61555.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 实现手机通知栏
 */
public class MainActivity extends AppCompatActivity {

    private Button show_btn;
    private Button cancel_btn;
    private Notification.Builder builder;
    private NotificationManager notificationManager;
    private final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //获取两个按钮实例
        show_btn = (Button) findViewById(R.id.show_notif);
        cancel_btn = (Button) findViewById(R.id.cancel_notif);
        //Notification 为系统常用服务
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        builder = new Notification.Builder(this);//获得Notification的Builder
        builder.setSmallIcon(R.drawable.ic_launcher_round);//设置通知栏图标
        builder.setTicker("Hello World");//设置通知栏小标题
        builder.setWhen(System.currentTimeMillis());//设置通知栏显示时间
        builder.setContentTitle("Notification Title");//设置通知栏标题
        builder.setContentText("Notification Content");//设置通知栏具体内容
        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder构造Notification实例，并将实例传给Manager
                notificationManager.notify("Test", NOTIFICATION_ID, builder.build());//发送通知到通知栏
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.cancel(NOTIFICATION_ID);//取消通知栏消息
            }
        });
    }
}
