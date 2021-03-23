package com.hualong.mylib.activity;


import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hualong.mylib.R;
import com.hualong.mylib.databinding.ActivityBottomNavigateBinding;

public class BottomNavigateActivity extends AppCompatActivity {
    private ActivityBottomNavigateBinding mBinding;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();//去除标题栏
        mBinding =  DataBindingUtil.setContentView(this,R.layout.activity_bottom_navigate);
        mBinding.bottomNavigationView.setItemIconTintList(null);//还原图标色彩！！！


        //实现页面的切换
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_home, R.id.nav_home, R.id.nav_home, R.id.nav_home)
                .build();
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(mBinding.bottomNavigationView,navController);


    }


}
