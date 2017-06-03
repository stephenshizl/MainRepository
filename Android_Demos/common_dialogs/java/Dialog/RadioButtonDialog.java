package com.example.a61555.dialogdemo.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/6/3.
 */

public class RadioButtonDialog extends MyDialog {

    private int singleChoiceID = -1;

    public RadioButtonDialog(Context context) {
        super(context);
    }

    @Override
    public void init() {
        final CharSequence[] items = {"item0","item1","itme2","item3","itme4","item5","item6"};
        AlertDialog.Builder builder = getBuilder();
        builder.setIcon(null);
        builder.setTitle("Title");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //点击事件
                singleChoiceID = whichButton;
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(singleChoiceID > 0) {
                    Toast.makeText(getContext(), "你选择了item"+singleChoiceID, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
