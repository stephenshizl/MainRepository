package com.example.a61555.camerademo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static int OPEN_SYSTEM_CMAERA = 1;
    final static int OPEN_SYSTEM_ALBUM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_camera = (Button) findViewById(R.id.camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //构建隐式Intent
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //调用系统相机
                startActivityForResult(intent, OPEN_SYSTEM_CMAERA);
            }
        });
        Button btn_album = (Button) findViewById(R.id.album);
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //构建隐式Intent
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                //调用系统相册
                startActivityForResult(intent, OPEN_SYSTEM_ALBUM);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == OPEN_SYSTEM_CMAERA) {
            if (data != null){
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap((Bitmap) data.getExtras().getParcelable("data"));
                Toast.makeText(this, "Take a Photo", Toast.LENGTH_SHORT).show();
            }else{
                return;
            }
        }

        if(requestCode == OPEN_SYSTEM_ALBUM) {
            if (data != null){
                //用户从图库选择图片后会返回所选图片的Uri
                Uri uri = data.getData();;
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageURI(uri);
                Toast.makeText(this, "Selected a Pic", Toast.LENGTH_SHORT).show();
            }else{
                return;
            }
        }
    }
}
