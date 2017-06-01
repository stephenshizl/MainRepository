package com.example.a61555.firstapplication;
/**
 * ArrayAdapter SimpleAdapter的用法
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private String[] strs = {"test1", "test2", "test3", "test4"};//模拟数据
    private List<Map<String, Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.得到组件
        //listView = (ListView) findViewById(R.id.list_view1);
        listView = (ListView) findViewById(R.id.list_view2);
        /*2.新建适配器 :
            context : 上下文
            resource : 布局文件id 可自定义
            data : 数据源
         */
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, strs);
        /*2.新建适配器 :
            context : 上下文
            data : 数据源 map的列表 每一个元素代表listview中的一行 map中的key为from中的名称
            resource : 布局文件id
            from : 数据源名称 自定义
            to : 目标视图id
         */
        simpleAdapter = new SimpleAdapter(getApplicationContext(), getData(),
                R.layout.item, new String[]{"data1", "data2", "data3"}, new int[]{R.id.data1, R.id.data2, R.id.data3});
        //3.加载适配器
        //listView.setAdapter(arrayAdapter);
        listView.setAdapter(simpleAdapter);
    }
    //生成模拟数据
    private List<Map<String, Object>> getData(){
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map map = new HashMap();
            map.put("data1", "data1."+new Random().nextLong());
            map.put("data2", "data2."+new Random().nextLong());
            map.put("data3", "data3."+new Random().nextLong());
            list.add(map);
        }
        return list;
    }
}
