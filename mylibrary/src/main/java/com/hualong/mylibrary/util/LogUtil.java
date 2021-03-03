package com.hualong.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class LogUtil {

    public static final String LOG_NAME ="hualong";

    public static void d(Activity activity, String ...msg){
        if(msg == null || msg.length == 0){
            Log.d(LOG_NAME,"msg is Empty");
        }else{
            StringBuffer sb = new StringBuffer();
            for(String str : msg){
                sb.append(str).append(",");
            }
            Log.d("hualong",activity.getLocalClassName()+","+sb.substring(0,sb.length()-1));
        }
    }

    public static void d(String ...msg){
        if(msg == null || msg.length == 0){
            Log.d(LOG_NAME,"msg is Empty");
        }else{
            StringBuffer sb = new StringBuffer();
            for(String str : msg){
                sb.append(str).append(",");
            }
            Log.d("hualong",sb.substring(0,sb.length()-1));
        }
    }

    public static void d(int ...num){
        StringBuffer sb = new StringBuffer();
        for(int n : num){
            sb.append(n).append(",");
        }
        Log.d("hualong",sb.substring(0,sb.length()-1));
    }

}
