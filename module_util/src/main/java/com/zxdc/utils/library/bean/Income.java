package com.zxdc.utils.library.bean;

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
        private double paid;
        private double income;

        public double getPaid() {
            return paid;
        }

        public void setPaid(double paid) {
            this.paid = paid;
        }

        public double getIncome() {
            return income;
        }

        public void setIncome(double income) {
            this.income = income;
        }
    }
}
