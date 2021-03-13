package com.hualong.mylibrary.api;

import android.content.Context;

import com.hualong.mylibrary.dialog.LoadingDialog;
import com.hualong.mylibrary.util.SPUtil;

import java.util.HashMap;
import java.util.Map;

public class ApiOptions {
    private Context mContext;
    public String url;
    public Map<String,String> data = new HashMap<>();
    public String method = "POST";
    public LoadingDialog dialog;
    public int actionCode;
    public boolean isToken = true;

    public ApiOptions(Context context){
        this.mContext = context;
    }

    public ApiOptions(Context context,String url,Map<String,String> data, LoadingDialog dialog,int actionCode){
        this.mContext = context;
        this.url = url;
        this.dialog = dialog;
        this.actionCode =  actionCode;
        this.data = data;
    }

    public ApiOptions setOptions(String url,Map<String,String> data, LoadingDialog dialog,int actionCode){
        this.url =  url;
        this.data = data;
        this.dialog = dialog;
        this.actionCode = actionCode;
        return this;
    }

    public static String urlWithToken(String url){
        return String.format("%1$s?loginId=%2$s&token=%3$s",url,
                SPUtil.getInstance().getValue(SPUtil.LOGIN_ID,""),
                SPUtil.getInstance().getValue(SPUtil.TOKEN,""));
    }

    public void get(){
         this.method = "GET";
    }

    public Context getContext(){
        return mContext;
    }

    public void setContext(Context context){
        this.mContext = context;
    }

}
