package com.example.a61555.progressbardemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements Runnable{

    private ProgressBar progressBar;
    private int firstProgress;
    private int secondProgress;
    private int maxProgress;
    private ProgressDialog progressDialog;
    private Button progressDialogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        firstProgress = progressBar.getProgress();
        secondProgress = progressBar.getSecondaryProgress();
        maxProgress = progressBar.getMax();
        progressDialogBtn = (Button) findViewById(R.id.btn_progressDialog);
        progressDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得ProgressDialog实例
                progressDialog = new ProgressDialog(MainActivity.this);
                //设置显示风格
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //设置标题
                progressDialog.setTitle("Downloading");
                //设置显示文本‘
                progressDialog.setMessage("Hello World");
                //设置图标
                progressDialog.setIcon(R.drawable.ic_launcher);
                //设置最大进度
                progressDialog.setMax(100);
                //设置初始进度
                progressDialog.incrementProgressBy(50);
                //是否隐藏进度的数值
                progressDialog.setIndeterminate(false);
                //设置按钮
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "目前的进度："+progressDialog.getProgress(), Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.setCancelable(true);
                progressDialog.show();
            }
        });

        new Thread(this).start();
    }

    @Override
    public void run() {

        while (firstProgress < maxProgress || secondProgress <maxProgress) {
            progressBar.incrementProgressBy(10);
            progressBar.incrementSecondaryProgressBy(20);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
