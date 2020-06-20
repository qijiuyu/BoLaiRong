package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class WorkerDetails extends BaseBean {

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
        private String goodsName="";
        private String stockTypeStr="";
        private String parentStockTypeStr="";
        private String spec="";
        private String brand="";
        private String unitStr="";
        private String goodsTypeStr="";
        private int num;
        private String batchNo="";
        private String memo="";
        private String wage="";
        private String rejectRate="";
        private String income="";

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getStockTypeStr() {
            return stockTypeStr;
        }

        public void setStockTypeStr(String stockTypeStr) {
            this.stockTypeStr = stockTypeStr;
        }

        public String getParentStockTypeStr() {
            return parentStockTypeStr;
        }

        public void setParentStockTypeStr(String parentStockTypeStr) {
            this.parentStockTypeStr = parentStockTypeStr;
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

        public String getGoodsTypeStr() {
            return goodsTypeStr;
        }

        public void setGoodsTypeStr(String goodsTypeStr) {
            this.goodsTypeStr = goodsTypeStr;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getBatchNo() {
            return batchNo;
        }

        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getWage() {
            return wage;
        }

        public void setWage(String wage) {
            this.wage = wage;
        }

        public String getRejectRate() {
            return rejectRate;
        }

        public void setRejectRate(String rejectRate) {
            this.rejectRate = rejectRate;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }
    }
}
