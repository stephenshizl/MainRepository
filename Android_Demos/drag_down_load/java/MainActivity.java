package com.example.a61555.dragdownloaddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 用 ScrollView 实现向下滑动加载
 */
public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        textView = (TextView) findViewById(R.id.content_text_view);
        //设置平滑滚动
        scrollView.setSmoothScrollingEnabled(true);
        //点击屏幕时调用
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //当手指离开时调用
                if (event.getAction() == MotionEvent.ACTION_UP)
                    //当滚动到顶端时
                    if (scrollView.getScrollY() <= 0)
                        Toast.makeText(MainActivity.this, "已经到顶了", Toast.LENGTH_SHORT).show();
                    //当滚动到底端时
                    else if (scrollView.getScrollY()+scrollView.getHeight() >= textView.getHeight()) {
                        //在文本尾添加新内容
                        Toast.makeText(MainActivity.this, "正在加载……", Toast.LENGTH_SHORT).show();
                        /*try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        textView.append(getResources().getText(R.string.content));
                    }
                return false;
            }
        });
    }
}
