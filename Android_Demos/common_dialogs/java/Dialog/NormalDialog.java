package com.example.a61555.dialogdemo.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/6/2.
 */

public class NormalDialog extends MyDialog{

    public NormalDialog(Context context) {
        super(context);
    }

    @Override
    public void init() {
        AlertDialog.Builder builder = getBuilder();
        builder.setIcon(null);
        builder.setTitle("Title");
        builder.setMessage("Message");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了确认", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
