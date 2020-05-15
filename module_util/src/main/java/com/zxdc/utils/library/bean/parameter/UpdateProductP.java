package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class UpdateProductP implements Serializable {

    private int id;
    private String outStatus;
    private String entryStatus;

    public UpdateProductP(){}

    public UpdateProductP(int id, String outStatus, String entryStatus) {
        this.id = id;
        this.outStatus = outStatus;
        this.entryStatus = entryStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(String outStatus) {
        this.outStatus = outStatus;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }
}
