package com.hualong.mylib.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.hualong.mylib.R;
import com.hualong.mylib.application.MyApplication;
import com.hualong.mylib.databinding.ActivityMainBinding;
import com.hualong.mylib.databinding.ItemTestBinding;
import com.hualong.mylibrary.api.Api;
import com.hualong.mylibrary.api.ApiOptions;
import com.hualong.mylibrary.api.ResCallback;
import com.hualong.mylibrary.base.BasicActivity;
import com.hualong.mylibrary.bind.BindViewHolder;
import com.hualong.mylibrary.adapter.RecyclerBindAdapter;
import com.hualong.mylibrary.callback.RecyclerAdapterCallback;
import com.hualong.mylibrary.callback.RefreshCallback;
import com.hualong.mylibrary.helper.AddressPickerHelper;
import com.hualong.mylibrary.util.Console;
import com.hualong.mylibrary.util.SPUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BasicActivity implements ResCallback, RecyclerAdapterCallback, RefreshCallback {
    private ActivityMainBinding mBinding;
    public int resId = R.layout.item_test;
    private Test test;
    private ApiOptions mOptions;
    private ApiOptions homeOptions;
    public String name="ssssssss";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBinding.setPresenter(this);

       // navigateTo(PickerActivity.class);

        // initOptions();

        // AddressPickerHelper.show();

    }

    private void initOptions() {
        SPUtil.getInstance().setValue(SPUtil.TOKEN,MyApplication.token);
        SPUtil.getInstance().setValue(SPUtil.LOGIN_ID,MyApplication.login);

        mOptions = new ApiOptions(this);
        Map<String,String> map = new HashMap<>();
        map.put("materialType", "3");
        map.put("size", "120*150");
        map.put("storehouseId", "2");
        map.put("sum", String.valueOf(1));
        mOptions.setOptions(ApiOptions.urlWithToken(MyApplication.add),map,
                null,1001);

        Map<String,String> homeParams = new HashMap<>();
        homeParams.put("loginId",MyApplication.login);
        homeParams.put("token",MyApplication.token);
        homeParams.put("storehouseId","");
        homeOptions = new ApiOptions(this,MyApplication.home,homeParams,null,1002);

        Api.isToken = false;
        Api.create(mOptions).postBody();
        // Api.create(homeOptions).post();
    }

    @Override
    public void success(String res, int actionCode) {

    }

    @Override
    public void fail(String error, int actionCode) {

    }




    @Override
    public void onBindViewHolder(BindViewHolder holder, final int position, Object obj) {
        ItemTestBinding itemBinding = (ItemTestBinding) holder.getBinding();
        Test test = (Test) obj;
        itemBinding.tvName.setText(String.format("%1$s%2$d",test.name,position));
        // itemBinding.etContent.setClickable(false);
        itemBinding.btn.setClickable(false);
        itemBinding.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position %  2 == 0)
                    ActivityUtils.startActivity(PickerActivity.class);
                else
                    AddressPickerHelper.getInstance().show();
            }
        });
        // itemBinding.btn.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         toast(String.format("按钮%1$d被点中",position));
        //     }
        // });
    }

    @Override
    public void getAdapter(RecyclerBindAdapter adapter) {
        List<Object> datas = new ArrayList<>();
        datas.add(new Test("原料名称","测试"));
        datas.add(new Test("实收数量","测试"));
        datas.add(new Test("生产日期","测试"));
        datas.add(new Test("保质期","测试"));
        datas.add(new Test("生产地","测试"));
        adapter.setDatas(datas);
        adapter.addList(datas);
        adapter.addList(datas);
        adapter.addList(datas);
        adapter.addList(datas);
    }

    @Override
    public void onRefrsh() {

    }

    @Override
    public void onLoadMore() {

    }

    public class Test{
        String name;
        String content;

        public Test(){}

        public Test(String name,String content){
            this.name = name;
            this.content = content;
        }
    }
}
