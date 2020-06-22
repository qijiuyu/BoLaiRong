package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class EntryBonusDetails extends BaseBean {

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
        private String customerName="";
        private String approvalName="";
        private String privateName="";
        private double entryFee;
        private String approvalDate="";

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getApprovalName() {
            return approvalName;
        }

        public void setApprovalName(String approvalName) {
            this.approvalName = approvalName;
        }

        public String getPrivateName() {
            return privateName;
        }

        public void setPrivateName(String privateName) {
            this.privateName = privateName;
        }

        public double getEntryFee() {
            return entryFee;
        }

        public void setEntryFee(double entryFee) {
            this.entryFee = entryFee;
        }

        public String getApprovalDate() {
            return approvalDate;
        }

        public void setApprovalDate(String approvalDate) {
            this.approvalDate = approvalDate;
        }
    }
}
