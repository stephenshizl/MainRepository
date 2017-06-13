package com.example.a61555.bottomnavigationdemo3;

import android.support.v4.app.Fragment;

/**
 * Created by 61555 on 2017/6/12.
 */

public class TabLayoutUtils {

    public static final int TAB_POSITION_HOME = 0;
    public static final int TAB_POSITION_EXPLORE = 1;
    public static final int TAB_POSITION_LIKE = 2;
    public static final int TAB_POSITION_ME = 3;
    public static final int NAVIGATION_BAR_COUNT = 4;

    public static final int []mTabRes = new int[]{
            R.drawable.ic_tab_strip_icon_category_24dp,
            R.drawable.ic_tab_strip_icon_feed_24dp,
            R.drawable.ic_tab_strip_icon_pgc_24dp,
            R.drawable.ic_tab_strip_icon_profile_24dp};
    public static final int []tabAliveColor = new int[]{
            R.color.aliveColorHome,R.color.aliveColorExplore,
            R.color.aliveColorLike,R.color.aliveColorMe
    };
    public static final String []mTabTitle = new String[]{"Home","Explore","Like","Me"};

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
