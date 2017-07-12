package com.example.a61555.fragmentdemo;

/**
 * Created by 61555 on 2017/7/12.
 * Activity 和 Fragment 都实现此接口（不同方法），以此完成互传数据
 */

public interface IExchangeData {
    /*
       由 Fragment 来实现接口，以此接用于与 Activity 进行通信
     */
    public void sendData2Fragment(String data);
    /*
       由 Activity 来实现接口，以此接用于与 Fragment 进行通信
     */
    public void sendData2Activity(String data);
}
