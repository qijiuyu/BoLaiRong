package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SellingDetails extends BaseBean {

    private DetailsBean selling;
    private List<GoodsList> sellingDetailList;

    public DetailsBean getSelling() {
        return selling;
    }

    public void setSelling(DetailsBean selling) {
        this.selling = selling;
    }

    public List<GoodsList> getSellingDetailList() {
        return sellingDetailList;
    }

    public void setSellingDetailList(List<GoodsList> sellingDetailList) {
        this.sellingDetailList = sellingDetailList;
    }

    public static class DetailsBean implements Serializable{
        private int id;
        private String approvalDate="";
        private String approvalName="";
        private String sellName="";
        private String createDate="";
        private int status;
        private int state;
        private String memo="";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getApprovalDate() {
            return approvalDate;
        }

        public void setApprovalDate(String approvalDate) {
            this.approvalDate = approvalDate;
        }

        public String getApprovalName() {
            return approvalName;
        }

        public void setApprovalName(String approvalName) {
            this.approvalName = approvalName;
        }

        public String getSellName() {
            return sellName;
        }

        public void setSellName(String sellName) {
            this.sellName = sellName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

    }


    public static class GoodsList implements Serializable{
        private int sellId;
        private int goodsId;
        private String goodsName="";
        private String batchNo="";
        private int num;
        private double unitPrice;
        private double totalPrice;
        private int stockType;
        private String stockName="";
        //1：物料   2：设备
        private int type;
        private String memo="";

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSellId() {
            return sellId;
        }

        public void setSellId(int sellId) {
            this.sellId = sellId;
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

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getStockType() {
            return stockType;
        }

        public void setStockType(int stockType) {
            this.stockType = stockType;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }
    }
}
