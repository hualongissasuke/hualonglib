package com.hualong.mylibrary.api;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

public class ApiCofig {
    public static ApiCofig instance;
    public String BASE_URL = "Empty";
    public Context mContext;

    public static ApiCofig getInstance(){
        if(instance == null){
            synchronized (ApiCofig.class){
                if(instance == null){
                    instance = new ApiCofig();
                }
            }
        }
        return instance;
    }

    public static String getBaseUrl(){
        return instance.BASE_URL;
    }

    public static void init(Context context, String url){
        getInstance();
        instance.mContext = context;
        instance.BASE_URL = url;
    }

    private ApiCofig(){

    }
}
