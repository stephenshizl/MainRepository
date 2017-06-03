package com.example.a61555.dialogdemo;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.a61555.dialogdemo.Dialog.CheckBoxDialog;
import com.example.a61555.dialogdemo.Dialog.CustomDialog;
import com.example.a61555.dialogdemo.Dialog.ListDialog;
import com.example.a61555.dialogdemo.Dialog.LoardDialog;
import com.example.a61555.dialogdemo.Dialog.MultiButtonDialog;
import com.example.a61555.dialogdemo.Dialog.NormalDialog;
import com.example.a61555.dialogdemo.Dialog.ProgressBarDialog;
import com.example.a61555.dialogdemo.Dialog.RadioButtonDialog;

/**
 * by:������
 * email:xuanyusong@qq.com
 * @author Administrator
 *
 */

public class MainDialog extends Activity {
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //确定取消信息框
        Button button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                new NormalDialog(MainDialog.this).getBuilder().show();
            }
        });
        //多按钮信息框
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                new MultiButtonDialog(MainDialog.this).getBuilder().show();
            }
        });
        //列表框
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	    new ListDialog(MainDialog.this).getBuilder().show();
            }
        });
        //进度条框
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ProgressBarDialog progressBarDialog = new ProgressBarDialog(MainDialog.this);
                progressBarDialog.getProgressDialog().show();
                new Thread(progressBarDialog).start();
            }
        });
        //单选框
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	    new RadioButtonDialog(MainDialog.this).getBuilder().show();
            }
        });  
        //复选框
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	    new CheckBoxDialog(MainDialog.this).getBuilder().show();
            }
        }); 
        //自定义布局框
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	    new CustomDialog(MainDialog.this).getBuilder().show();
            }
        }); 
        //加载进度框
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	new LoardDialog(MainDialog.this).getProgressDialog().show();
            }
        }); 
    }
}