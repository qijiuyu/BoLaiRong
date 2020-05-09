package com.zxdc.utils.library.bean.parameter;

import java.util.List;

public class OutBoundP {

    private int customerId;
    private String prop2;
    private int prop1;
    private String outDate;
    private int payType;
    private String receivableDate;
    private double unpaidAmount;
    private double addAmount;

    private List<AddGoodP> outOrderDetailList;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public int getProp1() {
        return prop1;
    }

    public void setProp1(int prop1) {
        this.prop1 = prop1;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public List<AddGoodP> getOutOrderDetailList() {
        return outOrderDetailList;
    }

    public void setOutOrderDetailList(List<AddGoodP> outOrderDetailList) {
        this.outOrderDetailList = outOrderDetailList;
    }

    public String getReceivableDate() {
        return receivableDate;
    }

    public void setReceivableDate(String receivableDate) {
        this.receivableDate = receivableDate;
    }

    public double getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(double unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public double getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(double addAmount) {
        this.addAmount = addAmount;
    }
}
