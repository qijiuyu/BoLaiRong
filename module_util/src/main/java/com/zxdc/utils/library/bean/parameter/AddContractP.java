package com.zxdc.utils.library.bean.parameter;

import java.util.List;

public class AddContractP {

    private int id;
    private String prop2;
    private int customerId;
    private String signedTime;
    private double amount;
    private int payType;
    private int assignerId;
    private int invoice;
    private int sellers;
    private List<FileList> fileList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(String signedTime) {
        this.signedTime = signedTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(int assignerId) {
        this.assignerId = assignerId;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getSellers() {
        return sellers;
    }

    public void setSellers(int sellers) {
        this.sellers = sellers;
    }

    public List<FileList> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileList> fileList) {
        this.fileList = fileList;
    }
}
