package com.example.a61555.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 61555 on 2017/7/5.
 * 在新建子类继承Service类，并新建一个子类继承自Binder类,Activity可以通过Binder类中的方法和Service通信
 */

public class LocalBindService extends Service {

    static final private String tag = "[LocalBindService]";
    static private String state = "none";
    private MyBinder mBinder = null;

    //只会在初次启动的时候才会执行
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(tag, "执行了onCreat()");
        state = "已创建完成";
    }
    //可被多次执行
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(tag, "执行了onStartCommand()");
        state = "已打开服务";
        return super.onStartCommand(intent, flags, startId);
    }
    //service 关闭时执行
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(tag, "执行了onDestory()");
        state = "已关闭服务";
    }
    //绑定服务
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.i(tag, "执行了onBind()");
        if (mBinder == null) {
            mBinder = new MyBinder();
        }
        state = "已绑定服务";
        //返回实例
        return mBinder;
    }
    //解绑服务
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(tag, "执行了onUnbind()");
        state = "已解绑服务";
        return super.onUnbind(intent);
    }

    //新建一个子类继承自Binder类
    class MyBinder extends Binder {
        public String getServiceState() {
            return state;
        }
    }

}
