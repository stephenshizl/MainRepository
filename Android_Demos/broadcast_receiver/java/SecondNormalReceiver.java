package com.example.a61555.normalreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/7/3.
 * 用于检验广播接收优先级
 * 在配置文件中设置了 SecondNormalReceiver 的优先级为100
 */
public class SecondNormalReceiver extends BroadcastReceiver{

    private static final String TAG = "SecondNormalReceiver";//打印日志文件的标签

    public SecondNormalReceiver() {}
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("broadcast");//读取接受到的String
        //abortBroadcast();// 截断广播，优先级低于 SecondNormalReceiver 的将会接收不到广播，但是在静态注册的接收器不可以截断广播
        Toast.makeText(context, TAG+":"+msg, Toast.LENGTH_SHORT).show();
        Log.e(TAG, msg);

    }
}
