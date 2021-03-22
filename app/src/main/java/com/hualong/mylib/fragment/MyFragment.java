package com.hualong.mylib.fragment;

import com.hualong.mylib.R;

public class MyFragment extends LazyLoadFragment {
    @Override
    protected int setContentView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void lazyLoad() {
    }
}
