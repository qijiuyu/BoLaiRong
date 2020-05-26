package com.zxdc.utils.library.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Income extends BaseBean {

    private IncomeBean data;

    public IncomeBean getData() {
        return data;
    }

    public void setData(IncomeBean data) {
        this.data = data;
    }

    public static class IncomeBean implements Serializable{
        @SerializedName("支出金额")
        private double Spending;
        @SerializedName("收入金额")
        private double income;

        public double getSpending() {
            return Spending;
        }

        public void setSpending(double spending) {
            Spending = spending;
        }

        public double getIncome() {
            return income;
        }

        public void setIncome(double income) {
            this.income = income;
        }
    }
}
