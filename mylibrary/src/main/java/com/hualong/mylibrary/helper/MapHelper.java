package com.hualong.mylibrary.helper;

import android.text.TextUtils;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hualong.mylibrary.util.Console;

import java.util.HashMap;
import java.util.Map;

public class MapHelper {
    private static MapHelper instance;
    private static Map<String, String> mParams;
    private static final String USER_NAME_KEY = "user_name";
    private static final String TOKEN_NAME_KEY = "token_name";
    private static String user_name;
    private static String token_name;
    private static String user_value;
    private static String token_value;

    private MapHelper() {
    }

    public static MapHelper getInstance() {
        if (instance == null) {
            synchronized (MapHelper.class) {
                if (instance == null)
                    instance = new MapHelper();
            }
        }
        mParams = new HashMap<>();
        return instance;
    }

    public void setCofig(String login_name, String login_value, String token_name, String token_value) {
        SPUtils.getInstance().put(USER_NAME_KEY, login_name);
        SPUtils.getInstance().put(login_name, login_value);
        SPUtils.getInstance().put(TOKEN_NAME_KEY, token_name);
        SPUtils.getInstance().put(token_name, token_value);
    }


    /**
     * 请求添加用户信息
     */
    private MapHelper hasToken() {
        if (valid()) {
            mParams.put(user_name, user_value);
            mParams.put(token_name, token_value);
        } else
            Console.loge("---暂无token---");
        return instance;
    }

    /**
     * 清除userId和token
     */
    public void clearCofig() {
        if (!TextUtils.isEmpty(user_name))
            SPUtils.getInstance().remove(user_name);
        if (!TextUtils.isEmpty(token_name))
            SPUtils.getInstance().remove(token_name);
        SPUtils.getInstance().remove(USER_NAME_KEY);
        SPUtils.getInstance().remove(TOKEN_NAME_KEY);
        this.user_name = "";
        this.token_name = "";
        this.user_value = "";
        this.token_value = "";
    }

    /**
     * 验证是否有userId和token
     */
    private boolean valid() {
        if (ObjectUtils.isEmpty(this.user_value) || ObjectUtils.isEmpty(this.token_value))
            if (SPUtils.getInstance().contains(USER_NAME_KEY) && SPUtils.getInstance().contains(TOKEN_NAME_KEY)) {
                this.user_name = SPUtils.getInstance().getString(USER_NAME_KEY);
                this.user_value = SPUtils.getInstance().getString(this.user_name);
                this.token_name = SPUtils.getInstance().getString(TOKEN_NAME_KEY);
                this.token_value = SPUtils.getInstance().getString(this.token_name);
            } else
                return false;
        return true;
    }

    public MapHelper put(String key, Object value) {
        mParams.put(key, String.valueOf(value));
        return instance;
    }

    public Map<String, String> build() {
        hasToken();
        return mParams;
    }

}
