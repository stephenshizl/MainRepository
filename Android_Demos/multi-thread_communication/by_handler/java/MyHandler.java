package com.example.a61555.handlerdemo;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 61555 on 2017/7/19.
 */

public class MyHandler extends Handler {

    private View view;
    private SendMsgData data;
    private int what;

    public MyHandler(View view) {
        this.view = view;
    }

    @Override
    public void handleMessage(Message msg) {
        TextView textView = (TextView) view;
        data = (SendMsgData) msg.obj;
        what = msg.what;

        String line = "\n";
        String mark = "From"+what;
        textView.append(line);
        textView.append((new StringBuffer(data.getMessage())));
        textView.append(mark);
    }
}
