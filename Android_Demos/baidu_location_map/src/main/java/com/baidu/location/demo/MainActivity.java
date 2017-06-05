package com.baidu.location.demo;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.baidulocationdemo.R;

import java.util.ArrayList;
import java.util.List;

/***
 * 本类代码同定位业务本身无关，负责现实列表
 * 
 * @author baidu
 *
 */
public class MainActivity extends Activity {

	private final int SDK_PERMISSION_REQUEST = 127;
	private ListView FunctionList;
	private String permissionInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_list);
		FunctionList = (ListView) findViewById(R.id.functionList);
		FunctionList
				.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));

		//获取手机权限
		getPersimmions();
	}
	/*
		Google在Android 6.0中引入了动态权限获取机制（Runtime Permission），使得Android的权限管理更加严格完善。
	动态权限获取要求开发者在调用涉及相关权限的代码时，使用系统接口来动态得申请相应权限。定位SDK涉及权限即在此范畴中。
	在未获取到定位权限情况下，定位SDK获取到的定位依据（基站、WiFi）均为空值，因此无法有效定位，定位服务会返回错误码167。
	Android 6.0对于动态权限机制的开启主要根据应用设定的targetSdkVersion，具体来讲：
			targetSdkVersion | 是否默认禁用敏感权限	 | 是否开启动态权限
			<23                 否               	否
			>=23               	是               	是
	 */
	@TargetApi(23)
	private void getPersimmions() {
		//判断Android版本
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			ArrayList<String> permissions = new ArrayList<String>();
			/**
			 * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
			 */
			// 定位精确位置
			if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
				permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
			}
			if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
				permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
			}
			/*
			 * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
			// 读写权限
			if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
				permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
			}
			// 读取电话状态权限
			if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
				permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
			}
			
			if (permissions.size() > 0) {
				requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
			}
		}
	}

	@TargetApi(23)
	private boolean addPermission(ArrayList<String> permissionsList, String permission) {
		if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请	
			if (shouldShowRequestPermissionRationale(permission)){
				return true;
			}else{
				permissionsList.add(permission);
				return false;
			}
				
		}else{
			return true;
		}
	}

	@TargetApi(23)
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		// TODO Auto-generated method stub
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		FunctionList.setOnItemClickListener(new OnItemClickListener() {

			@TargetApi(Build.VERSION_CODES.M)
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Class<?> TargetClass = null;
				switch (arg2) {
					case 0:
						TargetClass = LocationActivity.class;
						break;
					case 1:
						TargetClass = LocationOption.class;
						break;
					case 2:
						TargetClass = LocationAutoNotify.class;
						break;
					case 3:
						TargetClass = LocationFilter.class;
						break;
					case 4:
						TargetClass = NotifyActivity.class;
						break;
					case 5:
						TargetClass = IndoorLocationActivity.class;
						break;
					case 6:
						TargetClass = IsHotWifiActivity.class;
						break;
					case 7:
						TargetClass = QuestActivity.class;
						break;
					default:
						break;
				}

                if (isGpsOpen(getApplicationContext())) {//判断gps打开状态
                    if (isWifiOpen(getApplicationContext()) || isNetworkOpen(getApplicationContext())) {//判断网络打开状态
                        if (TargetClass != null) {
                            Intent intent = new Intent(MainActivity.this, TargetClass);
                            intent.putExtra("from", 0);
                            startActivity(intent);//画面跳转到对应页面
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "请打开网络", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "请打开GPS", Toast.LENGTH_SHORT).show();
                    //openGps(getApplicationContext());
                }
			}
		});
	}

	private List<String> getData() {

		List<String> data = new ArrayList<String>();
		data.add("基础定位功能");
		data.add("配置定位参数");
		data.add("自定义回调示例");
		data.add("连续定位示例");
		data.add("位置消息提醒");
		data.add("室内定位功能");
		data.add("判断移动热点");
		data.add("常见问题说明");
		

		return data;
	}
	/**判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
	 *@param context
	  *@return true表示开启
	 */
	public boolean isGpsOpen(final Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    /**判断2G/3G/4G网络是否开启
     *@param context
     *@return true表示开启
     */
    public boolean isNetworkOpen(final Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
    /**
     * 判断wifi网络打开状态
     * @param context
     * @return
     */
    private boolean isWifiOpen(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }
    /**
     * 强制打开GPS，Android 5.0后无用
     * @param context
     */
    public void openGps(Context context) {
        Intent gpsIntent = new Intent();
        gpsIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        gpsIntent.addCategory("android.intent.category.ALTERNATIVE");
        gpsIntent.setData(Uri.parse("custom:3"));
        try{
            PendingIntent.getBroadcast(context,0,gpsIntent,0).send();
        }catch(PendingIntent.CanceledException e){
            e.printStackTrace();
        }
    }

    /**
     * 强制打开2G/3G/4G网络
     * @param context
     */
    public void openNetwork(Context context) {
        //未实现
    }
}
