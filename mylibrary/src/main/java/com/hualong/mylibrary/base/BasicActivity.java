package com.hualong.mylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hualong.mylibrary.util.BToast;
import com.hualong.mylibrary.util.Console;


public class BasicActivity extends FragmentActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
    }

    //吐司
    public void toast(String msg){
        BToast.showText(this,msg,true);
    }

    public void showToast(String msg){
        ToastUtils.showShort(msg);
    }

    //跳转
    public void navigateTo(Class cls){
        ActivityUtils.startActivity(cls);
    }

    public void logd(Object ...msg){
        Console.logd(msg);
    }
}
