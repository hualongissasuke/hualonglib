package com.hualong.mylib.fragment;

import com.hualong.mylib.R;

public class NewsFragment extends LazyLoadFragment {
    @Override
    protected int setContentView() {
        return R.layout.fragment_news;
    }

    @Override
    protected void lazyLoad() {
    }
}
