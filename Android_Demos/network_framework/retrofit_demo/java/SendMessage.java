package com.example.a61555.okhttpdemo;

import android.os.Handler;
import android.os.Message;

/**
 * Created by 61555 on 2017/7/31.
 */

public class SendMessage implements Runnable {

    private Handler handler;
    private int what;//标识符
    private String result;//查询结果

    public SendMessage(Handler handler, int what, String result) {
        this.handler = handler;
        this.what = what;
        this.result = result;
    }

    @Override
    public void run() {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = result;
        handler.sendMessage(msg);
    }
}
