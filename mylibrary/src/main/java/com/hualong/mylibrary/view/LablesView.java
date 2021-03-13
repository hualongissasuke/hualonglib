package com.hualong.mylibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class LablesView extends LinearLayout {
    // private LIST

    public LablesView(Context context) {
        this(context,null);
    }

    public LablesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LablesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
