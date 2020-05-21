package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddPutStorageP implements Serializable {

    private int id;
    private List<EntryList> entryDetailList;
    private List<WasteList> oddsDetailList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<EntryList> getEntryDetailList() {
        return entryDetailList;
    }

    public void setEntryDetailList(List<EntryList> entryDetailList) {
        this.entryDetailList = entryDetailList;
    }

    public List<WasteList> getOddsDetailList() {
        return oddsDetailList;
    }

    public void setOddsDetailList(List<WasteList> oddsDetailList) {
        this.oddsDetailList = oddsDetailList;
    }

    public static class EntryList implements Serializable{
        private int goodsId;
        private int num;
        private int stockType;
        private String batchNo;
        private String memo;
        private String prop1;
        private String prop2;
        private String prop3;
        private String prop4;

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

        public String getProp1() {
            return prop1;
        }

        public void setProp1(String prop1) {
            this.prop1 = prop1;
        }

        public String getProp2() {
            return prop2;
        }

        public void setProp2(String prop2) {
            this.prop2 = prop2;
        }

        public String getProp3() {
            return prop3;
        }

        public void setProp3(String prop3) {
            this.prop3 = prop3;
        }

        public String getProp4() {
            return prop4;
        }

        public void setProp4(String prop4) {
            this.prop4 = prop4;
        }
    }


    public static class WasteList implements Serializable{
        private int goodsId;
        private int num;
        private int stockType;
        private String batchNo;
        private int type;
        private int deptId;
        private int chargeId;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public int getChargeId() {
            return chargeId;
        }

        public void setChargeId(int chargeId) {
            this.chargeId = chargeId;
        }
    }
}
