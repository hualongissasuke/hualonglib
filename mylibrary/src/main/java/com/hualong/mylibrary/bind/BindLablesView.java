package com.hualong.mylibrary.bind;

import android.graphics.drawable.Drawable;

import androidx.databinding.BindingAdapter;

import com.hualong.mylibrary.util.Console;
import com.hualong.mylibrary.view.LablesView;

public class BindLablesView {

    @BindingAdapter(value={"strokeWidth","strokeColor"},requireAll = false)
    public static void setStroke(LablesView lablesView, int strokeWidth, Object storkeColor){
        lablesView.setStroke(strokeWidth,storkeColor);
    }

    @BindingAdapter("itemBackgroundColor")
    public static void setItemBackgroundColor(LablesView lablesView,  Object itemBackgroundColor){
        lablesView.setItemBackgroundColor(itemBackgroundColor);
    }

    @BindingAdapter("radius")
    public static void setCornerRadius(LablesView lablesView,  double radius){
        lablesView.setCornerRadius((float) radius);
    }

    @BindingAdapter("selectType")
    public static void setSelectType(LablesView lablesView,  int selectType){
        lablesView.setSelectType(selectType == 1?LablesView.SINGLE:LablesView.MULTIPLE);
    }


    @BindingAdapter(value={"itemHeight","itemWidth","margin","textColor","selectBackground","selectTextColor",
            "selectBackgroundColor","selectStrokeColor","flex","layoutId"},requireAll = false)
    public static void setAttribute(LablesView lablesView,  double itemHeight, double itemWidth,
                                    float[] margin, Object textColor, Drawable selectBackground,Object selectTextColor,
                                    Object selectBackgroundColor,Object selectStrokeColor,double flex,
                                    int layoutId){
        Console.logd("setAttribute-layoutId",layoutId);
        lablesView.setAttribute((float) itemWidth, (float) itemHeight,margin,textColor,selectBackground,selectTextColor,
                selectBackgroundColor,selectStrokeColor, (float) flex,layoutId);
    }


}
