package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class Goods implements Serializable {

    private int id;
    private String name;
    private String spec;
    private int units;
    private String unitStr;
    private String brand;
    private int type;
    private String typeStr;
    private int num;
    private String prop1;
    private String prop2;
    private String memo;
    private String prop3;
    public  Goods(){}

    public Goods(int id,String name, String spec, String unitStr, String brand, int num, String prop1, String prop2, String memo, String prop3) {
        this.id=id;
        this.name = name;
        this.spec = spec;
        this.unitStr = unitStr;
        this.brand = brand;
        this.num = num;
        this.prop1 = prop1;
        this.prop2 = prop2;
        this.memo = memo;
        this.prop3 = prop3;
    }

    public Goods(int id, String name, String spec, String brand, int num, String memo) {
        this.id = id;
        this.name = name;
        this.spec = spec;
        this.brand = brand;
        this.num = num;
        this.memo = memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getProp3() {
        return prop3;
    }

    public void setProp3(String prop3) {
        this.prop3 = prop3;
    }


}
