package com.example.a61555.normalreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 61555 on 2017/7/3.
 * 用于检验广播接收优先级
 * 在配置文件中设置了 FirstNormalReceiver 的优先级为90
 */

public class FirstNormalReceiver extends BroadcastReceiver{

    private static final String TAG = "FirstNormalReceiver";//打印日志文件的标签

    public FirstNormalReceiver() {}
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("broadcast");//读取接受到的String
        Log.e(TAG, msg);
    }
}
