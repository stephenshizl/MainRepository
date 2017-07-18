package com.example.a61555.cardviewdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 61555 on 2017/7/18.
 */

public class CardViewListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<CardViewData> dataList;
    private RecyclerViewAdapter adapter;

    public static CardViewListFragment getInstance() {return new CardViewListFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View listView = inflater.inflate(R.layout.card_list, container, false);
        //
        recyclerView = (RecyclerView) listView.findViewById(R.id.rec_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //设置 adapter
        initCardViewData();
        adapter = new RecyclerViewAdapter(dataList, getActivity());
        recyclerView.setAdapter(adapter);

        return listView;
    }

    /**
     * 初始化数据
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
