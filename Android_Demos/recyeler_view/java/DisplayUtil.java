package com.example.a61555.picassoframeworkdemo;

import android.content.Context;

/**
 * Created by 61555 on 2017/8/2.
 */

public class DisplayUtil {
    /**
     * 将dp换算为px
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
