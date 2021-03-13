package com.hualong.mylibrary.bind;

import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BindViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private T mBinding;
    public BindViewHolder(T binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    public T getBinding(){
        return mBinding;
    }
}
