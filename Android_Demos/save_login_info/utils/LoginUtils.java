package com.zhzy.utils;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/**
 * 保存和加载用户信息
 * Created by Luo Tao on 2017/5/17 0017.
 */
public class LoginUtils {

    private static final char[] UC_ENCRYPT_CHARS = {'M','D','X','U','P','I','B','E','J','C','T','N',
            'K','O','G','W','R','S','F','Y','V','L','Z','Q','A','H'};
    private static final char[] LC_ENCRYPT_CHARS = {'m','d','x','u','p','i','b','e','j','c','t','n',
            'k','o','g','w','r','s','f','y','v','l','z','q','a','h'};
    private static final int[] ENCRYPT_NUMBER = {4,5,1,6,3,2,0,8,9,7};
    private static char[] UC_DECRYPT_CHARS = new char[26];
    private static char[] LC_DECRYPT_CHARS = new char[26];
    private static int[] DECRYPT_NUMBER = new int[10];

    static {
        for (int i=0;i<26;i++){
            char b = UC_ENCRYPT_CHARS[i];
            UC_DECRYPT_CHARS[b-'A'] = b;
            b = LC_ENCRYPT_CHARS[i];
            LC_DECRYPT_CHARS[b-'a'] = b;
        }
        LogUtils.i("[LC_DECRYPT_CHARS]", "LC_DECRYPT_CHARS:"+new String(LC_DECRYPT_CHARS));
        for (int i=0;i<10;i++) {
            int n = ENCRYPT_NUMBER[i];
            DECRYPT_NUMBER[n] = n;
        }
    }

    /**
     * 保存用户登录信息
     * @param context
     * @param username
     * @param password
     * @return
     */
    public static boolean saveUserInfo(Context context, String username, String password) {
        try {
            // 使用Android上下问获取当前项目的路径
            File file = new File(context.getFilesDir(), "userinfo.txt");
            // 创建输出流对象
            FileOutputStream fos = new FileOutputStream(file);
            //使用加密算法
            String username_en = encrypt(username);
            String password_en = encrypt(password);
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
     * 读取用户登录信息
     * @param context
     * @return
     */
    public static Map<String, String> getUserInfo(Context context) {
        // 创建FIle对象
        File file = new File(context.getFilesDir(), "userinfo.txt");
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
                String username_de = decrypt(contents[0]);
                String password_de = decrypt(contents[1]);
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
     * 加密算法
     * @param b
     * @return
     */
    public static char encrypt(char b){
        if (b >= 'A' && b <= 'Z'){
            return UC_ENCRYPT_CHARS[b-'A'];
        }else if(b >= 'a' && b <= 'z'){
            return LC_ENCRYPT_CHARS[b-'a'];
        }else if (b >= '0' && b <= '9'){
            int b_int = Integer.parseInt(Character.toString(b));
            return Integer.toString(ENCRYPT_NUMBER[b_int]).charAt(0);
        } else {
            return b;
        }
    }
    public static String encrypt(String input){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(encrypt(input.charAt(i)));
        }
        return sb.toString();
    }
    /**
     * 解密算法
     * @param b
     * @return
     */
    public static char decrypt(char b){
        int index = 0;
        String b_str = Character.toString(b);
        if (b >= 'A' && b <= 'Z'){
            index = new String(UC_ENCRYPT_CHARS).indexOf(b_str);
            return UC_DECRYPT_CHARS[index];
        }else if (b >= 'a' && b <= 'z'){
            index = new String(LC_ENCRYPT_CHARS).indexOf(b_str);
            return LC_DECRYPT_CHARS[index];
        }else if (b >= '0' && b <= '9'){
            for (int i = 0; i < ENCRYPT_NUMBER.length; i++) {
                if (Integer.parseInt(b_str) == ENCRYPT_NUMBER[i]){
                    index = i;
                    break;
                }
            }
            return Integer.toString(DECRYPT_NUMBER[index]).charAt(0);
        } else {
            return b;
        }
    }
    public static String decrypt(String input){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(decrypt(input.charAt(i)));
        }
        return sb.toString();
    }
}
