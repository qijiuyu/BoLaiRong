package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class LogDetails extends BaseBean{

    private DetailsBean saleLog;
    private Customer customer;

    public DetailsBean getSaleLog() {
        return saleLog;
    }

    public void setSaleLog(DetailsBean saleLog) {
        this.saleLog = saleLog;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public static class DetailsBean implements Serializable{
        private int id;
        private int customerId;
        private String followResult;
        private String createDate;
        private String operater;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getFollowResult() {
            return followResult;
        }

        public void setFollowResult(String followResult) {
            this.followResult = followResult;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getOperater() {
            return operater;
        }

        public void setOperater(String operater) {
            this.operater = operater;
        }
    }
}
