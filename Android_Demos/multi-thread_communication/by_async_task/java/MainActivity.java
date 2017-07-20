package com.example.a61555.asynctaskdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private CostumerTask costumerTask = new CostumerTask(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        costumerTask.cancel(true);
        super.onDestroy();
    }

    public void execute(View view) throws ExecutionException, InterruptedException {
        Button button = (Button) view;
        costumerTask.execute();
        button.setClickable(false);//点击启动之后，按钮不可再次使用
    }
}
