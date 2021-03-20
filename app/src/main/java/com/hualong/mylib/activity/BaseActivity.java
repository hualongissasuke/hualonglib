package com.hualong.mylib.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.hualong.mylibrary.util.BToast;

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // protected abstract void test();

    protected void toast(Object msg){

        BToast.showText(this,String.valueOf(msg),true);
    }
}
