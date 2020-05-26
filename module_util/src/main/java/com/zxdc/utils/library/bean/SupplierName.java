package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SupplierName extends BaseBean{

    private List<ListBean> dataList;

    public List<ListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<ListBean> dataList) {
        this.dataList = dataList;
    }

    public static class ListBean implements Serializable{
        private int id;
        private String supplierName;
        private int industry;
        private String industryStr="";
        private String contacts="";
        private String phone="";
        private String supplierAddress="";
        private String memo="";
        private int createId;
        private String createDate="";


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public int getIndustry() {
            return industry;
        }

        public void setIndustry(int industry) {
            this.industry = industry;
        }

        public String getIndustryStr() {
            return industryStr;
        }

        public void setIndustryStr(String industryStr) {
            this.industryStr = industryStr;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSupplierAddress() {
            return supplierAddress;
        }

        public void setSupplierAddress(String supplierAddress) {
            this.supplierAddress = supplierAddress;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
