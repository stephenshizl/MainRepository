package com.example.a61555.cardviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *  需引入
 *  com.android.support:cardview-v7:x.x.x
    com.android.support:recyclerview-v7:x.x.x
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<CardViewData> dataList;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     *
     */
    public void init() {
        //
        initCardViewData();
        //
        recyclerView = (RecyclerView) findViewById(R.id.rec_view);
        layoutManager = new LinearLayoutManager(this);
        //
        recyclerView.setLayoutManager(layoutManager);
        //
        adapter = new RecyclerViewAdapter(dataList, this);
        //
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     *
     */
    public void initCardViewData() {
        dataList = new ArrayList<CardViewData>();
        dataList.add(new CardViewData("标题1", "小标题1", R.color.colorAccent, getString(R.string.sup_content1)));
        dataList.add(new CardViewData("标题2", "小标题2", R.color.cardview_shadow_start_color, getString(R.string.sup_content2)));
        dataList.add(new CardViewData("标题3", "小标题3", R.color.cardview_shadow_end_color, getString(R.string.sup_content3)));
        dataList.add(new CardViewData("标题4", "小标题4", R.color.colorPrimary, getString(R.string.sup_content4)));
        dataList.add(new CardViewData("标题5", "小标题5", R.color.colorPrimaryDark, getString(R.string.sup_content5)));
    }
}
