package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class LedTableDetails extends BaseBean {

    private DetailsBean data;

    public DetailsBean getData() {
        return data;
    }

    public void setData(DetailsBean data) {
        this.data = data;
    }

    public static class DetailsBean implements Serializable{
        private int id;
        private String deptName="";
        private String receiveName="";
        private String createName="";
        private String createDate="";
        private List<ListBean> detailList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public List<ListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<ListBean> detailList) {
            this.detailList = detailList;
        }
    }


    public static class ListBean implements Serializable{
        private int id;
        private int requireId;
        private int goodsId;
        private String goodsName="";
        private int stockType;
        private String parentStockTypeStr="";
        private String stockTypeStr="";
        private String batchNo="";
        private int num;
        private int type;
        private String typeValue="";
        private String unitStr="";
        private String brand="";
        private String spec="";

        public String getParentStockTypeStr() {
            return parentStockTypeStr;
        }

        public void setParentStockTypeStr(String parentStockTypeStr) {
            this.parentStockTypeStr = parentStockTypeStr;
        }

        public String getUnitStr() {
            return unitStr;
        }

        public void setUnitStr(String unitStr) {
            this.unitStr = unitStr;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRequireId() {
            return requireId;
        }

        public void setRequireId(int requireId) {
            this.requireId = requireId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getStockType() {
            return stockType;
        }

        public void setStockType(int stockType) {
            this.stockType = stockType;
        }

        public String getStockTypeStr() {
            return stockTypeStr;
        }

        public void setStockTypeStr(String stockTypeStr) {
            this.stockTypeStr = stockTypeStr;
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

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }
    }
}
