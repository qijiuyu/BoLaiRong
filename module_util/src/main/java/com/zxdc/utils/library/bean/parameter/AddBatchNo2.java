package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class AddBatchNo2 implements Serializable {
    private String batchNo;
    private String num;

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
