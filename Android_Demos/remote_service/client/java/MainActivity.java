package com.example.a61555.remoteservicedemo_client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a61555.remoteservicedemo_server.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface mBinder = null;
    private TextView showText = null;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            showText = (TextView) findViewById(R.id.show_text);
            //使用 IMyAidlInterface.Stub.asInterface() 方法获取服务器端返回的 IBinder 对象
            //将 IBinder 对象传换成了 mBinder 接口对象
            mBinder = IMyAidlInterface.Stub.asInterface(service);
            try {
                String message = mBinder.myMethod();//调用在 Server 端中实现的方法
                int threadId = mBinder.basicTypes(1, 1223, true, 12.2f, 12.3, "Hello World");
                showText.setText(message+";"+threadId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bindService(View view) {

        //通过Intent指定服务端的服务名称和所在包，与远程Service进行绑定
        //参数与服务器端的action要一致,即"服务器包名.aidl接口文件名"
        Intent intent = new Intent("com.example.a61555.remoteservicedemo_server.IMyAidlInterface");
        //Android5.0后无法只通过隐式Intent绑定远程Service
        //需要通过setPackage()方法指定包名
        intent.setPackage("com.example.a61555.remoteservicedemo_server");

        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void unbindService(View v) {
        unbindService(connection);
        onDestroy();
    }
}
