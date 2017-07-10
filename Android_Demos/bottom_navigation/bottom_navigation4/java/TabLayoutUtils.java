package com.example.a61555.bottomnavigationdemo4;

import android.support.v4.app.Fragment;

/**
 * Created by 61555 on 2017/6/12.
 */

public class TabLayoutUtils {

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
