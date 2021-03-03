package com.hualong.mylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hualong.mylibrary.util.LogUtil;


public class BaseActivity extends Activity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
    }

    //吐司
    public void toast(String msg){
        ToastUtils.showShort(msg);
    }

    //跳转
    public void navigateTo(Class cls){
        ActivityUtils.startActivity(cls);
    }

    public void logd(String msg){
        LogUtil.d(this,msg);
    }
}
