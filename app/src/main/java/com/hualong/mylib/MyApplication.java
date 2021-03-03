package com.hualong.mylib;

import android.app.Application;

import com.hualong.mylibrary.api.ApiCofig;
import com.hualong.mylibrary.dialog.LoadingDialog;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        ApiCofig.init(this,"https://api-hmugo-web.itheima.net/api/public/v1/goods/detail");
    }
}
