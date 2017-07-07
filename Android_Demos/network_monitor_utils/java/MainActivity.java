package com.example.a61555.networkmonitordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getNetworkStatus(View view) {
        Toast.makeText(this, NetworkUtils.getAPNTypeStr(this), Toast.LENGTH_SHORT).show();
    }

    public void getMobileDataStatus(View view) {
        Toast.makeText(this, (NetworkUtils.isMobileConnected(this)?"已连接移动数据":"未连接移动数据"),
                Toast.LENGTH_SHORT).show();
    }

    public void getWifiStatus(View view) {
        Toast.makeText(this, (NetworkUtils.isWifiConnected(this)?"已连接WIFI":"未连接移动数据"), Toast.LENGTH_SHORT).show();
    }
}
