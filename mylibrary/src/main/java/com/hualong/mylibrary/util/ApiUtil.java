package com.hualong.mylibrary.util;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.hualong.mylibrary.dialog.LoadingDialog;
import com.hualong.mylibrary.listener.NetworkListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

public class ApiUtil {

    //请求
    public static void request(final Context mContext, String url, Map<String,String> map, final LoadingDialog dialog, final int returnCode){
        if(dialog!=null){
            dialog.show();
        }
        final NetworkListener listener = (NetworkListener) mContext;
        OkHttpUtils
                .post()
                .url(url)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        if(dialog != null){
                            dialog.dismiss();
                        }
                        ToastUtils.showShort("网络连接错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if(dialog != null){
                            dialog.dismiss();
                        }
                        listener.success(response,returnCode);
                    }
                });
    }

}
