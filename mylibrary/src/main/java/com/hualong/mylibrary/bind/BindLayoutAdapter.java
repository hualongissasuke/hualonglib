package com.hualong.mylibrary.bind;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;

public class BindLayoutAdapter {
    @BindingAdapter(value = {"itemWeight", "padding", "paddingLeft", "paddingRight"}, requireAll = false)
    public static void setLinearLayoutParams(LinearLayout ll, float itemWeight, float padding, float paddingLeft, float paddingRight) {
        ViewGroup.LayoutParams lp = ll.getLayoutParams();
        float parentWidth = ScreenUtils.getScreenWidth();
        if (padding > 0) {
            parentWidth = parentWidth - ConvertUtils.dp2px(padding) * 2;
        } else {
            if (paddingLeft > 0)
                parentWidth = parentWidth - ConvertUtils.dp2px(paddingLeft);
            if (paddingRight > 0)
                parentWidth = parentWidth - ConvertUtils.dp2px(paddingRight);
        }
        lp.width = (int) (parentWidth / itemWeight);
        ll.setLayoutParams(lp);
    }
}
