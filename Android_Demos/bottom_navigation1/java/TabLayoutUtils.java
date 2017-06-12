package com.example.a61555.bottomnavigationdemo1;

import android.support.v4.app.Fragment;

/**
 * Created by 61555 on 2017/6/12.
 */

public class TabLayoutUtils {

    public static final int []mTabRes = new int[]{R.drawable.tab_home_selector,
            R.drawable.tab_discovery_selector,
            R.drawable.tab_attention_selector,
            R.drawable.tab_profile_selector};

    public static final int []mTabResPressed = new int[]{
            R.drawable.ic_tab_strip_icon_feed_selected,
            R.drawable.ic_tab_strip_icon_category_selected,
            R.drawable.ic_tab_strip_icon_pgc_selected,
            R.drawable.ic_tab_strip_icon_profile_selected};

    public static final int []mTabResUnpressed = new int[]{
            R.drawable.ic_tab_strip_icon_category,
            R.drawable.ic_tab_strip_icon_category,
            R.drawable.ic_tab_strip_icon_pgc,
            R.drawable.ic_tab_strip_icon_profile};
    //Tab页的Title
    public static final String []mTabTitle = new String[]{"Home","Explore","Like","Me"};
    //创建4个Tab页
    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = DiscoveryFragment.newInstance(from);
        fragments[2] = AttentionFragment.newInstance(from);
        fragments[3] = ProfileFragment.newInstance(from);
        return fragments;
    }
}
