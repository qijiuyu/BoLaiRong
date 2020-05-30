package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class AddBatchno implements Serializable {

    private int goodsId;
    private int stockType;
    private String batchNo;
    private String num;

    public AddBatchno(){}

    public AddBatchno(int goodsId, int stockType, String batchNo, String num) {
        this.goodsId = goodsId;
        this.stockType = stockType;
        this.batchNo = batchNo;
        this.num = num;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

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
}
