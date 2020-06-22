package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class CustomerList extends BaseBean {

    private  CustomerBean data;

    public CustomerBean getData() {
        return data;
    }

    public void setData(CustomerBean data) {
        this.data = data;
    }

    public static class CustomerBean implements Serializable{

        private int total;

        private List<Customer> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Customer> getRows() {
            return rows;
        }

        public void setRows(List<Customer> rows) {
            this.rows = rows;
        }
    }
}
