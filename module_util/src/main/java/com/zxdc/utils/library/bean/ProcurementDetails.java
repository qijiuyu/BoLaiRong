package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class ProcurementDetails extends BaseBean {

    private DetailsBean data;

    public DetailsBean getData() {
        return data;
    }

    public void setData(DetailsBean data) {
        this.data = data;
    }

    public static class DetailsBean implements Serializable{
        private int id;
        private String purcName="";
        private String purcDate="";
        private String purcOrder="";
        private String createDate="";
        private int createId;
        private int state;
        private String approveName="";
        private String prop4="";
        private String prop5="";
        private String stateStr="";
        private List<GoodList> purchaseDetailList;
        private List<EntryList> entryDetailList;

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

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public String getApproveName() {
            return approveName;
        }

        public void setApproveName(String approveName) {
            this.approveName = approveName;
        }

        public String getProp4() {
            return prop4;
        }

        public void setProp4(String prop4) {
            this.prop4 = prop4;
        }

        public String getProp5() {
            return prop5;
        }

        public void setProp5(String prop5) {
            this.prop5 = prop5;
        }

        public String getStateStr() {
            return stateStr;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }

        public List<GoodList> getPurchaseDetailList() {
            return purchaseDetailList;
        }

        public void setPurchaseDetailList(List<GoodList> purchaseDetailList) {
            this.purchaseDetailList = purchaseDetailList;
        }

        public List<EntryList> getEntryDetailList() {
            return entryDetailList;
        }

        public void setEntryDetailList(List<EntryList> entryDetailList) {
            this.entryDetailList = entryDetailList;
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
        private String phone="";
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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


    public static class EntryList implements Serializable{
        private String goodsName="";
        private String batchNo="";
        private int num;
        private String arriveTypeStr="";
        private String stockTypeStr="";
        private String goodsTypeStr="";
        private String createName="";
        private String createDate="";
        private String memo="";

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getBatchNo() {
            return batchNo;
        }

        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getArriveTypeStr() {
            return arriveTypeStr;
        }

        public void setArriveTypeStr(String arriveTypeStr) {
            this.arriveTypeStr = arriveTypeStr;
        }

        public String getStockTypeStr() {
            return stockTypeStr;
        }

        public void setStockTypeStr(String stockTypeStr) {
            this.stockTypeStr = stockTypeStr;
        }

        public String getGoodsTypeStr() {
            return goodsTypeStr;
        }

        public void setGoodsTypeStr(String goodsTypeStr) {
            this.goodsTypeStr = goodsTypeStr;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }
}
