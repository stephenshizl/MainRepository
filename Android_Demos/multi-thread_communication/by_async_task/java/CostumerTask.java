package com.example.a61555.asynctaskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 61555 on 2017/7/20.
 * 使用AsyncTask实现多线通信
 * AsyncTask是一个抽象泛型类
 * abstract class AsyncTask<Params, Progress, Result>
 *     Params：开始异步任务执行时传入的参数类型；
 *     Progress：异步任务执行过程中，返回下载进度值的类型；
 *     Result：异步任务执行完成后，返回的结果类型；
 * 如果不想有参数，可以使用 Void
 */

public class CostumerTask extends AsyncTask<String, Integer, Boolean> {

    private ProgressDialog progressDialog;
    private Context context;
    private final String mark = "CostumerTask";
    private final int PROGRESS_MAX = 100;

    public CostumerTask(Context context) {
        this.context = context;
    }

    /**
     * 初始化progress dialog
     */
    public void initProgressDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Load Data");
        progressDialog.setMessage("We Are Trying to Load...");
        progressDialog.setIndeterminate(false);//是否隐藏进度的数值
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(PROGRESS_MAX);
        progressDialog.setIcon(R.mipmap.ic_launcher_round);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancel(true);//将会调用onCancelled方法，将线程标记为cancelled
            }
        });
    }

    /**
     * 在后台任务开始执行之间调用，在主线程执行。用于进行一些界面上的初始化操作，比如显示一个进度条对话框等
     */
    @Override
    protected void onPreExecute() {
        Log.i(mark, "onPreExecute");
        initProgressDialog();
        progressDialog.show();
    }

    /**
     * 在子线程中运行，在这里去处理所有的耗时任务
     * @param params
     * @return
     */
    @Override
    protected Boolean doInBackground(String... params) {
        Log.i(mark, "doInBackground");
        int progress = 0;
        while ( progress < PROGRESS_MAX) {
            //判断task是否被取消
            if (isCancelled())
                return false;
            //模拟进度不断增加的情景
            progress += 5;
            publishProgress(progress);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 这个方法中可以对UI进行操作，在主线程中进行，利用参数中的数值就可以对界面元素进行相应的更新
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.i(mark, "onProgressUpdate");
        progressDialog.setProgress(values[0]);
    }

    /**
     * doInBackground 执行完return时调用，可以得到返回的数据，可以进行 UI 操作，在主线程中进行
     * @param aBoolean
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        Log.i(mark, "onPostExecute");
        progressDialog.dismiss();
        if (aBoolean)
            Toast.makeText(context, "Succeed!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
    }

    /**
     * 将任务标记为 Cancel 状态
     * @param aBoolean
     */
    @Override
    protected void onCancelled(Boolean aBoolean) {
        Log.i(mark, "onCancelled");
        if (!aBoolean)
            Toast.makeText(context, "Be Failed!", Toast.LENGTH_SHORT).show();
    }
}
