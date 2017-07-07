package com.example.a61555.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by 61555 on 2017/7/6.
 */

public class LocalFrontService extends Service {

    private static int requestCode = 10;
    private static int flags = 90;

    @Override
    public void onCreate() {
        super.onCreate();
        //添加下列代码将后台Service变成前台Service
        //构建"点击通知后打开MainActivity"的Intent对象
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, new Intent(this, MainActivity.class), flags);
        //新建Builer对象
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("这里有一个前端服务通知");
        builder.setContentTitle("这是前台服务通知的标题");//设置通知的标题
        builder.setContentText("这是前台服务通知的内容");//设置通知的内容
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知的图标
        builder.setContentIntent(pendingIntent);//设置点击通知后的操作

        Notification notification = builder.build();//将Builder对象转变成普通的notification
        startForeground(1, notification);//让Service变成前台Service,并在系统的状态栏显示出来
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
