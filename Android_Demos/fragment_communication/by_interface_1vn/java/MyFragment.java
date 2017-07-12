package com.example.a61555.fragmentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 61555 on 2017/7/11.
 */

public class MyFragment extends BaseFragment {

    private EditText etData = null;
    private Button btnSend = null;
    private IExchangeData iSendData = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iSendData = (IExchangeData) activity;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment, container, false);//构造 Fragment
        etData = (EditText) view.findViewById(R.id.et_data_fg);
        btnSend = (Button) view.findViewById(R.id.btn_send_fg);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSendData.sendData2Activity(etData.getText().toString(), "Fragment");//点击按钮后发送信息到 Activity
            }
        });
        return view;
    }
}
