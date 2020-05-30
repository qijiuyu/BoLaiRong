package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class SalesOutBoundP implements Serializable {

    private int id;
    private String flag="1";
    private int expressType;
    private String expressNo;
    private int status;
    private List<GoodList> saleDetailList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getExpressType() {
        return expressType;
    }

    public void setExpressType(int expressType) {
        this.expressType = expressType;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<GoodList> getSaleDetailList() {
        return saleDetailList;
    }

    public void setSaleDetailList(List<GoodList> saleDetailList) {
        this.saleDetailList = saleDetailList;
    }

    public static class GoodList implements Serializable{
        private int goodsId;
        private int num;
        private int stockType;
        private String batchNo;
        private String memo;

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

        public int getStockType() {
            return stockType;
        }

        public void setStockType(int stockType) {
            this.stockType = stockType;
        }

        public String getBatchNo() {
            return batchNo;
        }

        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }
}
