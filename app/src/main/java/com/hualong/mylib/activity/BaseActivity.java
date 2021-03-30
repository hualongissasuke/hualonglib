package com.hualong.mylib.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.hualong.mylibrary.adapter.RecyclerBindAdapter;
import com.hualong.mylibrary.api.ResCallback;
import com.hualong.mylibrary.base.BasicActivity;
import com.hualong.mylibrary.bean.ResponseBean;
import com.hualong.mylibrary.bind.BindViewHolder;
import com.hualong.mylibrary.callback.RecyclerAdapterCallback;
import com.hualong.mylibrary.util.BToast;
import com.hualong.mylibrary.util.Console;
import com.hualong.mylibrary.util.JsonUtil;

public abstract   class BaseActivity extends BasicActivity implements  ResCallback{
    protected RecyclerBindAdapter recyclerBindAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void success(String res, int actionCode) {
        ResponseBean responseBean = JsonUtil.getObjectFromJson(res,ResponseBean.class);
        if(responseBean.success){
            requestSuccess(JsonUtil.toJson(responseBean.data),actionCode);
        }else
            toast(responseBean.msg);
    }

    @Override
    public void fail(String error, int actionCode) {
        requestFail(error,actionCode);
        if(!NetworkUtils.isConnected() || !NetworkUtils.isAvailable()){
            falseToast("网络异常");
        }
    }





    /** 请求成功 */
    protected abstract void requestSuccess(String res, int actionCode);
    /** 请求失败 */
    protected abstract void requestFail(String error, int actionCode);
}
