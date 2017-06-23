package com.example.a61555.sharedpreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 61555 on 2017/6/22.
 * 该类为工具类，使用该类来将信息加密之后存储
 * Method 1：存储在File中，利用saveUserInfo2File()和getUserInfoFromFile 才操作存储的数据
 *
 * Method 2：Android平台给我们提供了一个SharedPreferences类，它是一个轻量级的存储类，
 *          特别适合用于保存软件配置参数。使用SharedPreferences保存数据，其背后是用xml文件存放数据，
 *          文件存放在/data/data/<package name>/shared_prefs目录下.
 *          我们利用saveUserInfo2Pref()和getUserInfoFormPref()来获取存储的数据
 */

public class SaveInfoUtils {

    private final String SAVE_FILE_NAME = "userinfo.txt";
    private final String SAVE_PREF_NAME = "userInfo";
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public SaveInfoUtils(Context context) {
        this.context = context;
        prefInit();
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
    private void prefInit() {
        sharedPreferences = context.getSharedPreferences(SAVE_PREF_NAME, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    /**
     * 保存信息到本地文件File中
     * @param username
     * @param password
     * @return
     */
    public boolean saveUserInfo2File(String username, String password) {
        try {
            // 使用Android上下问获取当前项目的路径
            File file = new File(context.getFilesDir(), SAVE_FILE_NAME);
            // 创建输出流对象
            FileOutputStream fos = new FileOutputStream(file);
            //使用加密算法
            String username_en = EncryptionUtils.encrypt(username);
            String password_en = EncryptionUtils.encrypt(password);
            // 向文件中写入信息
            fos.write((username_en + "##" + password_en).getBytes());
            // 关闭输出流对象
            fos.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    /**
     * 读取信息从本地文件File中
     * @return
     */
    public Map<String, String> getUserInfoFromFile() {
        // 创建FIle对象
        File file = new File(context.getFilesDir(), SAVE_FILE_NAME);
        // 创建Map集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (file.isFile()){
                // 创建FileInputStream对象
                FileInputStream fis = new FileInputStream(file);
                // 创建BufferedReader对象
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                // 获取文件中的内容
                String content = br.readLine();
                // 使用保存信息使用的##将内容分割出来
                String[] contents = content.split("##");
                //使用解码算法
                String username_de = EncryptionUtils.decrypt(contents[0]);
                String password_de = EncryptionUtils.decrypt(contents[1]);
                // 保存到map集合中
                map.put("username", username_de);
                map.put("password", password_de);
                // 关闭流对象
                fis.close();
                br.close();
            } else {
                map.put("username", "");
                map.put("password", "");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return map;
        }
    }
    /**
     * 使用SharedPreference将信息
     * 保存到 xml配置文件
     * @param username
     * @param password
     * @return
     */
    public boolean saveUserInfo2Pref(String username, String password){
        editor.putString("username", EncryptionUtils.encrypt(username));
        editor.putString("password", EncryptionUtils.encrypt(password));
        return editor.commit();//提交事务
    }
    /**
     * 读取信息从xml配置文件中
     * @return
     */
    public Map<String, String> getUserInfoFromPref() {
        Map infoMap = new HashMap();
        infoMap.put("username", EncryptionUtils.decrypt(sharedPreferences.getString("username", "")));
        infoMap.put("password", EncryptionUtils.decrypt(sharedPreferences.getString("password", "")));
        return infoMap;
    }
}
