package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class EditSupplierGoodsP implements Serializable {

    private int id;
    private String prop1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }
}
