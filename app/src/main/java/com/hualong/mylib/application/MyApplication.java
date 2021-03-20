package com.hualong.mylib.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.hualong.mylibrary.api.ApiCofig;
import com.hualong.mylibrary.dialog.LoadingDialog;
import com.hualong.mylibrary.util.SPUtil;

public class MyApplication extends Application {
    public static String BASE_URL = "http://52.130.72.242/hxwms-web/";
    public static String home ="wStockIn/toDealtWith";
    public static String add = "wPallet/add";
    public static String login = "999";
    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTU3MjMyMzYzNTUsInBheWxvYWQiOiJ7XCJpZFwiOlwiOTk5XCIsXCJsb2dpbk5hbWVcIjpcImNhbmdndWFuMVwiLFwicGFzc3dvcmRcIjpcIjEyMzQ1NlwifSJ9.SacOO7GMkBDTlMuBW1EVclO-9wNyAvX8dUOYmfzDzRk";

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        SPUtil.init(this);
        ApiCofig.init(this,BASE_URL);
    }
}
