package com.example.a61555.normalreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Android 四大组件 Broadcast
 * 静态注册和动态注册广播接收器
 * 并且包含全局广播和本地广播的实现方法
 * 可检测电池电量、网络连接状态、应用的安装卸载
 */
public class MainActivity extends AppCompatActivity {

    private final String NORMAL_ACTION = "com.example.latou.normal.receiver";//指定接受广播信息的目标的唯一标识
    private final String NORMAL_PRIORITY_ACTION = "com.example.latou.normal.priority.receiver";
    private final String CUSTOM_ACTION = "com.example.latou.custom.receiver";
    private final String LOCAL_ACTION = "com.example.latou.local.receiver";
    private final String PERMISSION_ACTION = "com.example.latou.permission.receiver";

    private final String PRIVATE_PERMISSION = "com.example.permission.receiver";//自定义权限

    private final String TAG = "MainActivity";//打印的日志文件的标题

    private TextView batteryTextView = null;

    private BroadcastReceiver customBroadcastReceiver = null;//自定义广播接收器
    private IntentFilter intentFilter = null;//自定义过滤器

    // 本地广播机制发出的广播只能在应用内部进行传递，而且广播接收器也只能接收本应用内自身发出的广播，
    // 本地广播是无法通过静态注册的方式来接收的，因为静态注册广播主要是为了在程序未启动的情况下也能接收广播，
    // 而本地广播是应用自己发送的，此时应用肯定是启动的了。
    private BroadcastReceiver localBroadcastReceiver = null;
    private LocalBroadcastManager localBroadcastManager = null;
    private IntentFilter localIntentFilter = null;

    private BroadcastReceiver batteryReceiver = null;//监听手机电量 接收系统广播

    private BroadcastReceiver permissionReceiver = null;//自定义权限广播接收器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //在 onResume() 中注册广播接收器
    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    /*
        onPause()注销是因为onPause()在App死亡前一定会被执行，从而保证广播在App死亡前一定会被注销，从而防止内存泄露
     */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(customBroadcastReceiver);
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
        unregisterReceiver(batteryReceiver);
        unregisterReceiver(permissionReceiver);
    }

    public void init() {

        //自定义过滤器
        intentFilter = new IntentFilter(CUSTOM_ACTION);
        //自定义广播接收器
        customBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("broadcast");//读取接受到的String
                Toast.makeText(context, "CustomBroadcastReceiver:"+msg, Toast.LENGTH_SHORT).show();
                Log.e("CustomBroadcastReceiver", msg);
            }
        };
        registerReceiver(customBroadcastReceiver, intentFilter);//动态注册广播接收器

        //本地广播过滤器
        localIntentFilter = new IntentFilter(LOCAL_ACTION);
        //本地广播接收器
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("broadcast");//读取接受到的String
                Toast.makeText(context, "LocalBroadcastReceiver:"+msg, Toast.LENGTH_SHORT).show();
                Log.e("LocalBroadcastReceiver", msg);
            }
        };
        localBroadcastManager.registerReceiver(localBroadcastReceiver, localIntentFilter);//动态注册本地广播接收器

        //系统广播 监听手机电量
        batteryTextView = (TextView) findViewById(R.id.battery_text);
        batteryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int currentBattery = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);// 当前电量
                int totalBattery = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);// 总电量
                batteryTextView.setText("当前电量:" + currentBattery + "  总电量：" + totalBattery);
                Log.e(TAG, "当前电量 :" + currentBattery + "  总电量 ：" + totalBattery);
            }
        };
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        //自定义权限广播接收器
        permissionReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("broadcast");//读取接受到的String
                Toast.makeText(context, "Permission BroadcastReceiver:"+msg, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Permission Broadcast Receiver");
            }
        };
        registerReceiver(permissionReceiver, new IntentFilter(PERMISSION_ACTION), PRIVATE_PERMISSION, null);
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
        localBroadcastManager.sendBroadcast(intent);//发送本地广播
        Log.i(TAG, "Send Local Broadcast...");
    }

    /**
     * 全局广播
     * 限制广播发送的范围，只有声明了相同权限的才能接收
     * @param view
     */
    public void sendPermissionBroadcast(View view) {
        Intent intent = new Intent(PERMISSION_ACTION);
        intent.putExtra("broadcast", "Permission Broadcast");
        sendBroadcast(intent, PRIVATE_PERMISSION);//发送
        Log.i(TAG, "Send Permission Broadcast...");
    }
}
