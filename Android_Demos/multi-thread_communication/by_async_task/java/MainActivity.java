package com.example.a61555.asynctaskdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CostumerTask costumerTask = new CostumerTask(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void execute(View view) {
        Button button = (Button) view;
        button.setClickable(false);//点击启动之后，按钮不可使用
        costumerTask.execute();
    }
}
