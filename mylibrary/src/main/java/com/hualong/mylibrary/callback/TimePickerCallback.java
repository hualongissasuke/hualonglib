package com.hualong.mylibrary.callback;

import android.view.View;

import java.util.Date;

public interface TimePickerCallback {
    void onTimeSelect(Date date, View view);
}
