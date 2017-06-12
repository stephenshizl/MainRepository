package com.example.a61555.bottomnavigationdemo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * 用tablayout来实现底部navigation
 * 需要引入com.android.support:design:24.1.1库文件
 */
public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private Fragment[] fragments;
    private final int TAB_NUM = 4;//Tab页的数量
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //获得创建的Fragment Array
        fragments = TabLayoutUtils.getFragments("TabLayout Tab");
        //获得TabLayout实例
        tabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);
        //初始化Tab
        for(int i=0;i<TAB_NUM;i++) {
            tabLayout.addTab(tabLayout.newTab().setIcon(getResources()
                    .getDrawable(TabLayoutUtils.mTabResUnpressed[i], null))
                    .setText(TabLayoutUtils.mTabTitle[i]));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                onTabItemSelected(tab.getPosition());
                //改变Tab显示状态
                for(int i=0;i< tabLayout.getTabCount();i++){
                    if(i == tab.getPosition()){
                        tabLayout.getTabAt(i).setIcon(getResources().getDrawable(TabLayoutUtils.mTabResPressed[i], null));
                    }else{
                        tabLayout.getTabAt(i).setIcon(getResources().getDrawable(TabLayoutUtils.mTabRes[i], null));
                    }
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    /*
        点击Tab切换页面
     */
    private void onTabItemSelected(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = fragments[0];
                break;
            case 1:
                fragment = fragments[1];
                break;
            case 2:
                fragment = fragments[2];
                break;
            case 3:
                fragment = fragments[3];
                break;
        }
        //切换页面显示的内容
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }

    }
}
