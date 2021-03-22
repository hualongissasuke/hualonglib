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

import com.hualong.mylib.R;
import com.hualong.mylib.databinding.ActivityBottomNavigateBinding;

public class BottomNavigateActivity extends AppCompatActivity {
    private ActivityBottomNavigateBinding mBinding;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding =  DataBindingUtil.setContentView(this,R.layout.activity_bottom_navigate);
        mBinding.bottomNavigationView.setItemIconTintList(null);

        //实现页面的切换
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home, R.id.app, R.id.shop, R.id.news, R.id.my)
                .build();
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(mBinding.bottomNavigationView,navController);
    }


}
