package com.hualong.mylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;


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
    private void navigateTo(Class cls){
        ActivityUtils.startActivity(cls);
    }
}
