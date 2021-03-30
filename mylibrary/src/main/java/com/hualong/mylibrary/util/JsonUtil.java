package com.hualong.mylibrary.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private static Gson gson = new Gson();

    /** 对象解析 */
    public static <T> T fromJson(String result,Class<T> clazz) {
        return gson.fromJson(result, clazz);
    }

    /** 从获取结果对象获取数组 */
    public static String getArrayForRes(String obj,String arr_name){
        try {
            return new JSONObject(obj).getString(arr_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**数组解析*/
    public static <T> T fromJson(String result, Type clazz) {
        //gson.fromJson(json, new TypeToken<List<T>>() {}.getType());
        return gson.fromJson(result, clazz);
    }

    /**数组解析*/
    public static <T> List<T> getListFromJson(String array,Class<T> clazz){
        List<T> list = new ArrayList<>();
        JsonArray jsonArray = new JsonParser().parse(array).getAsJsonArray();
        for (JsonElement jsonElement : jsonArray)
            list.add(gson.fromJson(jsonElement, clazz)); //cls
        return list;
    }

    /** 对象解析 */
    public static <T> T getObjectFromJson(String result,Class<T> clazz) {
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
