package com.example.a61555.dialogdemo.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a61555.dialogdemo.R;

/**
 * Created by 61555 on 2017/6/3.
 */

public class CustomDialog extends MyDialog {

    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    public void init() {
        AlertDialog.Builder builder = getBuilder();
        LayoutInflater factory = LayoutInflater.from(getContext());
        final View textEntryView = factory.inflate(R.layout.test, null);
        builder.setIcon(null);
        builder.setTitle("Title");
        builder.setView(textEntryView);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                EditText userName = (EditText) textEntryView.findViewById(R.id.etUserName);
                EditText password = (EditText) textEntryView.findViewById(R.id.etPassWord);
                Toast.makeText(getContext(), "username:"+userName.getText().toString()+";password:"+password.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
