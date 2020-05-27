package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class UpdateProductP implements Serializable {

    private int id;
    private int planId;
    private int deptId;
    private String outStatus;
    private String entryStatus;

    public UpdateProductP(){}

    public UpdateProductP(int id, int planId, int deptId, String outStatus, String entryStatus) {
        this.id = id;
        this.planId = planId;
        this.deptId = deptId;
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

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
}
