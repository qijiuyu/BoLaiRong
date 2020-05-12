package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class AddBatchno implements Serializable {

    private String batchNo;
    private int num;

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
}
