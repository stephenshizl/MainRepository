package com.example.a61555.expanablelistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private MyExpandableListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init() {
        final String[] groupStrings = {"西游记", "水浒传", "三国演义", "红楼梦"};
        final String[][] childStrings = { {"唐三藏", "孙悟空", "猪八戒", "沙和尚"},
                                    {"宋江", "林冲", "李逵", "鲁智深"},
                                    {"曹操", "刘备", "孙权", "诸葛亮", "周瑜"},
                                    {"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"} };

        expandableListView = (ExpandableListView) findViewById(R.id.expand_list);
        adapter = new MyExpandableListAdapter(groupStrings, childStrings, this);
        expandableListView.setAdapter(adapter);//设置数据转换器
        expandableListView.setGroupIndicator(null);//隐藏指示标志
        //设置子选项点击监听器
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this, groupStrings[groupPosition]+"-"+childStrings[groupPosition][childPosition],
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //设置分组点击监听器
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //Toast.makeText(MainActivity.this, "group:"+groupPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //设置分组展开监听器
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //关闭无关分组
                for (int i=0;i<groupStrings.length;i++) {
                    if (i != groupPosition)
                        expandableListView.collapseGroup(i);
                }
                //Toast.makeText(MainActivity.this, "Expanded", Toast.LENGTH_SHORT).show();
            }
        });
        //设置分组关闭监听器
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //Toast.makeText(MainActivity.this, "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
