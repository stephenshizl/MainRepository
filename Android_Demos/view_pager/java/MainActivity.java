package com.example.a61555.viewpagerdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    private Fragment[] fragments;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private MyFragmentStatePagerAdapter myFragmentStatePagerAdapter;
    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    private final int FIRST_VIEW_PAGER_NUM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //获得组件
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        //获得fragment array
        fragments = TabLayoutUtils.getFragments("Pager View Title");
        //取消显示pager tab strip下的线
        pagerTabStrip.setDrawFullUnderline(false);
        //创建fragment pager adapter
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, TabLayoutUtils.viewPagerTitles);
        //创建fragment state pager adapter
        myFragmentStatePagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(), fragments, TabLayoutUtils.viewPagerTitles);
        //设置view pager的adapter
        //FragmentPagerAdapter不会调用Fragment的onDestroy方法
        //FragmentStatePagerAdapter会调用Fragment的onDestroy方法
        viewPager.setAdapter(myFragmentStatePagerAdapter);
        //自定义首页
        viewPager.setCurrentItem(FIRST_VIEW_PAGER_NUM);
    }
}
