package com.example.a61555.dialogdemo.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/6/3.
 */

public class ListDialog extends MyDialog {

    public ListDialog(Context context) {
        super(context);
    }

    @Override
    public void init() {
        final CharSequence[] items = {"item0","item1","itme2","item3","itme4","item5","item6"};
        AlertDialog.Builder builder = getBuilder();
        builder.setTitle("Title");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "你选择了"+items[which], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
