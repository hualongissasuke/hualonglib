package com.hualong.mylibrary.view;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.flexbox.FlexboxLayout;

public class FlexLayout extends FlexboxLayout {
    public FlexLayout(Context context) {
        this(context,null);
    }

    public FlexLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlexLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
