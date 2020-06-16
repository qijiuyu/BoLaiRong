package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class CustomerAuditP implements Serializable {

    private int id;
    private int state;
    private double entryFee;
    private int createId;
    private String memo;

    public CustomerAuditP(){}

    public CustomerAuditP(int id, int state, double entryFee, int createId,String memo) {
        this.id = id;
        this.state = state;
        this.entryFee = entryFee;
        this.createId = createId;
        this.memo=memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
