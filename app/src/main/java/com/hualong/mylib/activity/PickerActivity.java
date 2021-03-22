package com.hualong.mylib.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

// import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.hualong.mylib.R;
import com.hualong.mylibrary.base.BaseDialog;
import com.hualong.mylibrary.base.BasicActivity;
import com.hualong.mylibrary.callback.AddressCallback;
import com.hualong.mylibrary.helper.AddressPickerHelper;

public class PickerActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_picker);

        final Dialog dialog = new Dialog(this);

        TextView textView = new TextView(this);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        textView.setLayoutParams(lp);

        textView.setText("dsfafaf发发发");
        textView.setTextSize(70);
        dialog.setContentView(textView);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setType(WindowManager.LayoutParams.LAST_APPLICATION_WINDOW);
        dialog.show();

        final BaseDialog baseDialog = new BaseDialog(this);

        baseDialog.getWindow().setGravity(Gravity.BOTTOM);


      textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // baseDialog.show();
                // AddressPickerHelper.dialog = baseDialog;
                AddressPickerHelper.getInstance().onDialog(true)
                        .show().setAddressCallback(new AddressCallback() {
                    @Override
                    public void onResult(String province, String city, String area) {
                        toast(String.format("%1$s-%2$s-%3$s", province, city, area));
                    }
                });
            }
        });
    }

    public void showTime(View viwe) {
        // TimeUtil.showTimePicker(this, Calendar.getInstance(), new OnTimeSelectListener() {
        //     @Override
        //     public void onTimeSelect(Date date, View v) {
        //         toast(TimeUtil.date2String(date));
        //     }
        // });

        AddressPickerHelper.getInstance().onDialog(true)
                .show().setAddressCallback(new AddressCallback() {
            @Override
            public void onResult(String province, String city, String area) {
                toast(String.format("%1$s-%2$s-%3$s", province, city, area));
            }
        });

        // AddressPickerHelper.getInstance().onDialog(false)
        //         .show().setAddressCallback(new AddressCallback() {
        //     @Override
        //     public void onResult(String province, String city, String area) {
        //         toast(String.format("%1$s-%2$s-%3$s", province, city, area));
        //     }
        // });
    }


    public void showAddress(View view) {
        AddressPickerHelper.getInstance().show();
        // AddressPickerHelper.show().setAddressCallback(new AddressCallback() {
        //     @Override
        //     public void onResult(String province, String city, String area) {
        //         toast(String.format("%1$s-%2$s-%3$s", province, city, area));
        //     }
        // });
    }
}
