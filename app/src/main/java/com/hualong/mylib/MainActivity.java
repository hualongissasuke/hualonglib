package com.hualong.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hualong.mylibrary.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.d("测试");
    }
}
