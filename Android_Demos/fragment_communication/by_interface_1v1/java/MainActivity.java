package com.example.a61555.fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 *  利用接口，实现Activity 和 Fragment 一对一相互通信
 */
public class MainActivity extends AppCompatActivity implements IExchangeData {

    private TextView tvData = null;
    private EditText etData = null;
    private MyFragment myFragment = null;
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private IExchangeData iExchangeData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView) findViewById(R.id.tv_data_ac);
        etData = (EditText) findViewById(R.id.et_data_ac);
        //创建 Fragment 实例
        myFragment = new MyFragment();
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
        iExchangeData = (IExchangeData) fragment;
        super.onAttachFragment(fragment);
    }

    /**
     * 点击按钮调用的方法
     * @param view
     */
    public void sendData(View view) {
        iExchangeData.sendData2Fragment(etData.getText().toString());
    }

    /**
     * 实现接口，接收从 Fragment 传过来的数据
     * @param data
     */
    @Override
    public void sendData2Activity(String data) {
        tvData.setText("这里是Activity 从Fragment接收到的数据："+data);
    }

    @Override
    public void sendData2Fragment(String data) {}
}
