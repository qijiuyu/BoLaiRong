package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SupplierDetails extends BaseBean {

    private  DetailsBean data;

    public DetailsBean getData() {
        return data;
    }

    public void setData(DetailsBean data) {
        this.data = data;
    }

    public static class DetailsBean implements Serializable{
        private int id;
        private String supplierName="";
        private int industry;
        private String industryStr="";
        private String contacts="";
        private String phone="";
        private String supplierAddress="";
        private String memo="";
        private int createId;
        private String createDate="";
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

        public List<GoodList> getSupplierDetailList() {
            return supplierDetailList;
        }

        public void setSupplierDetailList(List<GoodList> supplierDetailList) {
            this.supplierDetailList = supplierDetailList;
        }

        public int getIndustry() {
            return industry;
        }

        public void setIndustry(int industry) {
            this.industry = industry;
        }
    }


    public static class GoodList implements Serializable{
        private int id;
        private int supplierId;
        private int goodsId;
        private String goodsName="";
        private String spec="";
        private String brand="";
        private String unitStr="";
        private String typeStr="";
        private String memo="";
        private String createDate="";
        private String prop1="";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getUnitStr() {
            return unitStr;
        }

        public void setUnitStr(String unitStr) {
            this.unitStr = unitStr;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(String typeStr) {
            this.typeStr = typeStr;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getProp1() {
            return prop1;
        }

        public void setProp1(String prop1) {
            this.prop1 = prop1;
        }
    }
}
