package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Contract extends BaseBean {

    private ContractBean data;

    public ContractBean getData() {
        return data;
    }

    public void setData(ContractBean data) {
        this.data = data;
    }

    public static class ContractBean implements Serializable{
        private List<ListBean> rows;

        public List<ListBean> getRows() {
            return rows;
        }

        public void setRows(List<ListBean> rows) {
            this.rows = rows;
        }
    }

    public static class ListBean implements Serializable{
        private int id;
        private int customerId;//客户id，关联客户表
        private String customerName="";//客户名称
        private String signedTime="";//签订时间
        private double amount;//订单金额
        private int payType;//支付方式： 1，全款 2，分期
        private int assignerId;//指派人id
        private String assignerName="";//指派人名称
        private int invoice;//是否开票 0，否 1，是
        private int sellers;//售卖公司 1，博徕荣 2，立钻
        private String createDate="";
        private String updateDate="";
        private String prop2="";

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

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getSignedTime() {
            return signedTime;
        }

        public void setSignedTime(String signedTime) {
            this.signedTime = signedTime;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public int getAssignerId() {
            return assignerId;
        }

        public void setAssignerId(int assignerId) {
            this.assignerId = assignerId;
        }

        public String getAssignerName() {
            return assignerName;
        }

        public void setAssignerName(String assignerName) {
            this.assignerName = assignerName;
        }

        public int getInvoice() {
            return invoice;
        }

        public void setInvoice(int invoice) {
            this.invoice = invoice;
        }

        public int getSellers() {
            return sellers;
        }

        public void setSellers(int sellers) {
            this.sellers = sellers;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getProp2() {
            return prop2;
        }

        public void setProp2(String prop2) {
            this.prop2 = prop2;
        }
    }
}
