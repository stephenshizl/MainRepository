package com.example.a61555.sqliteoperatedemo;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DBManager mgr;
    private ListView listView;
    private final static int OP_QUERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView_db);
        //初始化DBManager
        mgr = new DBManager(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //应用的最后一个Activity关闭时应释放DB
        mgr.closeDB();
    }
    /*
        添加模拟数据
     */
    public void add(View view) {
        ArrayList<User> users = new ArrayList<User>();

        User user1 = new User("Vorn", 17, "堤格尔维尔穆德·冯伦");
        User user2 = new User("Eleonora Viltaria", 17, "艾蕾欧诺拉·维尔塔利亚");
        User user3 = new User("Ludmilla Lurie", 16, "琉德米拉·露利叶");
        User user4 = new User("Sophia Obertas", 20, "苏菲亚·欧贝达斯");
        User user5 = new User("Alexandra Alshavin", 22, "亚莉莎德拉·阿尔夏芬");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        mgr.add(users);
        Toast.makeText(this, "已添加测试数据", Toast.LENGTH_SHORT).show();
        query(view);
    }
    /*
        在末尾追加数据
     */
    public void insert(View view) {
        User user = new User();
        user.name = "Gonn";
        user.age = 99;
        user.info = "简明现代魔法";
        mgr.insertUser(user);
        query(view);
    }
    /*
        更新指定数据
     */
    public void update(View view) {
        User user = new User();
        user.name = "Vorn";
        user.age = 20;
        mgr.updateAge(user);
    }
    /*
        删除末尾的数据
     */
    public void delete(View view) {
        User user = new User();
        mgr.deleteOldUser(user);
        query(view);//在删除完数据之后，刷新数据列表
    }
    /*
        将数据库中的所有数据显示到listView中
        使用SimpleAdapter 将数据list传入
     */
    public void query(View view) {
        List<User> users = mgr.query();
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (User user : users) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", user.name);
            map.put("info", user.age + " years old, " + user.info);//将年龄和信息合并为一行
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,
                new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }
    /*
        将数据库中的所有数据显示到listView中
        使用SimpleCursorAdapter 将数据cursor传入
     */
    public void queryTheCursor(View view) {
        Cursor c = mgr.queryTheCursor();
        CursorWrapper cursorWrapper = new CursorWrapper(c) {
            @Override
            public String getString(int columnIndex) {
                //重写指针里数据显示的格式，将简介前加上年龄
                if (getColumnName(columnIndex).equals("info")) {
                    int age = getInt(getColumnIndex("age"));
                    return age + " years old, " + super.getString(columnIndex);
                }
                return super.getString(columnIndex);
            }
        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                cursorWrapper, new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2}, OP_QUERY);
        ListView listView = (ListView) findViewById(R.id.listView_db);
        listView.setAdapter(adapter);
    }
}
