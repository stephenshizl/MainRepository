package com.example.a61555.normalreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 61555 on 2017/7/3.
 * 继承 BroadcastReceiver 类创建一个用于接收标准广播的Receiver，在 onReceive() 方法中取出 Intent 传递来的字符串
 */

public class NormalReceiver extends BroadcastReceiver{

    private static final String TAG = "NormalReceiver";//打印日志文件的标签

    public NormalReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("broadcast");//读取接受到的String
        Log.e(TAG, msg);
    }
}
