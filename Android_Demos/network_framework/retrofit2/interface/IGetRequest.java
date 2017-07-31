package com.example.a61555.okhttpdemo;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by 61555 on 2017/7/27.
 */

public interface IGetRequest {
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法
    //key:91137935A5A11951D9FFDAAE1A4423CC
    @POST("api/dictionary.php?type=json&w=work&key=91137935A5A11951D9FFDAAE1A4423CC")
    Call<Translation> getCall();
}
