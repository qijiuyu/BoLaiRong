package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class UpdateWasteP implements Serializable {

    private int id;
    private int num;
    private int stockType;
    private String batchNo;
    private int type;
    private int deptId;
    private int chargeId;
    private String memo;

    public UpdateWasteP(){}

    public UpdateWasteP(int id, int num, int stockType, String batchNo, int type, int deptId, int chargeId,String memo) {
        this.id = id;
        this.num = num;
        this.stockType = stockType;
        this.batchNo = batchNo;
        this.type = type;
        this.deptId = deptId;
        this.chargeId = chargeId;
        this.memo=memo;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getChargeId() {
        return chargeId;
    }

    public void setChargeId(int chargeId) {
        this.chargeId = chargeId;
    }
}
