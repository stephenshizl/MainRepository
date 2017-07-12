package com.example.a61555.fragmentdemo;

import android.app.Fragment;

/**
 * Created by 61555 on 2017/7/12.
 */

public class BaseFragment extends Fragment {

    /*
       由 Activity 来实现接口，以此接用于与 Fragment 进行通信
     */
    public interface IExchangeData {
        public void sendData2Activity(String data, String from);
    }
}
