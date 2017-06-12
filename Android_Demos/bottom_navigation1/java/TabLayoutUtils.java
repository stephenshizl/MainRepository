package com.example.a61555.bottomnavigationdemo1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 61555 on 2017/6/12.
 */

public class TabLayoutUtils {
    public static final int []mTabRes = new int[]{
            R.drawable.tab_home_selector,
            R.drawable.tab_discovery_selector,
            R.drawable.tab_attention_selector,
            R.drawable.tab_profile_selector};
    public static final int []mTabResPressed = new int[]{
            R.drawable.ic_tab_strip_icon_feed_selected,
            R.drawable.ic_tab_strip_icon_category_selected,
            R.drawable.ic_tab_strip_icon_pgc_selected,
            R.drawable.ic_tab_strip_icon_profile_selected};
    public static final String []mTabTitle = new String[]{"Home","Explore","Like","Me"};

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[]{
                HomeFragment.newInstance(from),
                DiscoveryFragment.newInstance(from),
                AttentionFragment.newInstance(from),
                ProfileFragment.newInstance(from)
        };
        return fragments;
    }
    /**
     * 构造自定义Tab View
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position){
        //映射布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content, null);
        //设置图标文件
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(mTabRes[position]);
        //设置标题
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }

}
