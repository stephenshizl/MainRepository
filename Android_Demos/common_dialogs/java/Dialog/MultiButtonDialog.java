package com.example.a61555.dialogdemo.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/6/3.
 */

public class MultiButtonDialog extends MyDialog{

    public MultiButtonDialog(Context context) {
        super(context);
    }

    @Override
    public void init() {
        AlertDialog.Builder builder = getBuilder();
        builder.setTitle("Title");
        builder.setMessage("Message");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了确认按钮", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("普通按钮", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了普通按钮", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了取消按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
