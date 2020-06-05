package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddLedTableP implements Serializable {

    private int deptId;
    private int receiveId;
    private List<MaterialList> detailList;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public List<MaterialList> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<MaterialList> detailList) {
        this.detailList = detailList;
    }

    public static class MaterialList implements Serializable{
        private int goodsId;
        private int stockType;
        private String batchNo;
        private int num;
        private int type;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
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

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
