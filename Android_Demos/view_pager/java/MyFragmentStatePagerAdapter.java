package com.example.a61555.viewpagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by 61555 on 2017/6/14.
 */

public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] fragments;
    private String[] viewPagerTitles;

    public MyFragmentStatePagerAdapter(FragmentManager fm, Fragment[] fragments, String[] viewPagerTitles) {
        super(fm);
        this.fragments = fragments;
        this.viewPagerTitles = viewPagerTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return viewPagerTitles[position];
    }


}
