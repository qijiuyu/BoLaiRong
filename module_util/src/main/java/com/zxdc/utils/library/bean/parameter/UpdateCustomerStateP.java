package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class UpdateCustomerStateP implements Serializable {

    private int id;
    private int privateId;
    private int privateState;

    public UpdateCustomerStateP(){}

    public UpdateCustomerStateP(int id, int privateId, int privateState) {
        this.id = id;
        this.privateId = privateId;
        this.privateState = privateState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrivateId() {
        return privateId;
    }

    public void setPrivateId(int privateId) {
        this.privateId = privateId;
    }

    public int getPrivateState() {
        return privateState;
    }

    public void setPrivateState(int privateState) {
        this.privateState = privateState;
    }
}
