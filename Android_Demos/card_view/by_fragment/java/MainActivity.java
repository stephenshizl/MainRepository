package com.example.a61555.cardviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 *  需引入
 *  com.android.support:cardview-v7:x.x.x
    com.android.support:recyclerview-v7:x.x.x
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.container, CardViewListFragment.getInstance())
                .commit();
    }
}