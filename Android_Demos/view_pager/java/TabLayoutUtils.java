package com.example.a61555.viewpagerdemo;

import android.support.v4.app.Fragment;

/**
 * Created by 61555 on 2017/6/12.
 */

public class TabLayoutUtils {

    public static final String[] viewPagerTitles = new String[]{
            "Home", "Explore", "Like", "Me"
    };

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
