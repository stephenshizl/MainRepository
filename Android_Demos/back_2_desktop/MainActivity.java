package com.example.a61555.back2desktopdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * 实现了双击返回桌面的功能
 */
public class MainActivity extends AppCompatActivity {

    private long exitTime;
    private final long MIN_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断是否点击系统回退键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //判断两次点击的时间间隔是否小于2秒
            if (System.currentTimeMillis() - exitTime > MIN_TIME) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();////显示提示信息
                exitTime = System.currentTimeMillis();//记录当前时间
            } else
                System.exit(0);//退出程序
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
