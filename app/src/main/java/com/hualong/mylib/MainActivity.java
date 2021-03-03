package com.hualong.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hualong.mylibrary.api.Api;
import com.hualong.mylibrary.api.ApiCofig;
import com.hualong.mylibrary.api.StringCallback;
import com.hualong.mylibrary.dialog.LoadingDialog;
import com.hualong.mylibrary.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements StringCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Map<String,String> map = new HashMap<>();
        // map.put("goods_id","47866");
        // Api.config("",map).postRequest(this,new LoadingDialog(this),1001);
    }

    @Override
    public void success(String res, int actionCode) {

    }

    @Override
    public void fail(String error, int actionCode) {

    }
}
