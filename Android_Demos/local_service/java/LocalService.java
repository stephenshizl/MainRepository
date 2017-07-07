package com.example.a61555.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 61555 on 2017/7/5.
 * 此 Service 是最基础的，但只能单机使用，即无法与 Activity 通信
 */

public class LocalService extends Service {

    static final String tag = "Local Service";
    //只会在初次启动的时候才会执行
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(tag, "执行了onCreat()");
    }
    //可被多次执行
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(tag, "执行了onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }
    //service 关闭时执行
    @Override
    public void onDestroy() {
        Log.i(tag, "执行了onDestory()");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
