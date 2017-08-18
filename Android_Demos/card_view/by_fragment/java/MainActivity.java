package com.example.a61555.cardviewdemo;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 *  需引入
 *  com.android.support:cardview-v7:x.x.x
    com.android.support:recyclerview-v7:x.x.x
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setScreenOrientation(this);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.container, CardViewListFragment.getInstance())
                .commit();
    }

    /**
     * 设置屏幕方向
     * @param context
     */
    private void setScreenOrientation(Context context){
        //手机，竖屏
        if(!ScreenUtils.isTabletDevice(context)) {
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else{
            //平板，横屏
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected void onDestroy() {
        System.out.println("onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        System.out.println("onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        System.out.println("onStop");
        super.onStop();
    }

    @Override
    protected void onResume() {
        System.out.println("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("onPause");
        super.onPause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        System.out.println("onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
}