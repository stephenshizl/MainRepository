package com.example.a61555.servicedemo;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by 61555 on 2017/7/6.
 */

public class MyServiceConnection implements ServiceConnection {

    private LocalBindService.MyBinder myBinder = null;
    private static final String tag = "[MyServiceConnection]";

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        myBinder = (LocalBindService.MyBinder) service;
        Log.i(tag, myBinder.getServiceState());
    }
    /*
        onServiceDisconnected is only called in extreme situations (crashed / killed).
        which is very unlikely to happen for a local service
        since all your application components normally run in the same process... meaning,
        unless you intentionnaly unbind or destroy the service, it should remain connected,
        or die with the component using it.
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.i(tag, myBinder.getServiceState());
    }
}
