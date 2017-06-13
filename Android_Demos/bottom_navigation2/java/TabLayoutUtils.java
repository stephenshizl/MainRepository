package com.example.a61555.bottomnaviagtiondemo2;

import android.support.v4.app.Fragment;

/**
 * Created by 61555 on 2017/6/12.
 */

public class TabLayoutUtils {
    public static final int []mTabRes = new int[]{
            R.drawable.ic_tab_strip_icon_feed,
            R.drawable.ic_tab_strip_icon_category,
            R.drawable.ic_tab_strip_icon_pgc,
            R.drawable.ic_tab_strip_icon_profile};
    public static final int []mTabResPressed = new int[]{
            R.drawable.ic_tab_strip_icon_feed_selected,
            R.drawable.ic_tab_strip_icon_category_selected,
            R.drawable.ic_tab_strip_icon_pgc_selected,
            R.drawable.ic_tab_strip_icon_profile_selected};

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[]{
                HomeFragment.newInstance(from),
                DiscoveryFragment.newInstance(from),
                AttentionFragment.newInstance(from),
                ProfileFragment.newInstance(from)
        };
        return fragments;
    }
}
