package com.hualong.mylibrary.api;

public interface StringCallback {
    void success(String res,int actionCode);
    void fail(String error,int actionCode);
}
