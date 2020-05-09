package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class SaleDetails implements Serializable {

    private int id;
    private int outId;
    private String goodsName;
    private String brand;
    private String spec;
    private int units;
    private String unitStr;
    private int stockType;
    private String stockTypeStr;
    private String batchNo;
    private int num;
    private double prop1;
    private double prop2;
    private String createName;
    private String createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOutId() {
        return outId;
    }

    public void setOutId(int outId) {
        this.outId = outId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getUnitStr() {
        return unitStr;
    }

    public void setUnitStr(String unitStr) {
        this.unitStr = unitStr;
    }

    public int getStockType() {
        return stockType;
    }

    public void setStockType(int stockType) {
        this.stockType = stockType;
    }

    public String getStockTypeStr() {
        return stockTypeStr;
    }

    public void setStockTypeStr(String stockTypeStr) {
        this.stockTypeStr = stockTypeStr;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getProp1() {
        return prop1;
    }

    public void setProp1(double prop1) {
        this.prop1 = prop1;
    }

    public double getProp2() {
        return prop2;
    }

    public void setProp2(double prop2) {
        this.prop2 = prop2;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
