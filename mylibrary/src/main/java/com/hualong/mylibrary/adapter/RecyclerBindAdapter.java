package com.hualong.mylibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ObjectUtils;
import com.hualong.mylibrary.bind.BindViewHolder;
import com.hualong.mylibrary.callback.RecyclerAdapterCallback;
import com.hualong.mylibrary.util.Console;

import java.util.ArrayList;
import java.util.List;

public class RecyclerBindAdapter extends RecyclerView.Adapter<BindViewHolder> {
    private Context mContext;
    private int resId;
    private RecyclerAdapterCallback callback;
    private List<Object> datas;



    public RecyclerBindAdapter(Context context, int resId) {
        this.mContext = context;
        this.resId = resId;
        callback = (RecyclerAdapterCallback) context;
        datas = new ArrayList<>();
    }

    public RecyclerAdapterCallback getCallback() {
        return callback;
    }

    //增加数据
    public void addList(List<Object> newData) {
        int position = datas.size();
        datas.addAll(position, newData);
        notifyItemInserted(position);
    }

    //设置数据
    public void setDatas(List<Object> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                resId, parent, false));
    }

    @Override
    public void onBindViewHolder(BindViewHolder holder, int position) {
        if (!ObjectUtils.isEmpty(datas))
            callback.onBindViewHolder(holder, position, datas.get(position));
        else
            Console.loge(mContext,this.getClass().getName(),"onBindViewHolder","datas = null");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
