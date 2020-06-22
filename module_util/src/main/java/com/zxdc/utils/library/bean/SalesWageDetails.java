package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SalesWageDetails extends BaseBean {

    private DetailsBean data;

    public DetailsBean getData() {
        return data;
    }

    public void setData(DetailsBean data) {
        this.data = data;
    }

    public static class DetailsBean implements Serializable{

        private List<ListBean> rows;

        public List<ListBean> getRows() {
            return rows;
        }

        public void setRows(List<ListBean> rows) {
            this.rows = rows;
        }
    }

    public static class ListBean implements Serializable{
        private String createName="";
        private String customerName="";
        private String contractCode="";
        private String payDate="";
        private double amount;
        private String memo="";

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getContractCode() {
            return contractCode;
        }

        public void setContractCode(String contractCode) {
            this.contractCode = contractCode;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }
}
