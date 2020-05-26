package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class StatisticalMaterial extends BaseBean {
    private MaterialBean data;

    public MaterialBean getData() {
        return data;
    }

    public void setData(MaterialBean data) {
        this.data = data;
    }

    public static class MaterialBean implements Serializable{
        private String[] timeList;
        private List<DataList> dataList;

        public String[] getTimeList() {
            return timeList;
        }

        public void setTimeList(String[] timeList) {
            this.timeList = timeList;
        }

        public List<DataList> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataList> dataList) {
            this.dataList = dataList;
        }
    }


    public static class DataList implements Serializable{
        private float total;
        private String month;

        public float getTotal() {
            return total;
        }

        public void setTotal(float total) {
            this.total = total;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }
    }
}
