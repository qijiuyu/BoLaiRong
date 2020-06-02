package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class TransferP implements Serializable {

    private int id;
    private int prop1;
    private String prop2;
    private String prop3;
    private String prop5;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProp1() {
        return prop1;
    }

    public void setProp1(int prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public String getProp3() {
        return prop3;
    }

    public void setProp3(String prop3) {
        this.prop3 = prop3;
    }

    public String getProp5() {
        return prop5;
    }

    public void setProp5(String prop5) {
        this.prop5 = prop5;
    }
}
