package com.hualong.mylibrary.api;

import android.content.Context;

import com.hualong.mylibrary.dialog.LoadingDialog;
import com.hualong.mylibrary.util.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class Api {
    public static Api api = new Api();
    public static String requestUrl;
    public static Map<String, String> mParams;
    public static OkHttpClient client;

    public Api() {

    }

    public static Api config(String url, Map<String, String> params) {
        client = new OkHttpClient.Builder().build();
        requestUrl = ApiCofig.getBaseUrl() + url;
        mParams = params;
        return api;
    }


    public void postRequest(Context context,final LoadingDialog dialog, final int actionCode) {
        if(dialog != null){
            dialog.show();
        }
        final StringCallback mCallback = (StringCallback) context;
        OkHttpUtils.get()
                .url(requestUrl)
                .params(mParams)
                .build()
                .execute(new com.zhy.http.okhttp.callback.StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if(dialog != null){
                            dialog.dismiss();
                        }
                        LogUtil.d("请求失败",requestUrl,mParams.toString());
                        mCallback.fail(e.getMessage(), actionCode);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.d(requestUrl);
                        LogUtil.d(response);
                        if(dialog != null){
                            dialog.dismiss();
                        }
                        mCallback.success(response, actionCode);
                    }
                });

    }


}
