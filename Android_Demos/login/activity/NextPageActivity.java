package com.example.a61555.debugapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by 61555 on 2017/6/22.
 */

public class NextPageActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView passwordTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_page);

        Intent intent = getIntent();
        Bundle info = intent.getBundleExtra("info");

        usernameTextView = (TextView) findViewById(R.id.username_show);
        passwordTextView = (TextView) findViewById(R.id.password_show);

        usernameTextView.setText((String) info.get("username"));
        passwordTextView.setText((String) info.get("password"));
    }
}
