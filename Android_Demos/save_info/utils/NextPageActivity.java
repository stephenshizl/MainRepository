package com.example.a61555.sharedpreferencedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by 61555 on 2017/6/22.
 */

public class NextPageActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView passwordTextView;
    private SaveInfoUtils saveInfoUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_page);
        init();
    }

    public void init(){
        saveInfoUtils = new SaveInfoUtils(this);
        //User user = saveInfoUtils.getUserInfoFromFile();
        //User user = saveInfoUtils.getUserInfoFromPref();
        User user = saveInfoUtils.getUserInfoFromDB();

        usernameTextView = (TextView) findViewById(R.id.username_show);
        passwordTextView = (TextView) findViewById(R.id.password_show);

        usernameTextView.setText(user.getName());
        passwordTextView.setText(user.getPassword());
    }
}
