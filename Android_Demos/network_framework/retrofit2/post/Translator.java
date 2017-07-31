package com.example.a61555.okhttpdemo;

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
 * Created by 61555 on 2017/7/28.
 */

public class Translator {

    public static void main(String[] args) {
        new Translator().request();
    }

    public void request() {
        //自定义Json数据解析器
        Gson gson = new GsonBuilder()
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dict-co.iciba.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        IGetRequest request = retrofit.create(IGetRequest.class);

        String key = "91137935A5A11951D9FFDAAE1A4423CC";
        String words = "join";
        String requestType = "json";
        Call<Translation> call = request.getCall(requestType, words, key);
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                //System.out.println(response.isSuccessful());
                //System.out.println(response.headers());
                //System.out.println(response.body().wordName);
                //System.out.println(response.body().exchange);
                System.out.print(response.body());
            }
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }
}
