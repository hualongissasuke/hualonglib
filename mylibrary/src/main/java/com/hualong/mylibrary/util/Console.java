package com.hualong.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ObjectUtils;
import com.hualong.mylibrary.base.BasicActivity;

public class Console {

    public static final String LOG_NAME = "hualong_";
    private static final String LOG_DIVIDE = " ； ";
    private static final String LOG_DEBUG = LOG_NAME + "debug";
    private static final String LOG_INFO = LOG_NAME + "info";
    private static final String LOG_WARN = LOG_NAME + "warn";
    private static final String LOG_ERROR = LOG_NAME + "error";

    //调试
    public static void logd(Object... objects) {
        Log.d(LOG_DEBUG, log(objects));
    }

    //信息
    public static void logi(Object... objects) {
        Log.i(LOG_INFO, log(objects));
    }

    //网络
    public static void logw(Object... objects) {
        Log.w(LOG_WARN, log(objects));
    }

    //报错
    public static void loge(Object... objects) {
        Log.e(LOG_ERROR, log(objects));
    }

    private static String log(Object... objects) {
        try {
            if (!ObjectUtils.isEmpty(objects)) {
                StringBuffer sb = new StringBuffer();
                for (Object obj : objects) {
                    if(ObjectUtils.isEmpty(obj)){
                        sb.append("obj is null").append(LOG_DIVIDE);
                        continue;
                    }
                    if (obj instanceof Integer || obj instanceof String) {
                        sb.append(obj).append(LOG_DIVIDE);
                    } else {
                        String name = obj.getClass().getSimpleName();
                        if(obj instanceof View){
                            sb.append((((Activity) (((View) obj).getContext())).getLocalClassName()))
                                    .append(" 的 ")
                                    .append(name).append(LOG_DIVIDE);
                        } else {
                            sb.append(name).append(" ==> ").append(JsonUtil.toJson(obj)).append(LOG_DIVIDE);
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
