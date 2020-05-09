package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Inventory extends BaseBean {

    private InventoryBean data;

    public InventoryBean getData() {
        return data;
    }

    public void setData(InventoryBean data) {
        this.data = data;
    }

    public static class InventoryBean implements Serializable{
        private List<ListBean> rows;

        public List<ListBean> getRows() {
            return rows;
        }

        public void setRows(List<ListBean> rows) {
            this.rows = rows;
        }
    }


    public static class ListBean implements Serializable{
        private int id;
        private int goodsId;
        private int sumNum;
        private String goodsName;
        private String spec;
        private String brand;
        private String units;
        private String unitStr;
        private int goodsType;
        private String typeStr;
        private int lowerLimit;
        private int upperLimit;
        private String color;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getSumNum() {
            return sumNum;
        }

        public void setSumNum(int sumNum) {
            this.sumNum = sumNum;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }

        public String getUnitStr() {
            return unitStr;
        }

        public void setUnitStr(String unitStr) {
            this.unitStr = unitStr;
        }

        public int getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(int goodsType) {
            this.goodsType = goodsType;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(String typeStr) {
            this.typeStr = typeStr;
        }

        public int getLowerLimit() {
            return lowerLimit;
        }

        public void setLowerLimit(int lowerLimit) {
            this.lowerLimit = lowerLimit;
        }

        public int getUpperLimit() {
            return upperLimit;
        }

        public void setUpperLimit(int upperLimit) {
            this.upperLimit = upperLimit;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
