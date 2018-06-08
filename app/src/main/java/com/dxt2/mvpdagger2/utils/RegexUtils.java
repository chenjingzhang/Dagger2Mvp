package com.dxt2.mvpdagger2.utils;

import android.util.Patterns;

import java.util.regex.Matcher;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public class RegexUtils {
    private static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,32}$";
    private static final String REGEX_USER_NAME_ENGLISH = "^[\\w]{6,17}$";
    private static final String REGEX_USER_NAME_CHINESE = "[\\u4E00-\\u9FA5]*";

    //英文字母 数字 或下划线 6--18位
    public static boolean isValidUserName(String userName) {
        return userName.matches(REGEX_USER_NAME_ENGLISH) || userName.matches(REGEX_USER_NAME_CHINESE);
    }
   //英文字母或数字，6--32位
    public static boolean isValidPassword(String password) {
        return password.matches(REGEX_PASSWORD);
    }

    public static String matchShareUrl(String text){
        Matcher matcher = Patterns.WEB_URL.matcher(text);
        if (matcher.find()){
            return matcher.group();
        }
        return "";
    }
}
