package com.zxdc.utils.library.bean.parameter;

import java.util.List;

public class AddDeviceP {

    private int id;
    private String equipName;
    private String spec;
    private int equipType;
    private String code;
    private String manufacturers;
    private double amount;
    private int deptId;
    private String purcTime;
    private int flag;
    private List<FileList> fileList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getEquipType() {
        return equipType;
    }

    public void setEquipType(int equipType) {
        this.equipType = equipType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(String manufacturers) {
        this.manufacturers = manufacturers;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getPurcTime() {
        return purcTime;
    }

    public void setPurcTime(String purcTime) {
        this.purcTime = purcTime;
    }

    public List<FileList> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileList> fileList) {
        this.fileList = fileList;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
