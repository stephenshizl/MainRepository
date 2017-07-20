package com.example.a61555.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 61555 on 2017/7/20.
 */

public class MyIntentService extends IntentService {


    public MyIntentService(){super("WorkThread");}
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    /**
     * 重写 onHandleIntent 方法，用于实现耗时操作
     * IntentService 单独开启一个新的 HandlerThread
     * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //耗时操作的具体内容
        Log.i("IntentService", "onHandleIntent");
        int flag = intent.getExtras().getInt("flag");
        String msg = intent.getExtras().getString("msg");
        Log.i("[Message]", msg+" FROM "+flag);
    }

    @Override
    public void onCreate() {
        Log.i("IntentService", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i("IntentService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("IntentService", "onDestroy");
        super.onDestroy();
    }
}
