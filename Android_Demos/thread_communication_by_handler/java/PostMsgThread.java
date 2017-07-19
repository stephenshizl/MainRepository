package com.example.a61555.handlerdemo;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 61555 on 2017/7/19.
 */

public class PostMsgThread implements Runnable {

    private Handler handler;
    private long sleepTime;
    private View view;
    private StringBuffer msg;

    public PostMsgThread(Handler handler, long sleepTime, View view, StringBuffer msg) {
        this.handler = handler;
        this.sleepTime = sleepTime;
        this.view = view;
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView) view;
                String line = "\n";
                StringBuffer text = new StringBuffer(textView.getText());
                text.append(line).append(msg);
                textView.setText(text);
            }

        });
    }
}
