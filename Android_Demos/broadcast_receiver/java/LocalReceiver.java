package com.example.a61555.normalreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/7/7.
 */

public class LocalReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //获取手机所有连接LOCATION_SERVICE对象
        LocationManager locationManager = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
        Toast.makeText(context, (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)?"打开GPS":"关闭GPS"),
                Toast.LENGTH_SHORT).show();
    }
}
