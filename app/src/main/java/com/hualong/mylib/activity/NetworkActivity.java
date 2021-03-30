package com.hualong.mylib.activity;

import android.os.Bundle;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.hualong.mylib.R;
import com.hualong.mylib.bean.NetworkBean;
import com.hualong.mylibrary.api.Api;
import com.hualong.mylibrary.bind.BindViewHolder;
import com.hualong.mylibrary.helper.MapHelper;
import com.hualong.mylibrary.util.Console;
import com.hualong.mylibrary.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NetworkActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
    }



    /** 登录 */
    public void login(View view){
        MapHelper.getInstance().setCofig("loginId","999","token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTY2MzU3NDYzNjQsInBheWxvYWQiOiJ7XCJpZFwiOlwiOTk5XCIsXCJsb2dpbk5hbWVcIjpcImNhbmdndWFuMVwiLFwicGFzc3dvcmRcIjpcIjEyMzQ1NlwifSJ9.VrvbWdNkgsxTzoiaO6ki2kOZStzLPs4AMV2EaAVt5ms");
        request();
    }

    /** 退出登录 */
    public void outLogin(View view){
        MapHelper.getInstance().clearCofig();
        request();
    }

    public void postRequest(View view){
        request();
    }

    private void request(){
        Api.post(this,"wms/deviceStatus",MapHelper.getInstance().build(),mLoadingDialog,1001);
    }

    @Override
    protected void requestSuccess(String res, int actionCode) {
        String arr = JsonUtil.getArrayForRes(res,"data");
        List<NetworkBean> mNetworkItems = JsonUtil.getListFromJson(arr,NetworkBean.class);
        toast(mNetworkItems.get(new Random().nextInt(mNetworkItems.size())).device_id);
        Console.logd(this,mNetworkItems.get(0));
    }

    @Override
    protected void requestFail(String error, int actionCode) {

    }


}
