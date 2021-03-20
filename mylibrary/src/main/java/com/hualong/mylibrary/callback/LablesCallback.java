package com.hualong.mylibrary.callback;

import android.view.View;
import android.widget.TextView;

import androidx.databinding.ViewDataBinding;

import java.util.List;

public interface LablesCallback<K extends ViewDataBinding , T extends Object> {
    void callback(List<Integer> selects);
    void initView(TextView tv, Object obj, int position);
    void customView(K labelsBinding, T bean, int position);
}
