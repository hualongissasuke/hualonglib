package com.hualong.mylibrary.helper;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.hualong.mylibrary.util.KeyboardUtil;

public class KeyboarHelper {
    public static KeyboardUtil setCofig(Activity activity, EditText licese){
        final KeyboardUtil keyboardUtil = new KeyboardUtil(activity,licese);
        keyboardUtil.hideSoftInputMethod();
        licese.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                keyboardUtil.showKeyboard();
                return false;
            }
        });
        return keyboardUtil;
    }
}
