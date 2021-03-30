package com.hualong.mylibrary.bind;

import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.hualong.mylibrary.util.Console;

public class BindViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private T mBinding;
    public BindViewHolder(T binding) {
        super(binding.getRoot());
        Console.loge("layout文件可能没binding layout");
        this.mBinding = binding;
    }

    public T getBinding(){
        return mBinding;
    }
}
