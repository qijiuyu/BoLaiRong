package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddOutBoundByProductP implements Serializable {

    private int planId;
    private int deptId;
    private List<GoodList> requireDetailList;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public List<GoodList> getRequireDetailList() {
        return requireDetailList;
    }

    public void setRequireDetailList(List<GoodList> requireDetailList) {
        this.requireDetailList = requireDetailList;
    }

    public static class GoodList implements Serializable{
        private int goodsId;
        private int num;
        private int stockType;
        private String batchNo;
        private String prop2;
        private String memo;

        public GoodList(){}

        public GoodList(int goodsId, int num, int stockType, String batchNo, String prop2, String memo) {
            this.goodsId = goodsId;
            this.num = num;
            this.stockType = stockType;
            this.batchNo = batchNo;
            this.prop2 = prop2;
            this.memo = memo;
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
    }
}
