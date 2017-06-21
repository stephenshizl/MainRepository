package com.example.a61555.contextmenudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用GirdView实现上下文菜单ContextMenu
 * 重写onCreateContextMenu和onContextItemSelected
 * 在Activity注册上下文菜单调用registerForContextMenu方法时调用onCreateContextMenu
 *                      在点击上下文菜单中的Item时调用onContextItemSelected
 * 在onCreateContextMenu方法中对ContextMenu进行构造，有动态和静态构造两种方法
 */
public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private MenuBuilder menuBuilder;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> textList;
    private ContextMenu contextMenu;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> optionalList;
    private String[] files = new String[]{"File1", "File2", "File3", "File4","File5", "File6", "File7", "File8"};
    private final int ITEM_COPY_ID = R.id.item_copy;
    private final int ITEM_EDIT_ID = R.id.item_edit;
    private final int ITEM_DELETE_ID = R.id.item_delete;
    private final int ITEM_SEARCH_ID = R.id.item_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 当执行长按上下文菜单时，大多数情况会调用registerForContextMenu(View) 函数
     * 和重写执行onCreateContextMenu(ContextMenu, View, ContextMenu.ContextMenuInfo)函数。
     * 因为要创建一个上下文菜单，你必须重写这个活动的上下文回调函数onCreateContextMenu()
     * 并且通过registerForContextMenu(View) 为其注册上下文菜单。
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("Optional");//设置菜单标题
        menu.setHeaderIcon(R.drawable.ic_launcher_round);//设置菜单图标
        //设置菜单显示内容
        /*menu.add(0, ITEM_COPY_ID, 1, "Copy");//以动态的方式添加Menu
        menu.add(0, ITEM_EDIT_ID, 1, "Edit");
        menu.add(0, ITEM_DELETE_ID, 1, "Delete");
        menu.add(0, ITEM_SEARCH_ID, 1, "Search");*/
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);//以映射的方式添加Menu
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    /**
     * 重写点击上下文菜单时触发事件的方法
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case ITEM_COPY_ID:
                Toast.makeText(this, "You Click Copy Item", Toast.LENGTH_SHORT).show();break;
            case ITEM_EDIT_ID:
                Toast.makeText(this, "You Click Edit Item", Toast.LENGTH_SHORT).show();break;
            case ITEM_DELETE_ID:
                Toast.makeText(this, "You Click Delete Item", Toast.LENGTH_SHORT).show();break;
            case ITEM_SEARCH_ID:
                Toast.makeText(this, "You Click Search Item", Toast.LENGTH_SHORT).show();break;
        }
        return super.onContextItemSelected(item);
    }

    private void init() {
        //获取GridView实例
        gridView = (GridView) findViewById(R.id.grid_view);
        //构造和添加适配器
        simpleAdapter = new SimpleAdapter(this, getList(), R.layout.custom_items, new String[]{"optional"}, new int[]{R.id.text_view});
        gridView.setAdapter(simpleAdapter);
        //注册上下文菜单,注册之后的View在被用户长按时，会弹出上下文菜单
        this.registerForContextMenu(gridView);
    }

    private List<Map<String, Object>> getList() {
        optionalList = new ArrayList<Map<String, Object>>();
        for(int i=0;i<files.length;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("optional", files[i]);
            optionalList.add(map);
        }
        return optionalList;
    }
}
