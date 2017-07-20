package com.example.a61555.handlerdemo;

import android.os.Handler;
import android.os.Message;

/**
 * Created by 61555 on 2017/7/19.
 */

public class SendMsgThread implements Runnable {

    private Handler handler;
    private long sleepTime;
    private int what;
    private SendMsgData data;

    public SendMsgThread(Handler handler, long sleepTime, int what, SendMsgData data) {
        this.handler = handler;
        this.sleepTime = sleepTime;
        this.what = what;
        this.data = data;
    }

    @Override
    public void run() {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = data;
        handler.sendMessageDelayed(msg, sleepTime);
    }
}
