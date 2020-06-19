package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class AddPaymentP implements Serializable {

    private int contractId;
    private String payDate;
    private double amount;
    private String memo;

    public AddPaymentP(){}

    public AddPaymentP(int contractId, String payDate, double amount, String memo) {
        this.contractId = contractId;
        this.payDate = payDate;
        this.amount = amount;
        this.memo = memo;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
