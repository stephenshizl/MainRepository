package com.example.a61555.okhttpdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 需要引入库 com.android.volley:volley:1.0.0
 * 启用权限 android.permission.INTERNET
 */
public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    RequestQueue queue;
    StringRequest stringRequest;
    final static String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.msg);
        /*
            不同方法创建请求队列
         */
        //queue = Volley.newRequestQueue(this);//方法一：创建请求队列

        //queue = getQueue();//方法二：获取带有缓存和network的请求队列
        //queue.start();//启动请求队列

        queue = SingletonQueue.getInstance(this).getRequestQueue();//通过单例模式获得请求队列实例
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (queue != null)
            queue.cancelAll(TAG);
    }

    public void send(View view) {
        String url ="http://www.g-cores.com/articles/26098";//请求地址
        //构造字符串请求
        stringRequest = new StringRequest(Request.Method.GET, url,
                //监听服务器的相应
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                },
                //监听服务器相应错误
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                });
        stringRequest.setTag(TAG);//设置标识
        queue.add(stringRequest);//将请求添加到请求队列
    }

    public void getJson(View view) {
        String jsonUrl = "http://www.w3school.com.cn/example/jquery/demo_ajax_json.js";
        queue.add(new JsonObjectRequest(Request.Method.GET, jsonUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mTextView.setText("Response is: "+ response.get("firstName").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
        }));
    }

    public void cancel(View view) {
        queue.cancelAll(TAG);//取消带有此标识的请求
    }
    /*
        获取带有缓存和network的请求队列
     */
    public RequestQueue getQueue() {
        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);//设置1MB缓存
        return new RequestQueue(cache, network);
    }
}

/**
 * 使用单例模式来创建实例
 */
class SingletonQueue {

    private static Context mcontext;
    private static SingletonQueue instance;
    private RequestQueue queue;

    private SingletonQueue(Context context) {
        mcontext = context;
    }

    public static synchronized SingletonQueue getInstance(Context context) {
        if (instance == null)
            instance = new SingletonQueue(context);
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (queue == null)
            queue = Volley.newRequestQueue(mcontext);
        return queue;
    }

    public void addRequest(Request request) {
        getRequestQueue().add(request);
    }
}
