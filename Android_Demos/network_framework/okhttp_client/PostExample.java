package com.example.a61555.okhttpdemo;

import android.support.annotation.Nullable;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by 61555 on 2017/7/25.
 */

public class PostExample {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public OkHttpClient client = new OkHttpClient();
    /*
        发送json数据
     */
    public String postJson(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);//创建请求体
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        //发送请求
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();//返回请求的数据
        }
    }
    /*
        以io流的方式发送请求
     */
    public String postStream(String url) throws IOException {

        RequestBody requestBody = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return JSON;
            }
            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                //将数据写入io流中
            }
        };

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        //发送请求
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();//返回请求的数据
        }
    }
    /*
        提交文件
     */
    public String postFile(String url) throws IOException {
        File file = new File("");//获得文件实例

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSON, file))
                .build();

        //发送请求
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();//返回请求的数据
        }
    }
    /*
        提交表单数据
     */
    public String postForm(String url) throws IOException {
        //添加表单数据
        RequestBody formBody = new FormBody.Builder()
                .add("username", "jackson")
                .add("password", "pw")
                .add("age", "123")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //发送请求
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();//返回请求的数据
        }
    }
    /*

     */
    public String postMultipart(String url) throws IOException {
        //添加不同部分的数据
        RequestBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "TITLE")
                .addFormDataPart("img", "test.png", RequestBody.create(MediaType.parse("image/png"), new File("")))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();
        //发送请求
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();//返回请求的数据
        }
    }

    /*
        用Gson来解析接受到的json数据
     */
    public Gdata postJsonbyGson(String url) throws IOException {
        Gson gson = new Gson();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()){
            //将json数据转换成对应的类，需要引入Gson库
            return gson.fromJson(response.body().charStream(), Gdata.class);
        }
    }

    public static void main(String []args) throws IOException {
        PostExample example = new PostExample();
        System.out.println(example.postJson("http://www.g-cores.com/articles/26143", "{status:1}"));
    }

    static public class Gdata {
        public String msg;
    }
}