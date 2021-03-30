package com.hualong.mylib.activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.blankj.utilcode.util.ActivityUtils;
import com.hualong.mylib.R;
import com.hualong.mylib.bean.ListItem;
import com.hualong.mylib.databinding.ItemListBinding;
import com.hualong.mylibrary.adapter.RecyclerBindAdapter;
import com.hualong.mylibrary.callback.RecyclerAdapterCallback;
import com.hualong.mylibrary.util.Console;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseActivity implements RecyclerAdapterCallback<ItemListBinding, ListItem>, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this,R.layout.activity_list);
        Console.logd(this);
    }


    @Override
    public void onBindViewHolder(ItemListBinding recyclerItemBinding, int position,final ListItem item) {
        recyclerItemBinding.btn.setText(item.name);
        recyclerItemBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(item.clz);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                // ActivityUtils.startActivity()
                break;
        }
    }

    @Override
    public void getAdapter(RecyclerBindAdapter adapter) {
        this.recyclerBindAdapter = adapter;
        List<ListItem> mItems = new ArrayList<>();
        mItems.add(new ListItem(RecyclerViewActivity.class));
        mItems.add(new ListItem(BottomNavigateActivity.class));
        mItems.add(new ListItem(NetworkActivity.class));
        mItems.add(new ListItem(AppActivity.class));
        mItems.add(new ListItem(PickerActivity.class));
        mItems.add(new ListItem(NetworkActivity.class));
        adapter.setDatas(mItems);
    }




    @Override
    protected void requestSuccess(String res, int actionCode) {

    }

    @Override
    protected void requestFail(String error, int actionCode) {

    }


}
