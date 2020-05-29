package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class AddBatchno implements Serializable {

    private String batchNo;
    private String num;

    public AddBatchno(){}

    public AddBatchno(String batchNo, String num) {
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
}
