package com.hualong.mylibrary.util;

import android.graphics.Color;

public class ColorUtil {
    /**
     * 颜色字符串转int
     */
    public static int str2Int(Object obj) {
        if (obj instanceof String) {
            return Color.parseColor(String.valueOf(obj));
        }
        return (int) obj;
    }

    // 获取更深颜色
    public static int getDarkerColor(int color,int darker){
        if(darker == 0)
            darker = 1;
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv); // convert to hsv
        // make darker
        hsv[1] = hsv[1] + 0.1f * darker; // 饱和度更高
        hsv[2] = hsv[2] - 0.1f * darker; // 明度降低
        int darkerColor = Color.HSVToColor(hsv);
        return  darkerColor ;
    }
    // 获取更浅的颜色
    public static int getBrighterColor(int color,int brighter){
        if(brighter == 0)
            brighter = 1;
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv); // convert to hsv

        hsv[1] = hsv[1] - 0.1f * brighter; // less saturation
        hsv[2] = hsv[2] + 0.1f * brighter; // more brightness
        int darkerColor = Color.HSVToColor(hsv);
        return  darkerColor ;
    }
}
