package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class ProcurementDetails extends BaseBean {

    private DetailsBean purchase;
    private List<GoodList> purchaseDetailList;

    public DetailsBean getPurchase() {
        return purchase;
    }

    public void setPurchase(DetailsBean purchase) {
        this.purchase = purchase;
    }

    public List<GoodList> getPurchaseDetailList() {
        return purchaseDetailList;
    }

    public void setPurchaseDetailList(List<GoodList> purchaseDetailList) {
        this.purchaseDetailList = purchaseDetailList;
    }

    public static class DetailsBean implements Serializable{
        private int id;
        private String purcName="";
        private String purcDate="";
        private String purcOrder="";
        private String createDate="";
        private int state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPurcName() {
            return purcName;
        }

        public void setPurcName(String purcName) {
            this.purcName = purcName;
        }

        public String getPurcDate() {
            return purcDate;
        }

        public void setPurcDate(String purcDate) {
            this.purcDate = purcDate;
        }

        public String getPurcOrder() {
            return purcOrder;
        }

        public void setPurcOrder(String purcOrder) {
            this.purcOrder = purcOrder;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }


    public static class GoodList implements Serializable{
        private String supplierName="";
        private int num;
        private double unitPrice;
        private double amount;
        private String memo="";
        private int payType;
        private String payDate="";
        private String name="";
        private String typeName="";
        private String spec="";
        private String unitName="";
        private String photo="";
        private String address="";
        private String purcName="";
        private String contacts="";

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
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

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPurcName() {
            return purcName;
        }

        public void setPurcName(String purcName) {
            this.purcName = purcName;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }
    }
}
