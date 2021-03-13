package com.hualong.mylibrary.callback;

import com.hualong.mylibrary.bind.BindViewHolder;
import com.hualong.mylibrary.adapter.RecyclerBindAdapter;

public interface RecyclerAdapterCallback {
    void onBindViewHolder(BindViewHolder holder,int position,Object obj);
    void getAdapter(RecyclerBindAdapter adapter);
}
