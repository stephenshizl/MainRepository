package com.example.a61555.gridviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private int[] iconId;
    private String[] iconName;
    private SimpleAdapter gridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //获得GridView
        gridView = (GridView) findViewById(R.id.grid_view);
        //Icon的ID数组
        iconId = new int[]{R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher
                , R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher
                , R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};
        //Icon的名字数组
        iconName = new String[]{"通讯录", "日历", "计算器", "网络", "设置", "浏览器", "相册", "时钟", "短信"};
        //
        gridAdapter = new SimpleAdapter(this, getData(), R.layout.item,
                new String[]{"image", "text"}, new int[]{R.id.image_view, R.id.text_view});
        //
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked the "+iconName[position], Toast.LENGTH_SHORT).show();
            }
        });
        //
        gridView.setAdapter(gridAdapter);
    }

    private List getData() {
        //获取DataList实例
        dataList = new ArrayList<Map<String, Object>>();
        for (int i=0;i<iconId.length;i++) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("image",iconId[i]);
            map.put("text",iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }
}
