package com.example.a61555.okhttpdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 61555 on 2017/7/27.
 */

public class TranslatorActivity extends AppCompatActivity {

    private TextView resultText;
    private EditText wordsText;
    private Handler handler;
    private Gson gson;
    private Retrofit retrofit;
    private IPostRequest request;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translator_js);

        resultText = (TextView) findViewById(R.id.result);
        wordsText = (EditText) findViewById(R.id.words);
        handler = new MyHandler(resultText);
        //自定义Json数据解析器
        gson = new GsonBuilder()
                //注册多层数据转换器（包括List模板类里的子类）
                //自定义JsonDeserializer，反序列化json数据，将json数据转换为class
                .registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() {
                    @Override
                    //反序列化
                    public List<?> deserialize(JsonElement json,
                                               Type typeOfT,
                                               JsonDeserializationContext context) throws JsonParseException {
                        //判断数据类型是否为list，如果是将返回该数据，反之返回空list
                        if (json.isJsonArray()) {
                            JsonArray array = json.getAsJsonArray();
                            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                            //由于无法直接返回，需要取出list里面的数据按对应类型，放入新的ArrayList内后返回
                            List list = new ArrayList<>();
                            for (int i = 0; i < array.size(); i++) {
                                JsonElement element = array.get(i);
                                Object item = context.deserialize(element, itemType);
                                list.add(item);
                            }
                            return list;
                        } else {
                            return Collections.EMPTY_LIST;
                        }
                    }
                })
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://dict-co.iciba.com")
                .addConverterFactory(GsonConverterFactory.create(gson))//添加数据解析器
                .build();
        request = retrofit.create(IPostRequest.class);
        //request();
    }

    public void translation(View view) {
        String requestType = "json";
        String key = "91137935A5A11951D9FFDAAE1A4423CC";
        String words = wordsText.getText().toString();

        Call<Translation> call = request.getCall(requestType, words, key);//发送请求
        call.enqueue(new Callback<Translation>() {//处理请求队列内的请求
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                //System.out.println(response.isSuccessful());
                //System.out.println(response.headers());
                //System.out.println(response.body().wordName);
                //System.out.println(response.body().exchange);
                //System.out.print(response.body());
                new Thread(new SendMessage(handler, 1, response.body().toString())).start();
            }
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                //System.out.println("连接失败");
                new Thread(new SendMessage(handler, -1, "")).start();
            }
        });
    }
}
