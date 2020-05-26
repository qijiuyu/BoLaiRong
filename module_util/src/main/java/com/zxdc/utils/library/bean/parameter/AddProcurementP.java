package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddProcurementP implements Serializable {

    private String purcDate;
    private String purcOrder;
    private List<GoodsList> entryDetailList;

    public String getPurcDate() {
        return purcDate;
    }

    public void setPurcDate(String purcDate) {
        this.purcDate = purcDate;
    }

    public String getPurcOrder() {
        return purcOrder;
    }

    public void setPurcOrder(String purcOrder) {
        this.purcOrder = purcOrder;
    }

    public List<GoodsList> getEntryDetailList() {
        return entryDetailList;
    }

    public void setEntryDetailList(List<GoodsList> entryDetailList) {
        this.entryDetailList = entryDetailList;
    }

    public static class GoodsList implements Serializable{
        private int supplierId;
        private int goodsId;
        private int num;
        private double unitPrice;
        private double amount;
        private int payType;
        private String payDate;
        private String memo;

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }
}
