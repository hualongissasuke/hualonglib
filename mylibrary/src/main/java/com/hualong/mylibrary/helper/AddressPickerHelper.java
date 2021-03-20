package com.hualong.mylibrary.helper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ActivityUtils;
import com.google.gson.reflect.TypeToken;
import com.hualong.mylibrary.bean.AddressBean;
import com.hualong.mylibrary.callback.AddressCallback;
import com.hualong.mylibrary.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址选择器
 */
public class AddressPickerHelper {
    private static AddressPickerHelper instance;
    private Context mContext;
    private String pickerName;
    private AddressCallback addressCallback;
    private OptionsPickerView pvOptions;

    /** 单例模式 */
    private AddressPickerHelper() {
        initJsonData(ActivityUtils.getTopActivity());
    }

    /** 获取单列对象 */
    public static AddressPickerHelper getInstance() {
        if (instance == null) {
            synchronized (AddressPickerHelper.class) {
                if (instance == null)
                    instance = new AddressPickerHelper();
            }
        }
        instance.initCofig();
        return instance;
    }

    /** 初始化配置 */
    private void initCofig(){
        if(mContext == null || mContext != ActivityUtils.getTopActivity()||true){
            mContext = ActivityUtils.getTopActivity();
            pvOptions = null;
            if (mContext instanceof AddressCallback)
                addressCallback = (AddressCallback) mContext;
        }

    }

    //设置回调
    public AddressPickerHelper setAddressCallback(AddressCallback callback) {
        this.addressCallback = callback;
        return instance;
    }

    public static AddressPickerHelper setTitle(String title){
        instance.pickerName = title;
        return instance;
    }

    /** 显示地址选择器 */
    public static AddressPickerHelper show() {
        getInstance();
        instance.showPickerView();
        return instance;
    }

    //一级
    private static List<AddressBean> options1Items = new ArrayList<>();
    //一级
    private static ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    //三级
    private static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

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

        // ArrayList<RegionalBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        // options1Items = jsonBean;

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

        // for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
        //     ArrayList<String> city_list = new ArrayList<>();//该省的城市列表（第二级）
        //     ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
        //
        //     for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
        //         String cityName = jsonBean.get(i).getCityList().get(c).getName();
        //         city_list.add(cityName);//添加城市
        //         ArrayList<String> area_list = new ArrayList<>();//该城市的所有地区列表
        //
        //         //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
        //         /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
        //                 || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
        //             city_AreaList.add("");
        //         } else {
        //             city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
        //         }*/
        //         area_list.addAll(jsonBean.get(i).getCityList().get(c).getArea());
        //         province_AreaList.add(area_list);//添加该省所有地区数据
        //     }
        //
        //
        //     /**
        //      * 添加城市数据
        //      */
        //     // options2Items.add(city_list);
        //
        //     /**
        //      * 添加地区数据
        //      */
        //     // options3Items.add(province_AreaList);
        // }
    }



    /**
     * 弹框展示
     * @return
     */
    private void showPickerView() {
        if(pvOptions != null){
            pvOptions.show();
            return;
        }
        pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
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
                if(addressCallback != null)
                    addressCallback.onResult(opt1tx, opt2tx, opt3tx);
            }
        })

                .setTitleText(pickerName)
                .setTitleColor(Color.BLUE)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();

    }


}
