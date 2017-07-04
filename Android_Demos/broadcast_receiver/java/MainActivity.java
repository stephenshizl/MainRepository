package com.example.a61555.normalreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Android 四大组件 Broadcast
 * 静态注册和动态注册广播接收器
 * 并且包含全局广播和本地广播的实现方法
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String NORMAL_ACTION = "com.example.latou.normal.receiver";//指定接受广播信息的目标的唯一标识
    private final String NORMAL_PRIORITY_ACTION = "com.example.latou.normal.priority.receiver";
    private final String CUSTOM_ACTION = "com.example.latou.custom.receiver";
    private final String LOCAL_ACTION = "com.example.latou.local.receiver";
    private final String TAG = "MainActivity";//打印的日志文件的标题

    private Button btnNormalBroadcsat = null;
    private Button btnNormalPriorityBroadcast = null;
    private Button btnCustomBroadcast = null;
    private Button btnLocalBroadcast = null;

    private BroadcastReceiver customBroadcastReceiver = null;//自定义广播接收器
    private IntentFilter intentFilter = null;//自定义过滤器

    // 本地广播机制发出的广播只能在应用内部进行传递，而且广播接收器也只能接收本应用内自身发出的广播，
    // 本地广播是无法通过静态注册的方式来接收的，因为静态注册广播主要是为了在程序未启动的情况下也能接收广播，
    // 而本地广播是应用自己发送的，此时应用肯定是启动的了。
    private BroadcastReceiver localBroadcastReceiver = null;
    private LocalBroadcastManager localBroadcastManager = null;
    private IntentFilter localIntentFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {

        btnNormalBroadcsat = (Button) findViewById(R.id.normal_broadcast);
        btnNormalBroadcsat.setOnClickListener(this);

        btnNormalPriorityBroadcast = (Button) findViewById(R.id.normal_priority_broadcast);
        btnNormalPriorityBroadcast.setOnClickListener(this);

        //自定义过滤器
        intentFilter = new IntentFilter(CUSTOM_ACTION);
        //自定义广播接收器
        customBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("broadcast");//读取接受到的String
                Log.e("CustomBroadcastReceiver", msg);
            }
        };
        registerReceiver(customBroadcastReceiver, intentFilter);//动态注册广播接收器
        btnCustomBroadcast = (Button) findViewById(R.id.custom_broadcast);
        btnCustomBroadcast.setOnClickListener(this);

        //本地广播过滤器
        localIntentFilter = new IntentFilter(LOCAL_ACTION);
        //本地广播接收器
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("broadcast");//读取接受到的String
                Log.e("LocalBroadcastReceiver", msg);
            }
        };
        localBroadcastManager.registerReceiver(localBroadcastReceiver, localIntentFilter);//动态注册本地广播接收器
        btnLocalBroadcast = (Button) findViewById(R.id.local_broadcast);
        btnLocalBroadcast.setOnClickListener(this);
    }

    /**
     * 全局广播
     * 发送一条测试用标准广播数据
     * 接受广播者使用静态注册
     * @param view
     */
    public void sendNormalBroadcast(View view) {
        Intent intent = new Intent(NORMAL_ACTION);
        intent.putExtra("broadcast", "Hello World");//类似于Map，第一个参数为key，第二个为values
        sendBroadcast(intent);
        Log.i(TAG, "Send Normal Broadcast...");
    }

    /**
     * 全局广播
     * 发送一条测试用标准广播数据
     * 接受广播者使用静态注册
     * 根据两个接收器优先级不同，收到信息的先后顺序也不同
     * 先接收到广播的可以调用 abortBroadcast() 方法截断广播
     * @param view
     */
    public void sendNormalPriorityBroadcast(View view) {
        Intent intent = new Intent(NORMAL_PRIORITY_ACTION);
        intent.putExtra("broadcast", "Priority Broadcast");
        sendBroadcast(intent);
        Log.i(TAG, "Send Normal Priority Broadcast...");
    }

    /**
     * 全局广播
     * 给自定义广播接收器发送广播
     * @param view
     */
    public void sendCustomBroadcast(View view) {
        Intent intent = new Intent(CUSTOM_ACTION);
        intent.putExtra("broadcast", "Custom Broadcast");
        sendBroadcast(intent);
        Log.i(TAG, "Send Custom Broadcast...");
    }

    /**
     * 本地广播
     * 给本地广播接收器发送广播
     * @param view
     */
    public void sendLocalBroadcast(View view) {
        Intent intent = new Intent(LOCAL_ACTION);
        intent.putExtra("broadcast", "Local Broadcast");
        sendBroadcast(intent);
        Log.i(TAG, "Send Local Broadcast...");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_broadcast:
                sendNormalBroadcast(v);
                break;
            case R.id.normal_priority_broadcast:
                sendNormalPriorityBroadcast(v);
                break;
            case R.id.custom_broadcast:
                sendCustomBroadcast(v);
                break;
            case R.id.local_broadcast:
                sendLocalBroadcast(v);
            default:
                break;
        }
    }
}
