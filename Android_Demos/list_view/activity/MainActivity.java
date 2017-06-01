package com.example.a61555.firstapplication;
/**
 * ArrayAdapter SimpleAdapter的用法 ver 1.0
 * 添加scroll listener、item click listener ver 1.1
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener{

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private String[] strs = {"test1", "test2", "test3", "test4"};//ArrayAdapter 模拟数据
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

        //添加滚动条监听器
        listView.setOnScrollListener(this);
        //添加item监听器
        listView.setOnItemClickListener(this);
    }
    //生成 SimpleAdapter 模拟数据
    private List<Map<String, Object>> getData(){
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Map map = new HashMap();
            map.put("data1", "data1."+new Random().nextLong());
            map.put("data2", "data2."+new Random().nextLong());
            map.put("data3", "data3."+new Random().nextLong());
            list.add(map);
        }
        return list;
    }
    /*  重写滚动条监听器
        监听滚动条状态
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        switch (scrollState){
            case SCROLL_STATE_FLING:
                Log.i("[SCROLL_STATE_FLING]", "手指离开屏幕，滚动条处于滚动状态");
                break;
            case SCROLL_STATE_IDLE:
                Log.i("[SCROLL_STATE_IDLE]", "手指离开屏幕，滚动条已停止滚动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("[SCROLL_STATE_TOUCH_SCROLL]", "手指触摸屏幕，滚动条处于滚动状态");
                break;
        }
    }
    /*  监听滚动条
    */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        Log.i("[当前屏幕可用Item的ID]", firstVisibleItem+"");
        Log.i("[当前屏幕上可用Item数量]", visibleItemCount+"");
        Log.i("[当前屏幕上Item的总数量]", totalItemCount+"");
    }
    /*  重写item点击监听器
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = listView.getItemAtPosition(position)+"";
        Toast.makeText(this,
                "[position]"+position+"\n [text]"+text, Toast.LENGTH_SHORT).show();
    }
}
