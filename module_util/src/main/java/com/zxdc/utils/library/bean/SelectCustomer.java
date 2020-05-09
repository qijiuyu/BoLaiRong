package com.zxdc.utils.library.bean;

import java.util.List;

public class SelectCustomer extends BaseBean {

    private List<Customer> customer;

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }
}
