package com.example.a61555.sharedpreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 61555 on 2017/6/22.
 *   Context.MODE_PRIVATE：为默认操作模式,代表该文件是私有数据,只能被应用本身或者共享了user id的应用访问,在该模式下,写入的内容会覆盖原文件的内容
     Context.MODE_APPEND：模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件.
     Context.MODE_WORLD_READABLE：表示当前文件可以被其他应用读取，已为过时方法.
     Context.MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入，已为过时方法.
     Context.MODE_ENABLE_WRITE_AHEAD_LOGGING：
     Context.MODE_NO_LOCALIZED_COLLATORS：
 */

public class SharedPreferenceUtils {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SharedPreferenceUtils(Context context) {
        this.context = context;
    }

    public boolean saveInfo(User user, String perfName) {
        sharedPreferences = context.getSharedPreferences(perfName, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("username", user.getName());
        editor.putString("password", user.getPassword());
        return editor.commit();
    }

    public User getInfo(String perfName){
        sharedPreferences = context.getSharedPreferences(perfName, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        User user = new User();
        user.setName(sharedPreferences.getString("username", ""));
        user.setPassword(sharedPreferences.getString("password", ""));
        return user;
    }
}
