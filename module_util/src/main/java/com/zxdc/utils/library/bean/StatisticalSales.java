package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class StatisticalSales extends BaseBean {

    private SalesBean data;

    public SalesBean getData() {
        return data;
    }

    public void setData(SalesBean data) {
        this.data = data;
    }

    public static class SalesBean implements Serializable{
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
        private float num;
        private float ammount;
        private String month;

        public float getNum() {
            return num;
        }

        public void setNum(float num) {
            this.num = num;
        }

        public float getAmmount() {
            return ammount;
        }

        public void setAmmount(float ammount) {
            this.ammount = ammount;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }
    }
}
