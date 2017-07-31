package com.example.a61555.okhttpdemo;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 61555 on 2017/7/31.
 */

public class MyHandler extends Handler {
    private View view;
    private String result;
    private int what;

    public MyHandler(View view) {
        this.view = view;
    }

    @Override
    public void handleMessage(Message msg) {
        TextView textView = (TextView) view;
        result = (String) msg.obj;
        what = msg.what;
        if (what == -1)
            textView.setText("无返回结果");
        else
            textView.setText(result);
    }
}
