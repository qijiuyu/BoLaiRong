package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SalesWage extends BaseBean {

    private List<ListBean> data;

    public List<ListBean> getData() {
        return data;
    }

    public void setData(List<ListBean> data) {
        this.data = data;
    }

    public static class ListBean implements Serializable{
        private int salesId;
        private String month="";
        private String salesName="";
        private double amount;
        private double income;

        public int getSalesId() {
            return salesId;
        }

        public void setSalesId(int salesId) {
            this.salesId = salesId;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getSalesName() {
            return salesName;
        }

        public void setSalesName(String salesName) {
            this.salesName = salesName;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getIncome() {
            return income;
        }

        public void setIncome(double income) {
            this.income = income;
        }
    }
}
