package com.example.a61555.bottomnavigationdemo3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

/**
 * 需引入 com.ashokvarma.android:bottom-navigation-bar
 * 使用BottomNavigationBar实现底端导航栏效果
 */
public class MainActivity extends AppCompatActivity {

    private Fragment[] mFragments;
    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //获取fragments
        mFragments = TabLayoutUtils.getFragments("Bottom Navigation Bar");
        //获取bottomNavigationBar实例
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.navigation);
        /*
            BottomNavigationBar.BACKGROUND_STYLE_RIPPLE：Bottom navigation的背景色是设置的颜色（ActiveColor）
            BottomNavigationBar.BACKGROUND_STYLE_STATIC：Bottom navigation的背景色是白色
         */
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //添加Item
        for(int i=0;i<TabLayoutUtils.NAVIGATION_BAR_COUNT;i++) {
            BottomNavigationItem bottomNavigationItem = new BottomNavigationItem(TabLayoutUtils.mTabRes[i],
                    TabLayoutUtils.mTabTitle[i]);
            //bottomNavigationItem.setActiveColor(TabLayoutUtils.tabAliveColor[i]);
            bottomNavigationBar.addItem(bottomNavigationItem);
        }
        //设置初始界面
        onTabItemSelected(TabLayoutUtils.TAB_POSITION_HOME);
        bottomNavigationBar.setFirstSelectedPosition(TabLayoutUtils.TAB_POSITION_HOME);
        /*
            BottomNavigationBar.MODE_SHIFTING：不固定大小
            BottomNavigationBar.MODE_FIXED：固定大小
         */
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //生成navigation bar
        bottomNavigationBar.initialise();
        //设置tab监听
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                onTabItemSelected(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
    /*
        切换界面
     */
    private void onTabItemSelected(int id){
        Fragment fragment = null;
        switch (id){
            case TabLayoutUtils.TAB_POSITION_HOME:
                fragment = mFragments[0];
                break;
            case TabLayoutUtils.TAB_POSITION_EXPLORE:
                fragment = mFragments[1];
                break;
            case TabLayoutUtils.TAB_POSITION_LIKE:
                fragment = mFragments[2];
                break;
            case TabLayoutUtils.TAB_POSITION_ME:
                fragment = mFragments[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }
}
