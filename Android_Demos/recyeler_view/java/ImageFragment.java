package com.example.a61555.picassoframeworkdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 61555 on 2017/8/2.
 */

public class ImageFragment extends Fragment {

    private ImageView imageView;
    private TextView textView;
    private Context context;
    private String url;

    public ImageFragment(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.img_view, container, false);
        imageView = (ImageView) view.findViewById(R.id.big_img_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventMessage("", 2));//点击图片，回到RecyclerView
            }
        });
        //加载图片
        Picasso.with(context).load(url)
                .placeholder(R.drawable.load)
                .into(imageView);

        textView = (TextView) view.findViewById(R.id.big_img_text);
        textView.setText(this.url);
        return view;
    }
}
