package com.hualong.mylibrary.bind;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.hualong.mylibrary.R;
import com.hualong.mylibrary.util.Console;
import com.hualong.mylibrary.view.RadiusLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class BindRadiusLayout {

    @BindingAdapter(value ={"textString","btnString","textSize","textColor"},requireAll = false)
    public static void setParams(RadiusLayout radiusLayout,String textString,String btnString,int textSize,  String textColor){
        Context mContext = radiusLayout.getContext();
        Button btn = new Button(mContext);
        btn.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
        TextView tv;
        if(!ObjectUtils.isEmpty(btnString))
            tv = btn;
        else
            tv  = new TextView(mContext);

        // R.color.black
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(mContext.getResources().getColor(R.color.white));
        tv.setText(textString);
        if(textSize != 0)
            tv.setTextSize(ConvertUtils.sp2px(textSize));
        if(!ObjectUtils.isEmpty(textColor)){
            tv.setTextColor(Color.parseColor(textColor));
        }
        radiusLayout.addView(tv,new ViewGroup.LayoutParams(MATCH_PARENT,MATCH_PARENT));
    }
}
