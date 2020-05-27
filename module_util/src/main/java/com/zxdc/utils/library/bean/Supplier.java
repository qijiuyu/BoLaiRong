package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Supplier extends BaseBean {

    private  SupplierBean data;

    public SupplierBean getData() {
        return data;
    }

    public void setData(SupplierBean data) {
        this.data = data;
    }

    public static class SupplierBean implements Serializable{

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
        private String supplierName="";
        private String industryStr="";
        private String contacts="";
        private String phone="";
        private String supplierAddress="";

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
    }
}
