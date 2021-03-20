package com.hualong.mylibrary.util;

import android.content.Context;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static void showTimePicker(Context context,Calendar endDate,OnTimeSelectListener list){
        new TimePickerBuilder(context,list).setRangDate(null,endDate).build().show();
    }

    public static String date2String(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date.getTime());
    }
}
