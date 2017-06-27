package com.example.a61555.sharedpreferencedemo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 61555 on 2017/6/27.
 */

public class FileUtils {

    private Context context;
    private File file;
    private FileInputStream fis;
    private BufferedReader br;
    private FileOutputStream fos;

    public FileUtils(Context context) {
        this.context = context;
    }

    public boolean saveUserInfo2File(User user, String fileName) {
        try {
            // 使用Android上下问获取当前项目的路径
            file = new File(context.getFilesDir(), fileName);
            // 创建输出流对象
            fos = new FileOutputStream(file);
            //使用加密算法
            String username_en = EncryptionUtils.encrypt(user.getName());
            String password_en = EncryptionUtils.encrypt(user.getPassword());
            // 向文件中写入信息
            fos.write((username_en + "##" + password_en).getBytes());

            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            // 关闭输出流对象
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public User getUserInfoFromFile(String SAVE_FILE_NAME) {
        // 创建FIle对象
        file = new File(context.getFilesDir(), SAVE_FILE_NAME);
        //
        User user = new User();
        try {
            if (file.isFile()){
                // 创建FileInputStream对象
                fis = new FileInputStream(file);
                // 创建BufferedReader对象
                br = new BufferedReader(new InputStreamReader(fis));
                // 获取文件中的内容
                String content = br.readLine();
                // 使用保存信息使用的##将内容分割出来
                String[] contents = content.split("##");
                //使用解码算法
                String username_de = EncryptionUtils.decrypt(contents[0]);
                String password_de = EncryptionUtils.decrypt(contents[1]);
                // 保存到map集合中
                user.setName(username_de);
                user.setPassword(password_de);
            } else {
                user.setName("");
                user.setPassword("");
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流对象
            try {
                fis.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return user;
        }
    }
}
