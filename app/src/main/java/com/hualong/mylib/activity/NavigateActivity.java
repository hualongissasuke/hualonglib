package com.hualong.mylib.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hualong.mylib.R;
import com.hualong.mylibrary.helper.BottomNavigationViewHelper;

public class NavigateActivity extends AppCompatActivity {
    // private ActivityNavigateBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mBinding = DataBindingUtil.setContentView(this,R.layout.activity_navigate);
        // mBinding.bottomNavigationView.setItemIconTintList(null);//解决Android使用Menu时不显示彩色图标的问题
    }
}
