package com.hualong.mylib.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.hualong.mylib.R;
import com.hualong.mylibrary.base.BasicActivity;
import com.hualong.mylibrary.callback.AddressCallback;
import com.hualong.mylibrary.helper.AddressPickerHelper;
import com.hualong.mylibrary.util.TimeUtil;

import java.util.Calendar;
import java.util.Date;

public class PickerActivity extends BasicActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this,R.layout.activity_picker);
    }

    public void showTime(View viwe){
        TimeUtil.showTimePicker(this, Calendar.getInstance(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                toast(TimeUtil.date2String(date));
            }
        });
    }


    public void showAddress(View view){
        AddressPickerHelper.show().setAddressCallback(new AddressCallback() {
            @Override
            public void onResult(String province, String city, String area) {
                toast(String.format("%1$s-%2$s-%3$s",province,city,area));
            }
        });
    }
}
