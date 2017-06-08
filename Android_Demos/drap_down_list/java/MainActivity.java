package com.example.a61555.drapdownlistdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Spinner spinner;
    private List<String> list;
    private String[] strList;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //
        textView = (TextView) findViewById(R.id.text_view);
        textView.setText("你选择的是：Stage 1");
        spinner = (Spinner) findViewById(R.id.spinner);
        //
        strList = new String[]{"Stage 1", "Stage 2", "Stage 3", "Stage 4", "Stage 5"};
        //获得ArrayAdapter实例，传入List或者Array都可以
        //arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getData());
        //设置spinner的下拉样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //设置adpater
        spinner.setAdapter(arrayAdapter);
        //
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText("你选择的是："+ strList[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private List getData() {
        list = new ArrayList<String>();
        for (String str: strList) {
            list.add(str);
        }
        return list;
    }
}
