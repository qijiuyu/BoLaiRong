package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SdEnterDetails extends BaseBean {

    private  DetailsBean data;

    public DetailsBean getData() {
        return data;
    }

    public void setData(DetailsBean data) {
        this.data = data;
    }

    public static class DetailsBean implements Serializable{
        private int id;
        private String memo="";
        private String createName="";
        private String purcName="";
        private String createDate="";
        private String purcDate="";
        private List<GoodList> detailList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getPurcName() {
            return purcName;
        }

        public void setPurcName(String purcName) {
            this.purcName = purcName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getPurcDate() {
            return purcDate;
        }

        public void setPurcDate(String purcDate) {
            this.purcDate = purcDate;
        }

        public List<GoodList> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<GoodList> detailList) {
            this.detailList = detailList;
        }
    }

    public static class GoodList implements Serializable{
        private String stockTypeStr="";
        private String goodsName="";
        private String spec="";
        private String unitsStr="";
        private String goodsTypeStr="";
        private double amount;
        private String batchNo="";
        private int num;
        private String createName="";
        private String createDate="";

        public String getStockTypeStr() {
            return stockTypeStr;
        }

        public void setStockTypeStr(String stockTypeStr) {
            this.stockTypeStr = stockTypeStr;
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

        public String getUnitsStr() {
            return unitsStr;
        }

        public void setUnitsStr(String unitsStr) {
            this.unitsStr = unitsStr;
        }

        public String getGoodsTypeStr() {
            return goodsTypeStr;
        }

        public void setGoodsTypeStr(String goodsTypeStr) {
            this.goodsTypeStr = goodsTypeStr;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
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
    }
}
