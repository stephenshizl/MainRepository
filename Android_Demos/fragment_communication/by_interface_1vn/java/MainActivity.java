package com.example.a61555.fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 *  利用接口，实现Activity 和 Fragment 一对多单向通信
 */
public class MainActivity extends AppCompatActivity implements BaseFragment.IExchangeData {

    private TextView tvData = null;
    private Fragment myFragment = null;
    private Fragment myFragment2 = null;
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView) findViewById(R.id.tv_data_ac);
        //创建 Fragment 实例
        myFragment = new MyFragment();
        myFragment2 = new MyFragment2();
        //
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //将 fragment 添加到 activity 页面中
        fragmentTransaction.add(R.id.main_layout, myFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    /**
     * 实现接口，接收从 Fragment 传过来的数据
     * @param data
     */
    @Override
    public void sendData2Activity(String data, String from) {
        tvData.setText("这里是Activity 从"+from+"接收到的数据："+data);
    }

    public void replace(View view) {
        if (myFragment.isAdded())
            getFragmentManager().beginTransaction().replace(R.id.main_layout, myFragment2).commit();
        else
            getFragmentManager().beginTransaction().replace(R.id.main_layout, myFragment).commit();
    }
}
