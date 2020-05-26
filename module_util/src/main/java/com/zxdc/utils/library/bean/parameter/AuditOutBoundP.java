package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class AuditOutBoundP implements Serializable {

    private int id;
    private int createId;
    private int state;
    private String prop4;

    public AuditOutBoundP(){}

    public AuditOutBoundP(int id, int createId, int state, String prop4) {
        this.id = id;
        this.createId = createId;
        this.state = state;
        this.prop4 = prop4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getProp4() {
        return prop4;
    }

    public void setProp4(String prop4) {
        this.prop4 = prop4;
    }
}
