package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class AddLogP implements Serializable {

    private int customerId;
    private String followResult;

    public AddLogP(){}

    public AddLogP(int customerId, String followResult) {
        this.customerId = customerId;
        this.followResult = followResult;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFollowResult() {
        return followResult;
    }

    public void setFollowResult(String followResult) {
        this.followResult = followResult;
    }
}
