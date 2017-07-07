package com.example.a61555.servicedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 本地服务的实现方法，包括静态声明和动态声明
 */
public class MainActivity extends AppCompatActivity {

    private TextView localServiceState = null;
    private TextView localBindServiceState = null;

    private Intent localServiceIntent = null;
    private Intent localBindServiceIntent = null;
    private Intent localFrontServiceIntent = null;

    private MyServiceConnection mServiceConnection = null;

    private static final String localServicePrefix = "本地服务:";
    private static final String localBindServicePrefix = "本地可通信服务状态:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        localServiceState = (TextView) findViewById(R.id.local_service_state);
        localBindServiceState = (TextView) findViewById(R.id.bind_service_state);

        localServiceIntent = new Intent(this, LocalService.class);
        localBindServiceIntent = new Intent(this, LocalBindService.class);
        localFrontServiceIntent = new Intent(this, LocalFrontService.class);

        mServiceConnection = new MyServiceConnection();
    }
    //打开本地服务
    public void openLocalService(View v) {
        startService(localServiceIntent);
        localServiceState.setText(localServicePrefix+"已打开");
    }
    //关闭本地服务
    public void closeLocalService(View v) {
        stopService(localServiceIntent);
        localServiceState.setText(localServicePrefix+"已关闭");
    }
    //打开可通信服务
    public void openLocalBindService(View v) {
        startService(localBindServiceIntent);
        localBindServiceState.setText(localBindServicePrefix+"已经打开");
    }
    //关闭可通信服务
    public void closeLocalBindService(View v) {
        stopService(localBindServiceIntent);
        localBindServiceState.setText(localBindServicePrefix+"已经关闭");
    }
    //绑定服务
    public void bindService(View v) {
        //参数说明
        //第一个参数:Intent对象
        //第二个参数:上面创建的Serviceconnection实例
        //第三个参数:标志位
        //  标志位传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service
        //  这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
        bindService(localBindServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        localBindServiceState.setText(localBindServicePrefix+"已经绑定服务");
    }
    //解绑服务
    public void unbindService(View v) {
        unbindService(mServiceConnection);
        localBindServiceState.setText(localBindServicePrefix+"已经解绑服务");
    }
    //打开前端服务，服务会已通知的方式弹出
    public void openFrontService(View v) {
        startService(localFrontServiceIntent);
    }
    //关闭前端服务
    public void closeFrontService(View v) {
        stopService(localFrontServiceIntent);
    }
}
