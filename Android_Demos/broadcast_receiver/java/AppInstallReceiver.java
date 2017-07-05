package com.example.a61555.normalreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 61555 on 2017/7/4.
 * 用于监听和接收APP安装、更新和卸载的广播
 */

public class AppInstallReceiver extends BroadcastReceiver {

    private final String TAG = "AppInstallReceiver";

    public AppInstallReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {

        //判断广播类型
        String action = intent.getAction();
        //获取包名
        String appName = intent.getDataString();
        Log.d(TAG, "安装了：" + appName);
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {//ACTION_PACKAGE_INSTALL过时，不建议使用
            Log.d(TAG, "安装了：" + appName);
        } else if (Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
            Log.d(TAG, "更新了：" + appName);
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            Log.d(TAG, "卸载了：" + appName);
        }
    }
}
