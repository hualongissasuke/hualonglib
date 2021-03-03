package com.hualong.mylibrary.util;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.hualong.mylibrary.dialog.LoadingDialog;
import com.hualong.mylibrary.listener.OkhttpListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

public class HttpUtil {

    //请求
    public static void request(final Context mContext,final String url, Map<String,String> map, final LoadingDialog dialog, final int returnCode){
        if(dialog!=null){
            dialog.show();
        }
        final OkhttpListener listener = (OkhttpListener) mContext;
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
                        if(!TextUtils.isEmpty(response))
                            listener.onSuccess(response,returnCode);
                        else
                            LogUtil.d("error:",url);
                    }
                });
    }

}
