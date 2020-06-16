package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddSupplierP implements Serializable {

    private int id;
    private String supplierName;
    private int industry;
    private String contacts;
    private String phone;
    private String supplierAddress;
    private String memo;
    private String openAccount;
    private String openBank;
    private String openAccName;
    private String privateAccount;
    private String privateBank;
    private String privateAccName;
    private List<GoodList> supplierDetailList;

    public String getOpenAccount() {
        return openAccount;
    }

    public void setOpenAccount(String openAccount) {
        this.openAccount = openAccount;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getOpenAccName() {
        return openAccName;
    }

    public void setOpenAccName(String openAccName) {
        this.openAccName = openAccName;
    }

    public String getPrivateAccount() {
        return privateAccount;
    }

    public void setPrivateAccount(String privateAccount) {
        this.privateAccount = privateAccount;
    }

    public String getPrivateBank() {
        return privateBank;
    }

    public void setPrivateBank(String privateBank) {
        this.privateBank = privateBank;
    }

    public String getPrivateAccName() {
        return privateAccName;
    }

    public void setPrivateAccName(String privateAccName) {
        this.privateAccName = privateAccName;
    }

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

    public List<GoodList> getSupplierDetailList() {
        return supplierDetailList;
    }

    public void setSupplierDetailList(List<GoodList> supplierDetailList) {
        this.supplierDetailList = supplierDetailList;
    }

    public static class GoodList implements Serializable{
        private int goodsId;
        private String memo;
        private String prop1;

        public GoodList(){}

        public GoodList(int goodsId, String memo, String prop1) {
            this.goodsId = goodsId;
            this.memo = memo;
            this.prop1 = prop1;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getProp1() {
            return prop1;
        }

        public void setProp1(String prop1) {
            this.prop1 = prop1;
        }
    }
}
