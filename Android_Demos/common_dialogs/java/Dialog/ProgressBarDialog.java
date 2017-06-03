package com.example.a61555.dialogdemo.Dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/6/3.
 */

public class ProgressBarDialog extends MyDialog implements Runnable{

    private ProgressDialog progressDialog;
    private final int MAX_PROGRESS = 100;
    private final int MIN_PROGRESS = 0;

    public ProgressBarDialog(Context context) {
        super(context);
    }

    @Override
    public void init() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIcon(null);
        progressDialog.setTitle("Title");
        progressDialog.setProgress(MIN_PROGRESS);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.setButton("Button1", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了Button1", Toast.LENGTH_SHORT).show();
            }
        });
        progressDialog.setButton2("Button2", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了Button2", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*
        模拟进度条增加
     */
    @Override
    public void run() {
        int Progress = 0;
        while(Progress < MAX_PROGRESS) {
            try {
                Thread.sleep(100);
                Progress++;
                progressDialog.incrementProgressBy(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }
}
