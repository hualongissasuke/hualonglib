package com.hualong.mylibrary.callback;

import androidx.databinding.ViewDataBinding;

public interface DialogCallback<K extends ViewDataBinding,V extends Object> {
    void initView(K dialogBinding);
    boolean onSure(V data);
}
