package com.hualong.mylibrary.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hualong.mylibrary.R;

public class LoadingDialog extends Dialog {
    public LoadingDialog( Context context) {
        super(context, R.style.LoadProgressDialog);
    }


}
