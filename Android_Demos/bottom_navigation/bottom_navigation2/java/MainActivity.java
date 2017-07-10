package com.example.a61555.bottomnaviagtiondemo2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * 需引入 com.android.support:design
 * 使用BottomNavigationView+Fragment实现底部导航栏
 * BottomNavigatonView的tab只能是3-5个，多了或者少了是会报错。
 * 第一次进入页面的时候不会调用onNavigationItemSelected 方法因此第一次需要手动调用 添加fragment的方法。
 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //获取fragment array
        mFragments = TabLayoutUtils.getFragments("Bottom Navigation View");
        //获得bottomNavigationView 实例
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        //设置初始页面
        mBottomNavigationView.setSelectedItemId(R.id.tab_menu_home);
        onTabItemSelected(R.id.tab_menu_home);
        //设置监听
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onTabItemSelected(item.getItemId());
                return true;
            }
        });
    }
    /*
        切换界面
     */
    private void onTabItemSelected(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.tab_menu_home:
                fragment = mFragments[0];
                break;
            case R.id.tab_menu_discovery:
                fragment = mFragments[1];
                break;

            case R.id.tab_menu_attention:
                fragment = mFragments[2];
                break;
            case R.id.tab_menu_profile:
                fragment = mFragments[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }

}
