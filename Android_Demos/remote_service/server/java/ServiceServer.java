package com.example.a61555.remoteservicedemo_server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 61555 on 2017/7/7.
 * 远程 Service 的 Server 端
 * 实现了 AIDL 的接口
 */

public class ServiceServer extends Service {

    private static final String TAG = "[ServiceServer]";
    //实例化AIDL的Stub类(Binder的子类)
    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public int basicTypes(int anInt, long aLong, boolean aBoolean,
                              float aFloat, double aDouble, String aString) throws RemoteException {
            Log.d(TAG, "Thread: " + Thread.currentThread().getName());
            Log.d(TAG, "basicTypes aDouble: " + aDouble +" anInt: " + anInt+" aBoolean " + aBoolean+" aString " + aString);
            /*返回给调用者当前的线程编号*/
            return (int) Thread.currentThread().getId();

        }

        //实现接口中的方法
        @Override
        public String myMethod() throws RemoteException {
            Log.d(TAG, "Thread: " + Thread.currentThread().getName());

            return "从客户端发送过来的通信信息";

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "执行了onCreat()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "执行了onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "执行了onDestory()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "执行了onBinder()");
        //在绑定服务的同时，返回实现了的 Stub 类型的 Binder
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "执行了onUnBinder()");
        return super.onUnbind(intent);
    }
}
