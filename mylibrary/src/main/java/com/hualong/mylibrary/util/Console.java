package com.hualong.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.hualong.mylibrary.base.BasicActivity;

public class Console {

    public static final String LOG_NAME = "hualong_";
    private static final String LOG_DIVIDE = " ！ ";
    private static final String LOG_VERBOSE = LOG_NAME + "verbose";
    private static final String LOG_DEBUG = LOG_NAME + "debug";
    private static final String LOG_INFO = LOG_NAME + "info";
    private static final String LOG_WARN = LOG_NAME + "warn";
    private static final String LOG_ERROR = LOG_NAME + "error";

    //网络
    public static void logv(Object... objects) {
        Log.v(LOG_VERBOSE, log(objects));
    }

    //调试
    public static void logd(Object... objects) {
        Log.d(LOG_DEBUG, log(objects));
    }

    //信息
    public static void logi(Object... objects) {
        Log.i(LOG_INFO, log(objects));
    }

    //警告
    public static void logw(Object... objects) {
        Log.w(LOG_WARN, log(objects));
    }

    //报错
    public static void loge(Object... objects) {

        Log.e(LOG_ERROR, log(objects));
    }

    private static String log(Object... objects) {
        LogUtils.getConfig().setBorderSwitch(true);
        // LogUtils.getConfig().setSingleTagSwitch(true);
        try {
            if (!ObjectUtils.isEmpty(objects)) {
                StringBuffer sb = new StringBuffer();
                for (Object obj : objects) {
                    if (ObjectUtils.isEmpty(obj)) {
                        sb.append("null").append(LOG_DIVIDE);
                        continue;
                    }
                    if (obj instanceof Integer || obj instanceof String) {
                        sb.append(obj).append(LOG_DIVIDE);
                    } else {
                        try {
                            sb.append(obj.getClass().getSimpleName()).append(" ==> ").append(JsonUtil.toJson(obj)).append(LOG_DIVIDE);
                        } catch (Exception e) {
                            if (e.getMessage().contains("lang.reflect.Method"))
                                sb.append(obj.toString()).append(LOG_DIVIDE);
                            else
                                sb.append(e.getMessage()).append(LOG_DIVIDE);
                        }

                    }

                }
                return sb.toString().trim().substring(0, sb.length() - 1);
            } else
                return "objects is null or the size is zero";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
