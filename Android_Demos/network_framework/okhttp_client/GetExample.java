package com.example.a61555.okhttpdemo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 61555 on 2017/7/24.
 */

public class GetExample {

    private OkHttpClient client = new OkHttpClient();
    /*
        同步请求
        一直等待http请求, 直到返回了响应. 在这之间会阻塞进程, 所以通过get不能在Android的主线程中执行, 否则会报错
     */
    public void syncGet(String url) throws IOException {
        //构造请求
        /*
        Request:
          HttpUrl url;----请求地址
          String method;----请求方法
          Headers headers;----请求头
          RequestBody body;----请求体
          Object tag;----标识
         */
        Request request = new Request.Builder()
                .url(url)
                .build();
        //发送get请求
        /*
        Response：
          Request request;----持有的请求
          Protocol protocol;----请求协议
          int code;----响应码
          String message;----描述信息
          Handshake handshake;----通信协议验证信息
          Headers headers;----响应头
          ResponseBody body;----响应体
          Response networkResponse;
          Response cacheResponse;
          Response priorResponse;
          long sentRequestAtMillis;----发送请求时间
          long receivedResponseAtMillis;----接受响应时间
         */
        try (Response response = client.newCall(request).execute()){
            //打印响应头
            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            //打印响应体
            System.out.print(response.body().string());//返回请求的数据
        }
    }
    /*
        异步请求
        在另外的工作线程中执行http请求, 请求时不会阻塞当前的线程, 所以可以在Android主线程中使用
     */
    public void asyncGet(String url) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            //出错时调用的方法
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            //获得响应时调用的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //打印响应头
                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                //打印响应体
                System.out.print(response.body().string());//返回请求的数据
            }
        });
    }

    public static void main(String []args) throws IOException {
        GetExample example = new GetExample();
        //https://raw.github.com/square/okhttp/master/README.md
        //example.syncGet("http://publicobject.com/helloworld.txt");
        example.asyncGet("http://publicobject.com/helloworld.txt");
    }
}
