package com.hualong.mylibrary.util;

public class StringUtil {
    public static String setCofig(String url,String loginId,String token){
        return String.format("%1$s?loginId=%2$s&token=%3$s",url,loginId,token);
    }
}
