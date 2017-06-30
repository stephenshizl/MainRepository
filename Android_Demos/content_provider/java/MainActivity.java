package com.example.a61555.contentproviderdemo;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * 利用ContentResolver访问ContentProvider操纵其他应用中的数据（Demo里是操纵联系人数据）
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_contacts = null;
    private Button btn_insert = null;
    private ContactsOperateUtils contactsOperateUtils = null;
    private final static int OP_QUERY = 1;
    private SimpleCursorAdapter simpleCursorAdapter = null;
    private ListView content_list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        content_list = (ListView) findViewById(R.id.content_list);
        contactsOperateUtils = new ContactsOperateUtils(this);

        btn_contacts = (Button) findViewById(R.id.get_contacts);
        btn_contacts.setOnClickListener(this);
        btn_insert = (Button) findViewById(R.id.insert_contact);
        btn_insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Cursor cursor = null;
        switch (v.getId()) {
            case R.id.get_contacts:
                cursor = contactsOperateUtils.getContacts();
                simpleCursorAdapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_list_item_2,
                        cursor, new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER},
                        new int[]{android.R.id.text1, android.R.id.text2}, OP_QUERY);
                break;
            case R.id.insert_contact:
                //插入新数据
                contactsOperateUtils.insertContact();
                //插入新数据后更新listView
                cursor = contactsOperateUtils.getContacts();
                simpleCursorAdapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_list_item_2,
                        cursor, new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER},
                        new int[]{android.R.id.text1, android.R.id.text2}, OP_QUERY);
                break;
            default:
                break;
        }
        content_list.setAdapter(simpleCursorAdapter);
    }
}
