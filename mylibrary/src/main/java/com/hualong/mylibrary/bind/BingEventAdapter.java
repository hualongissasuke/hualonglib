package com.hualong.mylibrary.bind;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.hualong.mylibrary.R;
import com.hualong.mylibrary.util.ColorUtil;
import com.hualong.mylibrary.util.Console;

public class BingEventAdapter {
    @BindingAdapter(value = {"pressColor","corner","corner_top_left","corner_top_right","corner_bottom_left","corner_bottom_right"}, requireAll = false)
    public static void setPressColor(Button btn, Object pressColor,double corner,double corner_top_left
            ,double corner_top_right,double corner_bottom_left,double corner_bottom_right) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            if(corner > 0) {
                corner_top_left = corner;
                corner_top_right = corner;
                corner_bottom_left = corner;
                corner_bottom_right = corner;
            }
            initCornerRipple(btn,pressColor, (float) corner_top_left,
                    (float) corner_top_right,(float) corner_bottom_left,(float) corner_bottom_right);
        }else{
            btn.setBackgroundColor(ColorUtil.str2Int(pressColor));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void initRipple(View view){

        int[][] stateList = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{android.R.attr.state_activated},
                new int[]{}
        };

        //深蓝
        int normalColor = Color.parseColor("#303F9F");
        //玫瑰红
        int pressedColor =  Color.parseColor("#FF4081");
        int[] stateColorList = new int[]{
                pressedColor,
                pressedColor,
                pressedColor,
                normalColor
        };
        ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);

        Drawable drawable = view.getContext().getResources().getDrawable(R.drawable.radius5);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.parseColor("#ffffff"));
        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, gd, null);
        view.setBackground(rippleDrawable);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void initCornerRipple(View view,Object colorObj,final float CORNER_TOP_LEFT, final float CORNER_TOP_RIGHT,
                                        final float CORNER_BOTTOM_LEFT,final float CORNER_BOTTOM_RIGHT){
        int color = ColorUtil.str2Int(colorObj);
        int[][] stateList = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{android.R.attr.state_activated},
                new int[]{}
        };


        //深蓝
        // int normalColor = Color.parseColor("#303F9F");
        int normalColor = ColorUtil.getDarkerColor(color,2);
        //玫瑰红
        // int pressedColor = Color.parseColor("#FF4081");
        int pressedColor = ColorUtil.getBrighterColor(color,20);
        int[] stateColorList = new int[]{
                pressedColor,
                pressedColor,
                pressedColor,
                normalColor
        };
        ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);

        //左上，右上，右下，左下
        float[] outRadius = new float[]{ConvertUtils.dp2px(CORNER_TOP_LEFT), ConvertUtils.dp2px(CORNER_TOP_LEFT),
                ConvertUtils.dp2px(CORNER_TOP_RIGHT), ConvertUtils.dp2px(CORNER_TOP_RIGHT),
                ConvertUtils.dp2px(CORNER_BOTTOM_RIGHT), ConvertUtils.dp2px(CORNER_BOTTOM_RIGHT),
                ConvertUtils.dp2px(CORNER_BOTTOM_LEFT), ConvertUtils.dp2px(CORNER_BOTTOM_LEFT)};
        RoundRectShape roundRectShape = new RoundRectShape(outRadius, null, null);
        ShapeDrawable maskDrawable = new ShapeDrawable();
        maskDrawable.setShape(roundRectShape);
        maskDrawable.getPaint().setColor(Color.parseColor("#000000"));
        maskDrawable.getPaint().setStyle(Paint.Style.FILL);

        ShapeDrawable contentDrawable = new ShapeDrawable();
        contentDrawable.setShape(roundRectShape);
        // contentDrawable.getPaint().setColor(Color.parseColor("#f7c653"));
        contentDrawable.getPaint().setColor(color);
        contentDrawable.getPaint().setStyle(Paint.Style.FILL);

        //contentDrawable实际是默认初始化时展示的；maskDrawable 控制了rippleDrawable的范围
        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, contentDrawable, maskDrawable);
        view.setBackground(rippleDrawable);
    }
}
