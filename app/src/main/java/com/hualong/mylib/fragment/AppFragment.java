package com.hualong.mylib.fragment;

import com.hualong.mylib.R;

public class AppFragment extends LazyLoadFragment {
    @Override
    protected int setContentView() {
        return R.layout.fragment_app;
    }

    @Override
    protected void lazyLoad() {
    }
}
