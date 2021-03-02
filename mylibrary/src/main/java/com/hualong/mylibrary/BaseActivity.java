package com.hualong.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


public class BaseActivity extends Activity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
    }
}
