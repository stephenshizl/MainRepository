package com.example.a61555.handlerdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 使用 Handler 进行多个线程之间通信
 */
public class MainActivity extends AppCompatActivity {

    private Handler postMsgHandler;
    private Handler sendMsgHandler;
    public TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = (TextView) findViewById(R.id.show);
        StringBuffer text = new StringBuffer();
        text.append("Carson_Ho:Do you love me?");
        show.setText(text);

        /*
            用 Post 方式发送 Message
         */
        //实例化Handler,这里并无指定Looper,即自动绑定当前线程(主线程)的Looper和MessageQueue
        //postMsgHandler = new Handler();
        //启动子线程
        //new Thread(new PostMsgThread(postMsgHandler, 3000, show, new StringBuffer("angelababy:Yes,I do"))).start();
        //new Thread(new PostMsgThread(postMsgHandler, 5000, show, new StringBuffer("黄晓明:what the fuck?"))).start();

        /*
            用 Send 方式发送 Message
         */
        //
        sendMsgHandler = new MyHandler(show);
        //
        new Thread(new SendMsgThread(sendMsgHandler, 3000, 1, new SendMsgData("This Message"))).start();
        new Thread(new SendMsgThread(sendMsgHandler, 5000, 2, new SendMsgData("There is Message"))).start();
    }
}
