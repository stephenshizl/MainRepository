package com.example.a61555.eventbusdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 61555 on 2017/7/11.
 */

public class MyFragment extends Fragment {

    private EditText etData = null;
    private Button btnSend = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment, container, false);//构造 Fragment
        etData = (EditText) view.findViewById(R.id.et_data_fg);
        btnSend = (Button) view.findViewById(R.id.btn_send_fg);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventMessage(etData.getText().toString()));
            }
        });
        return view;
    }
}
