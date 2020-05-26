package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SupplierName extends BaseBean{

    private List<ListBean> dataList;

    public List<ListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<ListBean> dataList) {
        this.dataList = dataList;
    }

    public static class ListBean implements Serializable{
        private int id;
        private String supplierName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }
    }
}
