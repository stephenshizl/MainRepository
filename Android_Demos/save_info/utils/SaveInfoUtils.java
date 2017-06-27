package com.example.a61555.sharedpreferencedemo;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 61555 on 2017/6/22.
 * 该类为工具类，使用该类来将信息加密之后存储
 * Method 1：存储在File中，利用saveUserInfo2File()和getUserInfoFromFile 才操作存储的数据
 *
 * Method 2：Android平台给我们提供了一个SharedPreferences类，它是一个轻量级的存储类，
 *          特别适合用于保存软件配置参数。使用SharedPreferences保存数据，其背后是用xml文件存放数据，
 *          文件存放在/data/data/<package name>/shared_prefs目录下.
 *          我们利用saveUserInfo2Pref()和getUserInfoFormPref()来获取存储的数据
 * Method 3：将数据存储在SQLite Database中，saveUserInfo2DB和getUserInfoFromDB操作
 */

public class SaveInfoUtils {

    private final String SAVE_FILE_NAME = "userinfo.txt";
    private final String SAVE_PREF_NAME = "userInfo";
    private FileUtils fileUtils;
    private SharedPreferenceUtils sharedPreferenceUtils;
    private Context context;

    public SaveInfoUtils(Context context) {
        this.context = context;
        fileUtils = new FileUtils(context);
        sharedPreferenceUtils = new SharedPreferenceUtils(context);
    }

    /**
     * 初始化SharedPreference
     *   Context.MODE_PRIVATE：为默认操作模式,代表该文件是私有数据,只能被应用本身或者共享了user id的应用访问,在该模式下,写入的内容会覆盖原文件的内容
         Context.MODE_APPEND：模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件.
         Context.MODE_WORLD_READABLE：表示当前文件可以被其他应用读取，已为过时方法.
         Context.MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入，已为过时方法.
         Context.MODE_ENABLE_WRITE_AHEAD_LOGGING：
         Context.MODE_NO_LOCALIZED_COLLATORS：
     */
    /*private void prefInit() {
        sharedPreferences = context.getSharedPreferences(SAVE_PREF_NAME, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }*/
    /**
     * 保存信息到本地文件File中
     * @param user
     * @return
     */
    public boolean saveUserInfo2File(User user) {
        return fileUtils.saveUserInfo2File(user, SAVE_FILE_NAME);
    }
    /**
     * 读取信息从本地文件File中
     * @return
     */
    public User getUserInfoFromFile() {
        return fileUtils.getUserInfoFromFile(SAVE_FILE_NAME);
    }
    /**
     * 使用SharedPreference将信息保存到 xml配置文件
     * @param user
     * @return
     */
    public boolean saveUserInfo2Pref(User user){
        return sharedPreferenceUtils.saveInfo(user, SAVE_PREF_NAME);
    }
    /**
     * 读取信息从xml配置文件中
     * @return
     */
    public User getUserInfoFromPref() {
        return sharedPreferenceUtils.getInfo(SAVE_PREF_NAME);
    }

    /**
     * 将数据保存到SQLite数据库中
     * @param user
     */
    public void saveUserInfo2DB(User user) {
        DBManager manager = new DBManager(context);
        List userList = new ArrayList();
        userList.add(user);
        manager.add(userList);
        Toast.makeText(context, "已添加测试数据", Toast.LENGTH_SHORT).show();
    }

    /**
     * 从SQLite中获取数据
     * @return
     */
    public User getUserInfoFromDB() {
        DBManager manager = new DBManager(context);
        List userList = manager.query();
        return (User) userList.get(0);//只取第一个
    }
}
