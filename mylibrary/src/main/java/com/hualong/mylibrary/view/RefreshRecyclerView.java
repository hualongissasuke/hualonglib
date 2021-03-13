package com.hualong.mylibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hualong.mylibrary.R;
import com.hualong.mylibrary.callback.RefreshCallback;
import com.hualong.mylibrary.databinding.LayoutRefreshRecylerBinding;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

/**
 * 带刷新的RecycoerView
 */
public class RefreshRecyclerView extends LinearLayout {
    private Context mContext;
    private LayoutRefreshRecylerBinding mBinding;

    public RefreshRecyclerView(Context context) {
        this(context,null);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        final RefreshCallback callback = (RefreshCallback) mContext;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.layout_refresh_recyler
            ,this,false);
        this.addView(mBinding.getRoot());

        // mBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        //设置 Header 为 贝塞尔雷达 样式
        mBinding.refreshLayout.setRefreshHeader(new MaterialHeader(mContext));
//设置 Footer 为 球脉冲 样式
        mBinding.refreshLayout.setRefreshFooter(new BallPulseFooter(mContext).setSpinnerStyle(SpinnerStyle.Scale));
        // mBinding.refreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                callback.onRefrsh();
            }
        });

        mBinding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                callback.onLoadMore();
            }
        });
    }

    public RecyclerView getRecyclerView(){
        return mBinding.recyclerView;
    }
}
