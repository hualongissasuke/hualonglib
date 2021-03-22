package com.hualong.mylibrary.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.hualong.mylibrary.R;
import com.hualong.mylibrary.callback.DialogCallback;
import com.hualong.mylibrary.util.Console;

/**
 * hualong
 * 2021-03-18
 * 导入layoutId即可使用的Dialog
 * @param <T>
 */
public class BaseDialog<T extends ViewDataBinding> extends Dialog implements View.OnClickListener {
    private int resId;
    private Context mContext;
    private T dialogBinding;

    public T getViewBinding(){
        return dialogBinding;
    }

    private DialogCallback dialogCallback;

    public BaseDialog setDialogCallback(DialogCallback dialogCallback){
        this.dialogCallback = dialogCallback;
        return this;
    }

    public BaseDialog(Context context){
        this(context,0);
    }

    public BaseDialog(Context context, int resId) {
        super(context, R.style.Dialog_Common);
        this.mContext = context;
        this.resId = resId;
        if(mContext instanceof DialogCallback)
            this.dialogCallback = (DialogCallback) mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(resId == 0) return;
        dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),resId,null,false);

        setContentView(dialogBinding.getRoot());
        if(dialogCallback != null)
            dialogCallback.initView(dialogBinding);

        try{
            // dialogBinding.getRoot().findViewById(R.id.tv_err).setOnClickListener(this);
            // dialogBinding.getRoot().findViewById(R.id.tv_cancel).setOnClickListener(this);
            // dialogBinding.getRoot().findViewById(R.id.tv_sure).setOnClickListener(this);
        }catch (Exception e){
            Console.loge(this,e.getMessage());
        }
    }

    /**
     * 须在onShow()之后调用
     * 获取View
     */
     public View getView(int viewId){
        return  dialogBinding.getRoot().findViewById(viewId);
    }

    /**
     * 须在onShow()之后调用
     * 获取View
     */
    public void setDialogTitle(String title){
        // ((TextView)dialogBinding.getRoot().findViewById(R.id.tv_title)).setText(title);
    }

    @Override
    public void onClick(View view) {
        try{
            switch (view.getId()){
                // case R.id.tv_err:
                // case R.id.tv_cancel:
                //     dismiss();
                //     break;
                // case R.id.tv_sure:
                //     if(dialogCallback == null || dialogCallback.onSure(null))
                //         dismiss();
                //     break;
            }
        }catch (Exception e){
            Console.loge(this,e.getMessage());
        }
    }
}
