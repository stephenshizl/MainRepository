package com.example.a61555.intentservicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 *  使用 IntentService 来进行多线程通信
 *  与后台线程相比，IntentService是一种后台服务，优势是：优先级高（不容易被系统杀死），从而保证任务的执行
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity", "onCreate");
        /*
            Android 5.0 之后 Ientent 需要显式调用
            在设置 Action 的同时，需要设置对应的 Package 名称
         */
        Intent intent = new Intent();
        intent.setAction("latou.intent.service");
        intent.setPackage("com.example.a61555.intentservicedemo");
        //创建数据包
        Bundle bundle = new Bundle();
        bundle.putInt("flag", 1);
        bundle.putString("msg", "Hello World");
        intent.putExtras(bundle);

        startService(intent);
        //创建数据包
        Bundle bundle2 = new Bundle();
        bundle2.putInt("flag", 2);
        bundle2.putString("msg", "We do not talk any more");
        intent.putExtras(bundle2);

        startService(intent);
    }
}
