package com.example.a61555.datepickerdemo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private EditText dateEditText;
    private DateUtils dateUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取文本编辑框对象
        dateEditText = (EditText) findViewById(R.id.date);
        //获得DateUtils实例
        dateUtils = DateUtils.getInstance();
        //初始化年月日
        dateUtils.initDate();
        //绑定点击监听事件
        dateEditText.setOnClickListener(this);
        //timeEditText.setOnClickListener(this);
        //初始化文本框显示日期信息
        setDateText();
    }
    /*
        点击日期编辑框之后调用的方法
     */
    @Override
    public void onClick(View v) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            //在日期改变时调用的方法
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateUtils.setDate(year, month, dayOfMonth);
                setDateText();
            }
        }, dateUtils.getYear() , dateUtils.getMonth(), dateUtils.getDay()).show();//初始化年月日信息
    }
    /*
        设置EditText内显示的信息
     */
    private void setDateText() {
        char dateCharArray[] = dateUtils.getDateCharArray();
        dateEditText.setText(dateCharArray, 0, dateCharArray.length);
    }
}
