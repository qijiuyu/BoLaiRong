package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class UpdateEntryGoodP implements Serializable {
    private int id;
    private int num;
    private int stockType;
    private String batchNo;
    private String memo;

    public UpdateEntryGoodP(){}

    public UpdateEntryGoodP(int id, int num, int stockType, String batchNo, String memo) {
        this.id = id;
        this.num = num;
        this.stockType = stockType;
        this.batchNo = batchNo;
        this.memo = memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
