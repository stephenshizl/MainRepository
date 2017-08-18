package com.example.a61555.cardviewdemo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 用于获取屏幕大小
 * Created by 61555 on 2017/8/7.
 */

public class ScreenUtils {

    final static int SCREENLAYOUT_SIZE_LARGE = 400;//超过400dp,代表为宽屏或平板设备

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度  
        return  (int) (dm.widthPixels/dm.density);//屏幕宽度(dp)  
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度  
        return (int)(dm.heightPixels/dm.density);//屏幕高度(dp)
    }

    public static int getScreenDpi(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
    }

    /**
     * 判断是否为宽屏或平板设备
     * @param context
     * @return  true:平板,false:手机
     */
    public static boolean isTabletDevice(Context context) {
        return (ScreenUtils.getScreenWidth(context) >= SCREENLAYOUT_SIZE_LARGE) ? true : false;
    }
}
