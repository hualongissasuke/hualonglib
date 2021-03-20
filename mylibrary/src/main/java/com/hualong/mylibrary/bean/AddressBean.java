package com.hualong.mylibrary.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.ArrayList;
import java.util.List;

public class AddressBean implements IPickerViewData {

    /**
     * name : 广东省
     * city : [{"name":"广州市","area":["其他"]}]
     */

    private String name;
    private List<City> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public static class City {
        /**
         * name : 广州市
         * area : ["其他"]
         */

        private String name;
        private ArrayList<String> area;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<String> getArea() {
            return area;
        }

        public void setArea(ArrayList<String> area) {
            this.area = area;
        }
    }
}
