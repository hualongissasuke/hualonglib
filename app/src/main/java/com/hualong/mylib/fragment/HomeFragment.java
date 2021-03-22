package com.hualong.mylib.fragment;

import com.hualong.mylib.R;

public class HomeFragment extends LazyLoadFragment {
    @Override
    protected int setContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void lazyLoad() {
    }
}
