package com.example.a61555.viewflipperdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * 使用 ViewFlipper 实现图片轮播效果
 * 增加了手指滑动翻页的功能
 */
public class MainActivity extends AppCompatActivity {

    private final int VIEW_FLIPPER_FLIP_INTERVAL = 5000;
    private final int MIN_FINGER_MOVE_LENGTH = 200;
    private float startX;
    private float endX;
    private ViewFlipper viewFlipper;
    private final int[] pictures = new int[]{
            R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        //添加ImageView到viewFlipper
        for (int resId : pictures) {
            viewFlipper.addView(getImageView(resId));
        }
        //设置图片切换时间
        viewFlipper.setFlipInterval(VIEW_FLIPPER_FLIP_INTERVAL);
        //设置图片切入和切出动画
        viewFlipper.setInAnimation(this, R.anim.right_in);
        viewFlipper.setOutAnimation(this, R.anim.left_out);
        //图片开始翻动
        //viewFlipper.setAutoStart(true);
        viewFlipper.startFlipping();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //手指落下
            case MotionEvent.ACTION_DOWN:
                //获取当前手指触摸的坐标X
                startX = event.getX();
                //当手指触摸了就停止滚动
                viewFlipper.stopFlipping();
                break;
            //手指移动
            case MotionEvent.ACTION_MOVE:
                break;
            //手指离开
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                //手指向左滑动
                if (startX-endX >= MIN_FINGER_MOVE_LENGTH) {
                    viewFlipper.setInAnimation(this, R.anim.right_in);
                    viewFlipper.setOutAnimation(this, R.anim.left_out);
                    viewFlipper.showNext();
                }
                //手指向右滑动
                else if (endX-startX >= MIN_FINGER_MOVE_LENGTH) {
                    viewFlipper.setInAnimation(this, R.anim.left_in);
                    viewFlipper.setOutAnimation(this, R.anim.right_out);
                    viewFlipper.showPrevious();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /* 获得 ImageView 方法
     */
    private ImageView getImageView(int resId) {
        ImageView imageView = new ImageView(this);
        //imageView.setImageResource(resId);
        imageView.setBackgroundResource(resId);
        return imageView;
    }
}
