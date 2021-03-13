package com.hualong.mylibrary.bind;

import android.content.Context;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.blankj.utilcode.util.ObjectUtils;
import com.hualong.mylibrary.adapter.RecyclerBindAdapter;
import com.hualong.mylibrary.callback.RecyclerAdapterCallback;
import com.hualong.mylibrary.util.Console;
import com.hualong.mylibrary.view.RefreshRecyclerView;

public class BindRecyclerAdapter {

    @BindingAdapter("resId")
    public static void setParams(RecyclerView recyclerView,int resId){
        Context mContext = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        RecyclerBindAdapter bindAdapter =new RecyclerBindAdapter(mContext, resId);
        recyclerView.setAdapter(bindAdapter);

        RecyclerAdapterCallback callback = (RecyclerAdapterCallback) mContext;
        if(!ObjectUtils.isEmpty(callback)){
            callback.getAdapter(bindAdapter);
        }else
            Console.loge(mContext,"BindRecyclerAdapter.java","RecyclerAdapterCallback is null");
    }

    @BindingAdapter("resId")
    public static void setParams(RefreshRecyclerView refreshRecyclerView, int resId){
        RecyclerView recyclerView = refreshRecyclerView.getRecyclerView();
        Context mContext = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        RecyclerBindAdapter bindAdapter =new RecyclerBindAdapter(mContext, resId);
        recyclerView.setAdapter(bindAdapter);

        RecyclerAdapterCallback callback = (RecyclerAdapterCallback) mContext;
        if(!ObjectUtils.isEmpty(callback)){
            callback.getAdapter(bindAdapter);
        }else
            Console.loge(mContext,"BindRecyclerAdapter.java","RecyclerAdapterCallback is null");
    }
}
