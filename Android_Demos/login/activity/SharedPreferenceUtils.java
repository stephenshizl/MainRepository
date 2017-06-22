package com.example.a61555.sharedpreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 61555 on 2017/6/22.
 */

public class SharedPreferenceUtils {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferenceUtils(Context context, String perfName) {
        sharedPreferences = context.getSharedPreferences(perfName, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean saveInfo(String username, String password) {
        editor.putString("username", username);
        editor.putString("password", password);
        return editor.commit();
    }

    public Map getInfo(){
        Map info = new HashMap();
        info.put("username", sharedPreferences.getString("username", ""));
        info.put("password", sharedPreferences.getString("password", ""));
        return info;
    }
}
