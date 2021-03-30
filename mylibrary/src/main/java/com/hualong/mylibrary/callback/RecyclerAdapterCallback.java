package com.hualong.mylibrary.callback;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ViewDataBinding;

import com.hualong.mylibrary.bind.BindViewHolder;
import com.hualong.mylibrary.adapter.RecyclerBindAdapter;

public interface RecyclerAdapterCallback<K extends ViewDataBinding,V extends Object> {
    void onBindViewHolder(K recyclerItemBinding,int position,V item);
    void getAdapter(RecyclerBindAdapter adapter);
}
