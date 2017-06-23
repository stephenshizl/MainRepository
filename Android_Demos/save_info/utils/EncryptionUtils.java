package com.example.a61555.sharedpreferencedemo;

/**
 * Created by 61555 on 2017/6/23.
 * 凯撒密码加密算法
 */

public class EncryptionUtils {

    private static final char[] UC_ENCRYPT_CHARS = {'M','D','X','U','P','I','B','E','J','C','T','N',
            'K','O','G','W','R','S','F','Y','V','L','Z','Q','A','H'};
    private static final char[] LC_ENCRYPT_CHARS = {'m','d','x','u','p','i','b','e','j','c','t','n',
            'k','o','g','w','r','s','f','y','v','l','z','q','a','h'};
    private static final int[] ENCRYPT_NUMBER = {4,5,1,6,3,2,0,8,9,7};
    private static char[] UC_DECRYPT_CHARS = new char[26];
    private static char[] LC_DECRYPT_CHARS = new char[26];
    private static int[] DECRYPT_NUMBER = new int[10];

    //初始化操作
    static {
        for (int i=0;i<26;i++){
            char b = UC_ENCRYPT_CHARS[i];
            UC_DECRYPT_CHARS[b-'A'] = b;
            b = LC_ENCRYPT_CHARS[i];
            LC_DECRYPT_CHARS[b-'a'] = b;
        }

        for (int i=0;i<10;i++) {
            int n = ENCRYPT_NUMBER[i];
            DECRYPT_NUMBER[n] = n;
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
