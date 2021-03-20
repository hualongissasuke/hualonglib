package com.hualong.mylibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.hualong.mylibrary.R;

public class MKeyboarView extends LinearLayout {
    public MKeyboarView(Context context) {
        this(context,null);
    }

    public MKeyboarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MKeyboarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = View.inflate(context, R.layout.view_keyboard,this);
        this.addView(view);
    }
}
