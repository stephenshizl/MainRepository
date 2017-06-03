package com.example.a61555.dialogdemo.Dialog;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by 61555 on 2017/6/3.
 */

public abstract class MyDialog {

    private AlertDialog.Builder builder;
    private Context context;

    public MyDialog(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
        init();
    }

    public abstract void init();

    public AlertDialog.Builder getBuilder() {
        return builder;
    }

    public Context getContext() {
        return context;
    }
}
