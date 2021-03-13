package com.hualong.mylib.bind;

import android.widget.TextView;


import androidx.databinding.BindingAdapter;

import com.hualong.mylibrary.util.Console;

public class TextBindAdapter {

    @BindingAdapter("hint")
    public static void setHintStr(TextView tv,String content){
        Console.logd("hint-content",content);
        tv.setHint(content);
    }
}
