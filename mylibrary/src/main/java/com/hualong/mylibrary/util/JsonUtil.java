package com.hualong.mylibrary.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class JsonUtil {
    private static Gson gson = new Gson();

    /** 对象解析 */
    public static <T> T fromJson(String result,Class<T> clazz) {
        return gson.fromJson(result, clazz);
    }

    /**数组解析*/
    public static <T> T fromJson(String result, Type clazz) {
        return gson.fromJson(result, clazz);
    }

    /** 对象转json */
    public static String toJson(Object src) {
        return gson.toJson(src);
    }


    public static <T> String toJson(Object src, Class<T> typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }

    /**
     * 解析文件获取json
     * @param context
     * @param fileName
     * @return
     */
    public static String getJsonFromFile(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
