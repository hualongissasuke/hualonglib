package com.hualong.mylibrary.api;

import android.content.Context;

import com.blankj.utilcode.util.ObjectUtils;
import com.google.gson.Gson;
import com.hualong.mylibrary.dialog.LoadingDialog;
import com.hualong.mylibrary.util.Console;
import com.hualong.mylibrary.util.SPHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

public class Api {
    private static Api api;
    private static ApiOptions mOptions;
    private static LoadingDialog mDialog;


    private Api() {}

    public static Api create(ApiOptions options) {
       if(api == null){
           synchronized (Api.class){
               if(api == null){
                   api = new Api();
               }
           }
       }
       mOptions = options;
       return api;
    }

    /**
     *  ApiOptions请求
     */
    public void request(){
        if(!ObjectUtils.isEmpty(mOptions))
            if(mOptions.method.equals("GET"))
                get();
            else
                post();
    }

    /**
     * ApiOptions_GET请求
     */
    public void get(){
        get(mOptions.getContext(),mOptions.url,mOptions.data,mOptions.dialog,mOptions.actionCode);
    }

    /**
     * ApiOptions_POST请求
     */
    public void post(){
        post(mOptions.getContext(),mOptions.url,mOptions.data,mOptions.dialog,mOptions.actionCode);
    }

    public void postBody(){
        postBody(mOptions.getContext(),mOptions.url,mOptions.data,mOptions.dialog,mOptions.actionCode);
    }

    /**
     * get
     */
    public static void get(Context context, final String url,  Map<String,String> params, final LoadingDialog dialog,
                           final int actionCode) {
        showLoading(dialog);

        final ResCallback mCallback = (ResCallback) context;
        OkHttpUtils.get()
                .url(ApiCofig.getBaseUrl()+url)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Api.onError(url,e,actionCode,mCallback);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Api.onResponse(response,actionCode,mCallback);
                    }
                });
    }

    /**
     * 共同步骤
     * @return
     */
    private static ResCallback commonSteps(LoadingDialog dialog,Map<String,String> params, Context context){
        showLoading(dialog);

        return  (ResCallback) context;
    }

    public static void postBody(Context context,final String url, Map<String,String> params,final LoadingDialog dialog,
                                final int actionCode){
        showLoading(dialog);
        final ResCallback mCallback = (ResCallback) context;
        OkHttpUtils
                .postString()
                .url(ApiCofig.getBaseUrl() + url)
                .content(new Gson().toJson(params))
                .mediaType(MediaType.parse("application/json"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Api.onError(url,e,actionCode,mCallback);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Api.onResponse(response,actionCode,mCallback);
                    }
                });


    }

    /**
     * post请求
     */
    public static void post(Context context, final String url, final Map<String,String> params, final LoadingDialog dialog,
                            final int actionCode) {
        showLoading(dialog);

        final ResCallback mCallback = (ResCallback) context;
        OkHttpUtils.post()
                .url(ApiCofig.getBaseUrl() + url)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                       Console.logv(ApiCofig.getBaseUrl() + url,params.toString(),"    ==>", e.getMessage());
                       Api.onError(url,e,actionCode,mCallback);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Console.logv(ApiCofig.getBaseUrl() + url,params.toString(),"    ==>",response);
                        Api.onResponse(response,actionCode,mCallback);
                    }
                });
    }

    /**
     * 请求失败
     */
    private static void onError( String url, Exception e,int actionCode,ResCallback mCallback){
        dismissLoading();
        mCallback.fail(e.getMessage(), actionCode);
    }

    /**
     * 请求成功
     */
    private static void onResponse(String response,int actionCode,ResCallback mCallback){
        dismissLoading();
        mCallback.success(response, actionCode);
    }

    /**
     * 显示加载框
     * @param loadingDialog
     */
    private static void showLoading(LoadingDialog loadingDialog){
        if(loadingDialog != null){
            mDialog = loadingDialog;
            mDialog.show();
        }
    }

    /**
     * 隐藏加载框
     */
    private static void dismissLoading(){
        if(mDialog != null){
            mDialog.dismiss();
        }
    }


}
