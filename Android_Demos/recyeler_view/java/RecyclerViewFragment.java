package com.example.a61555.picassoframeworkdemo;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/8/2.
 */

public class RecyclerViewFragment extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager layoutManager;
    private final static int SPAN_COUNT = 4;//设置图片列数
    private String[] picUrls = {
            "http://alioss.g-cores.com/uploads/image/2d73e365-bd3f-45a3-82d9-39fa342f22be_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/922fba32-e306-4ceb-a48c-ce6805ade28a_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/8c624b62-092c-41c4-9ce6-0757a8778b1e_watermark.png",
            "http://alioss.g-cores.com/uploads/image/2a9749a2-70a5-4874-a543-a7c4ef85ae92_watermark.png",
            "http://alioss.g-cores.com/uploads/image/deda5641-4bb7-4aa3-9cca-daa4f7f76bdd_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/282b7148-a7ce-4295-a2a5-94aa78637f78_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/aebdf097-9799-497b-91c9-5ae3badc9528_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/40af0223-1db1-4156-a83c-6f4c2bc4820d_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/15dc1387-3443-41de-97c6-ea15a5aca2c9_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/23adde99-bab7-4ec9-9dfb-5ae0c8145cff_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/da5d4588-cc6a-4a41-afbd-d01139772fe7_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/79f4623b-6be2-4b35-8fbf-299db398bcd6_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/705a813f-8f4a-4a4a-b564-8efd05cf43ba_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/62d9787c-1ae1-4e7e-b4c9-74f1f707720c_watermark.jpg",
            "http://alioss.g-cores.com/uploads/image/abda59a9-d413-40e4-abf5-51743f40c854_watermark.jpg"
    };//初始化图片下载链接

    public RecyclerViewFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pic_flow, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rec_view);
        initRecyclerView(recyclerView);
        initLayoutManager(recyclerView);
        initRecyclerViewAdapter(recyclerView);
        return view;
    }

    private void initRecyclerView(final RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);//设置为固定大小
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置列表项增加，删除，重排序的动画，默认为平移
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP)
                    if (recyclerView.getScrollY() <= 0)//下拉加载时
                        Toast.makeText(context, "模拟下拉加载", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void initLayoutManager(RecyclerView recyclerView) {
        layoutManager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);//设置列数与排列方式
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//防止图片显示跳行
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initRecyclerViewAdapter(RecyclerView recyclerView) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(picUrls, context, getWindowWidth(), SPAN_COUNT);
        recyclerView.setAdapter(adapter);//设置适配器
    }

    /**
     * 获取当前手机屏幕宽度
     * @return
     */
    public int getWindowWidth() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
}
