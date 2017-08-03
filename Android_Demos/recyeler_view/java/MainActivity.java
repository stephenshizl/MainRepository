package com.example.a61555.picassoframeworkdemo;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private ImageFragment imgFragment;
    private RecyclerViewFragment recViewFragment;
    private android.app.FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imgFragment = new ImageFragment();
        recViewFragment = new RecyclerViewFragment(this);
        getFragmentManager().beginTransaction()
                .add(R.id.main_container, recViewFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage(EventMessage message) {
        switch (message.optional) {
            case 1:
                imgFragment = new ImageFragment(this, message.url);
                getFragmentManager().beginTransaction()
                        .hide(recViewFragment)
                        .add(R.id.main_container, imgFragment)
                        .commit();
                break;
            case 2:
                getFragmentManager().beginTransaction()
                        .remove(imgFragment)
                        .show(recViewFragment)
                        .commit();
                break;
            default: break;
        }
    }
}
