package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddSupplierMaterialP implements Serializable {

    private int id;
    private List<AddSupplierP.GoodList> supplierDetailList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AddSupplierP.GoodList> getSupplierDetailList() {
        return supplierDetailList;
    }

    public void setSupplierDetailList(List<AddSupplierP.GoodList> supplierDetailList) {
        this.supplierDetailList = supplierDetailList;
    }
}
