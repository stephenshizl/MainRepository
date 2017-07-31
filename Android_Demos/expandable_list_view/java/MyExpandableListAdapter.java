package com.example.a61555.expanablelistviewdemo;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

/**
 * Created by 61555 on 2017/7/31.
 * 自定义 ExpandableListAdapter
 */

public class MyExpandableListAdapter implements ExpandableListAdapter {

    private String[] groupStrs;
    private String[][] childStrs;
    private Context context;

    public MyExpandableListAdapter(String[] groupStrs, String[][] childStrs, Context context) {
        this.groupStrs = groupStrs;
        this.childStrs = childStrs;
        this.context = context;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }
    //获取分组数
    @Override
    public int getGroupCount() {
        return groupStrs.length;
    }
    //获取指定分组子选项个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return childStrs[groupPosition].length;
    }
    //获取指定分组数据
    @Override
    public Object getGroup(int groupPosition) {
        return groupStrs[groupPosition];
    }
    //获取指定分组子选项数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childStrs[groupPosition][childPosition];
    }
    //获取指定分组唯一ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    //获取指定分组子选项唯一ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    //分组和子选项是否持有稳定的ID
    @Override
    public boolean hasStableIds() {
        return true;
    }
    //获取显示的指定分组视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        TextView textView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_expand_group, parent, false);
            textView = (TextView) convertView.findViewById(R.id.label_expand_group);
            convertView.setTag(textView);
        } else {
            textView = (TextView) convertView.getTag();
        }
        textView.setText(getGroup(groupPosition).toString());
        return convertView;
    }
    //获取显示的指定分组子选项视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_expand_child, parent, false);
            textView = (TextView) convertView.findViewById(R.id.label_expand_child);
            convertView.setTag(textView);
        } else {
            textView = (TextView) convertView.getTag();
        }
        textView.setText(getChild(groupPosition, childPosition).toString());
        return convertView;
    }
    //指定位置子选项是否可选中、可点击
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    //所有的元素是否可用
    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return childId;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return groupId;
    }
}
