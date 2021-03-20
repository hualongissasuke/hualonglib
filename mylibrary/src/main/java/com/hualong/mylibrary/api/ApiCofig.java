package com.hualong.mylibrary.api;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

public class ApiCofig {
    private static ApiCofig instance = new ApiCofig();
    public static String BASE_URL = "";
    public Context mContext;

    // public static ApiCofig getInstance(){
    //     if(instance == null){
    //         synchronized (ApiCofig.class){
    //             if(instance == null){
    //                 instance = new ApiCofig();
    //             }
    //         }
    //     }
    //     return instance;
    // }

    public static String getBaseUrl(){
        return BASE_URL;
    }

    public static void init(Context context, String url){
        instance.mContext = context;
        BASE_URL = url;
    }

    private ApiCofig(){

    }
}
