package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddProductPlanP implements Serializable {

    private String prop2;
    private String deliveryDate;
    private String memo;
    private List<AddGoodP> detailList;

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<AddGoodP> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<AddGoodP> detailList) {
        this.detailList = detailList;
    }
}
