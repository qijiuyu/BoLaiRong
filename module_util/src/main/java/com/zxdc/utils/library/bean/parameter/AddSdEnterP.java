package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddSdEnterP implements Serializable {

    private int purcId;
    private String purcDate;
    private String memo;
    private List<DetailsList> detailList;

    public int getPurcId() {
        return purcId;
    }

    public void setPurcId(int purcId) {
        this.purcId = purcId;
    }

    public String getPurcDate() {
        return purcDate;
    }

    public void setPurcDate(String purcDate) {
        this.purcDate = purcDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<DetailsList> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailsList> detailList) {
        this.detailList = detailList;
    }

    public static class DetailsList implements Serializable{
        private int goodsId;
        private int stockType;
        private String batchNo;
        private String price;
        private int num;
        private String amount;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getStockType() {
            return stockType;
        }

        public void setStockType(int stockType) {
            this.stockType = stockType;
        }

        public String getBatchNo() {
            return batchNo;
        }

        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
