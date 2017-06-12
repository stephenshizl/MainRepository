package com.example.a61555.bottomnavigationdemo1;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 用tablayout来实现底部navigation
 * 需要引入com.android.support:design:24.1.1库文件
 */
public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private Fragment[] fragments;
    private final int TAB_COUNT = 4;//Tab页的数量
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
        for(int i=0;i<TAB_COUNT;i++) {
            //tabLayout.addTab(tabLayout.newTab().setIcon(getResources().getDrawable(TabLayoutUtils.mTabResUnpressed[i], null)).setText(TabLayoutUtils.mTabTitle[i]));
            tabLayout.addTab(tabLayout.newTab().setCustomView(TabLayoutUtils.getTabView(this, i)));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                onTabItemSelected(tab.getPosition());
                //改变Tab显示状态
                for(int i=0;i< tabLayout.getTabCount();i++){

                    View view = tabLayout.getTabAt(i).getCustomView();
                    ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
                    TextView title = (TextView) view.findViewById(R.id.tab_content_text);

                    if(i == tab.getPosition()){
                        //选中状态
                        //tabLayout.getTabAt(i).setIcon(getResources().getDrawable(TabLayoutUtils.mTabResPressed[i], null));
                        icon.setImageResource(TabLayoutUtils.mTabResPressed[i]);
                        title.setTextColor(getResources().getColor(android.R.color.black));
                    }else{
                        //未选中状态
                        //tabLayout.getTabAt(i).setIcon(getResources().getDrawable(TabLayoutUtils.mTabRes[i], null));
                        icon.setImageResource(TabLayoutUtils.mTabRes[i]);
                        title.setTextColor(getResources().getColor(android.R.color.darker_gray));
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
