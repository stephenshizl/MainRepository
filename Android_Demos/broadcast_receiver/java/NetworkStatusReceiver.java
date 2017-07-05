package com.example.a61555.normalreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/7/4.
 */

public class NetworkStatusReceiver extends BroadcastReceiver {

    private ConnectivityManager connMgr = null;//ConnectivityManager主要用于查看网络状态和管理网络连接相关的操作
    private StringBuffer toastMsg = null;//用于存储Toast显示的信息

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //检测 API 版本，在 API21 之后 getNetworkInfo() 方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            //判断 WIFI 连接状态
            if (wifiNetworkInfo.isConnected())
                toastMsg.append("WIFI 已连接,");
            else
                toastMsg.append("WIFI 已断开");
            //判断移动数据连接状态
            if (dataNetworkInfo.isConnected())
                toastMsg.append("移动数据 已连接,");
            else
                toastMsg.append("移动数据 已断开");
        } else {
            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            //通过循环将网络信息逐个取出来
            for (int i=0; i < networks.length; i++){
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                toastMsg.append(networkInfo.getTypeName() + "已" + (networkInfo.isConnected() ? "连接" : "未连接"));
            }
        }
        //显示网络状态
        Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show();
    }
}
