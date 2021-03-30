package com.hualong.mylib.bean;

import com.hualong.mylibrary.util.Console;

public class ListItem {
    public String name;
    public Class clz;

    public ListItem(Class clz){

        this.name = clz.getSimpleName();
        this.clz = clz;
    }
}
