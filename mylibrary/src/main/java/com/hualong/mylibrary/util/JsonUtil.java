package com.hualong.mylibrary.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtil {
    private static Gson gson = new Gson();

    public static <T> T fromJson(String result,Class<T> clazz) {
        return gson.fromJson(result, clazz);
    }
    public static <T> T fromJson(String result, Type clazz) {
        return gson.fromJson(result, clazz);
    }

    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static <T> String toJson(Object src, Class<T> typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }
}
