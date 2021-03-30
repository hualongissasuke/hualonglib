package com.hualong.mylib.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.android.flexbox.JustifyContent;
import com.hualong.mylib.R;
import com.hualong.mylib.bean.LabelsBean;
import com.hualong.mylib.databinding.ActivityLabelsBinding;
import com.hualong.mylib.databinding.ItemLablesCustomBinding;
import com.hualong.mylibrary.bind.BindViewHolder;
import com.hualong.mylibrary.callback.LablesCallback;
import com.hualong.mylibrary.util.Console;
import com.hualong.mylibrary.view.LablesView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LabelsActivity extends BaseActivity implements LablesCallback<ItemLablesCustomBinding,LabelsBean> {
    private ActivityLabelsBinding mBinding;
    // public float[] margin = {0,0,0,0};
    public float[] margin = {10,0,0,0};
    private List<LabelsBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_labels);
        mBinding.setPresenter(this);

        initLabels();


    }



    @Override
    protected void requestSuccess(String res, int actionCode) {

    }

    @Override
    protected void requestFail(String error, int actionCode) {

    }

    private void initLabels() {
        String[] surname = {"林","冯","李","孙","梁","郭","曾","郑"};
        String[] name = {"华","志","洪","涛","志","亚","志","杰"};
        String[] name2 = {"龙","豪","磊","义","达","婷","莹","妮"};

        for(int i = 0; i < 22; i++){
            int r1 = new Random().nextInt(8);
            int r2 = new Random().nextInt(8);
            int r3 = new Random().nextInt(8);
            // int r4 = new Random().nextInt(100);
            list.add(new LabelsBean(String.format("%1$s%2$s%3$s",surname[r1],name[r2],name2[r3]),new Random().nextInt(100)));
        }

        mBinding.lablesViewCustom.lablesname = "自定义";
        mBinding.lablesViewCustom.setData(list);
        mBinding.lablesViewCustom.setItemCount(0);
        mBinding.lablesViewCustom.initLayout(R.layout.item_lables_custom,list);
        mBinding.lablesViewCustom.getFlexboxLayout().setJustifyContent(JustifyContent.SPACE_BETWEEN);

        mBinding.lablesView.setData(list);
        mBinding.lablesView.setSelectIndex(3);
        mBinding.lablesView.setItemCount(4);
        mBinding.lablesView.getFlexboxLayout().setJustifyContent(JustifyContent.SPACE_BETWEEN);
        mBinding.lablesView.setSelectType(LablesView.SINGLE);
    }

    @Override
    public void callback(List<Integer> selects) {
        Console.logd( "中",selects);
    }

    @Override
    public void initView(TextView tv, LabelsBean obj, int position) {
        LabelsBean bean = (LabelsBean) obj;
        tv.setText(bean.name);
    }

    @Override
    public void customView(ItemLablesCustomBinding labelsBinding, final LabelsBean bean, int position) {
        labelsBinding.tv.setText(String.format("%1$s今年%2$d",bean.name,bean.age));
        labelsBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast(String.format("%1$s今年%2$d",bean.name,bean.age));
            }
        });

        labelsBinding.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast(bean.name);
            }
        });

        labelsBinding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast(bean.age);
            }
        });
    }

}
