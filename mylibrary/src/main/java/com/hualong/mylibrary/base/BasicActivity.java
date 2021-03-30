package com.hualong.mylibrary.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hualong.mylibrary.dialog.LoadingDialog;
import com.hualong.mylibrary.util.BToast;
import com.hualong.mylibrary.util.Console;


public class BasicActivity extends AppCompatActivity {
    public LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new LoadingDialog(this);
    }

    //吐司
    public void toast(Object msg){
        BToast.showText(this,String.valueOf(msg),true);
    }

    //吐司
    public void falseToast(Object msg){
        BToast.showText(this,String.valueOf(msg),false);
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
