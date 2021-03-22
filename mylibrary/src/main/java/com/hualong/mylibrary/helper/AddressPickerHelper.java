package com.hualong.mylibrary.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ActivityUtils;
import com.google.gson.reflect.TypeToken;
import com.hualong.mylibrary.MoptionsPickerBuilder;
import com.hualong.mylibrary.base.BaseDialog;
import com.hualong.mylibrary.bean.AddressBean;
import com.hualong.mylibrary.callback.AddressCallback;
import com.hualong.mylibrary.util.Console;
import com.hualong.mylibrary.util.JsonUtil;
import com.hualong.mylibrary.view.MOptionsPickerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址选择器
 */
public class AddressPickerHelper {
    public static Dialog dialog;
    private static AddressPickerHelper instance;
    //一级
    private static List<AddressBean> options1Items = new ArrayList<>();
    //一级
    private static ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    //三级
    private static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Context mContext;
    private String pickerName;
    private AddressCallback addressCallback;
    private OptionsPickerView pvOptions;
    private OptionsPickerBuilder optionsPickerBuilder;

    public AddressPickerHelper setOptionsPickerBuilder(OptionsPickerBuilder builder) {
        this.optionsPickerBuilder = builder;
        return instance;
    }

    public OptionsPickerBuilder getOptionsPickerBuilder() {
        initOptionsPickerBuilder();
        return optionsPickerBuilder;
    }

    /**
     * 单例模式
     */
    private AddressPickerHelper() {
        initJsonData(ActivityUtils.getTopActivity());
    }

    /**
     * 获取单列对象
     */
    public static AddressPickerHelper getInstance() {
        if (instance == null) {
            synchronized (AddressPickerHelper.class) {
                if (instance == null)
                    instance = new AddressPickerHelper();
            }
        }
        instance.initCofig();
        if(instance.optionsPickerBuilder == null)
            instance.initOptionsPickerBuilder();
        return instance;
    }

    public static AddressPickerHelper setTitle(String title) {
        instance.pickerName = title;
        return instance;
    }

    /**
     * 显示地址选择器
     */
    public  AddressPickerHelper show() {
        instance.showPickerView();
        return instance;
    }

    /**
     * 初始化弹框
     */
    public static void initJsonData(Context context) {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
        String JsonData = JsonUtil.getJsonFromFile(context, "city.json");//获取assets目录下的json文件数据
        List<AddressBean> addressBean = com.hualong.mylibrary.util.JsonUtil.fromJson(JsonData, new TypeToken<List<AddressBean>>() {
        }.getType());

        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        options1Items = addressBean;
        //遍历省份
        for (int i = 0; i < addressBean.size(); i++) {
            List<AddressBean.City> cityList = addressBean.get(i).getCity();
            //集合一个省的所有市级
            ArrayList<String> citys = new ArrayList();
            //集合一个市的所有地区
            ArrayList<ArrayList<String>> areas = new ArrayList();

            for (int x = 0; x < cityList.size(); x++) {
                citys.add(cityList.get(x).getName());
                areas.add(cityList.get(x).getArea());
            }
            /**
             * 添加城市数据
             */
            options2Items.add(citys);
            /**
             * 添加地区数据
             */
            options3Items.add(areas);
        }
    }

    /**
     * 初始化配置
     */
    private void initCofig() {
        if (mContext == null || mContext != ActivityUtils.getTopActivity()) {
            mContext = ActivityUtils.getTopActivity();
            pvOptions = null;
            if (mContext instanceof AddressCallback)
                addressCallback = (AddressCallback) mContext;
        }
    }

    public AddressPickerHelper onDialog(boolean flag) {
        if (!flag) return instance;
        dialog = new BaseDialog(mContext);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();
        return instance;
    }

    //设置回调
    public AddressPickerHelper setAddressCallback(AddressCallback callback) {
        this.addressCallback = callback;
        return instance;
    }


    private void initOptionsPickerBuilder() {
        optionsPickerBuilder = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";
                if (addressCallback != null)
                    addressCallback.onResult(opt1tx, opt2tx, opt3tx);
            }
        })


                .setTitleText(pickerName)
                .setTitleColor(Color.BLUE)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)
                .isDialog(false)
                .setOutSideCancelable(true);
    }

    /**
     * 弹框展示
     *
     * @return
     */
    private void showPickerView() {

        try {
            if(dialog != null)
                optionsPickerBuilder.setDecorView((ViewGroup) dialog.getWindow().getDecorView());
            else
                optionsPickerBuilder.setDecorView((ViewGroup) ((Activity)mContext).getWindow().getDecorView());
        } catch (Exception e) {
        }

        if(pvOptions != null){

            pvOptions.show();
            return;
        }

        pvOptions = optionsPickerBuilder.build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
        pvOptions.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                if (dialog != null) {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    dialog = null;
                }
            }
        });
    }


}
