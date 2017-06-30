package com.example.a61555.contentproviderdemo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by 61555 on 2017/6/29.
 */

public class ContactsOperateUtils {

    private Context context = null;
    private Cursor cursor = null;

    public ContactsOperateUtils(Context context){
        this.context = context;
    }

    public Cursor getContacts() {
        //
        ContentResolver resolver = context.getContentResolver();
        //Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;//content://com.android.contacts/data/phones URI被封装为常量
        Uri uri = Uri.parse("content://com.android.contacts/data/phones");//访问联系人的uir
        //Uri uri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;//访问邮箱的uir
        //查询联系人数据
        cursor = resolver.query(uri, null, null, null, null);
        return cursor;
    }

    public void insertContact() {
        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentValues values = new ContentValues();
        long contactId = ContentUris.parseId(resolver.insert(uri,values));
        //信息Uri
        uri = Uri.parse("content://com.android.contacts/data");
        //添加姓名
        values.put("raw_contact_id",contactId);
        values.put("mimetype","vnd.android.cursor.item/name");
        values.put("data2", "Test Name");
        resolver.insert(uri, values);

        //添加电话
        values.put("raw_contact_id",contactId);
        values.put("mimetype","vnd.android.cursor.item/phone_v2");
        values.put("data2", "2");
        values.put("data1","13333333333");
        resolver.insert(uri,values);
    }

    public Cursor getEmails(){
        //查询raw_contacts表获得联系人的id
        ContentResolver resolver = context.getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        //查询联系人数据
        cursor = resolver.query(uri, null, null, null, null);
        return cursor;
        /*while(cursor.moveToNext())
        {
            //获取联系人姓名,手机号码
            String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //String cEmail = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
            System.out.println("姓名:" + cName);
            System.out.println("号码:" + cNum);
            //System.out.println("邮箱："+cEmail);
            System.out.println("======================");
        }
        cursor.close();*/
    }
}
