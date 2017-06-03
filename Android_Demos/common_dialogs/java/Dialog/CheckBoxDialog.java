package com.example.a61555.dialogdemo.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by 61555 on 2017/6/3.
 */

public class CheckBoxDialog extends MyDialog{

    public CheckBoxDialog(Context context) {
        super(context);
    }

    @Override
    public void init() {
        final ArrayList<Integer> checkedIdList = new ArrayList <Integer>();
        final CharSequence[] items = {"item0","item1","itme2","item3","itme4","item5","item6"};
        final boolean[] checkboxBooleanArray = new boolean[]{false, false, false, false, false, false, false};
        AlertDialog.Builder builder = getBuilder();

        checkedIdList.clear();
        builder.setIcon(null);
        builder.setTitle("Title");
        builder.setMultiChoiceItems(items, checkboxBooleanArray,
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton, boolean isChecked) {
                        if(isChecked) {
                            checkedIdList.add(whichButton);
                        }else {
                            checkedIdList.remove(whichButton);
                        }

                    }
                });

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String str = "";
                int size = checkedIdList.size();
                for (int i = 0 ;i < size; i++) {
                    str+= items[checkedIdList.get(i)] + ", ";
                }
                Toast.makeText(getContext(), "你选择了:"+str, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
