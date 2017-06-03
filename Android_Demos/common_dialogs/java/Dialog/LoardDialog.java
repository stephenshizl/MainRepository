package com.example.a61555.dialogdemo.Dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/6/3.
 */

public class LoardDialog extends MyDialog {

    private ProgressDialog progressDialog;

    public LoardDialog(Context context) {
        super(context);
    }

    @Override
    public void init() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Title");
        progressDialog.setMessage("Message");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setButton("Button1", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了Button1", Toast.LENGTH_SHORT).show();
            }
        });
        progressDialog.setButton2("Button2", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "你点击了Button2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }
}
