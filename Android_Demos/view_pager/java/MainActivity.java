package com.example.a61555.viewpagerdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

/**
 * 使用FragmentPagerStateAdapter/FragmentPagerAdapter & Fragment & ViewPager
 * 实现了左右滚动页的功能
 */
public class MainActivity extends FragmentActivity{

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
        //添加页面切换的监听器
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 当页面在滑动时至滑动停止之前，此方法会一直调用
             * @param position 页面编号，向左滑动时为起始页面编号和终止页面编号（从起始变到终止）；向右滑动时为终止页面编号
             * @param positionOffset 当前页面偏移百分比
             * @param positionOffsetPixels 当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 页面跳转完后调用
             * @param position 当前所在页编号
             */
            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(getApplicationContext(), "PAGE "+(position+1)+" BE SELECTED", Toast.LENGTH_SHORT).show();
            }

            /**
             * 页面状态改变时调用，页面状态分为静止、滑动时和滑动后
             * @param state 页面状态
             */
            @Override
            public void onPageScrollStateChanged(int state) {

                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING://滑动状态

                        break;
                    case ViewPager.SCROLL_STATE_IDLE://空闲状态

                        break;
                    case ViewPager.SCROLL_STATE_SETTLING://滑动后自然沉降的状态

                        break;
                }
            }
        });
    }
}
