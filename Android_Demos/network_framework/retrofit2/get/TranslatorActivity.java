package com.example.a61555.okhttpdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
    private String resultStr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translator_js);

        resultText = (TextView) findViewById(R.id.result);
        request();
    }

    public void request() {
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() {
                    @Override
                    public List<?> deserialize(JsonElement json,
                                               Type typeOfT,
                                               JsonDeserializationContext context) throws JsonParseException {
                        if (json.isJsonArray()) {
                            JsonArray array = json.getAsJsonArray();
                            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
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

        Call<Translation> call = request.getCall();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                System.out.println(response.isSuccessful());
                System.out.println(response.headers());
                System.out.println(response.body());
            }
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }
}
