package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class ProductProgress extends BaseBean {

    private ProductBean data;

    public ProductBean getData() {
        return data;
    }

    public void setData(ProductBean data) {
        this.data = data;
    }

    public static class ProductBean implements Serializable{
        private int id;
        private int planId;
        private String planCode="";
        private int deptId;
        private String deptName="";
        private int outStatus;
        private String outStatusStr="";
        private int entryStatus;
        private String entryStatusStr="";
        private String memo="";
        private String createName="";
        private String createDate="";
        private String updateName="";
        private String updateDate="";
        private String prop5="";
        private String entryName="";
        private String prop2="";
        private List<OutBoundList> requireDetailList;
        private List<EntryList> entryDetailList;
        private List<WasteList> oddsDetailList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPlanId() {
            return planId;
        }

        public void setPlanId(int planId) {
            this.planId = planId;
        }

        public String getPlanCode() {
            return planCode;
        }

        public void setPlanCode(String planCode) {
            this.planCode = planCode;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public int getOutStatus() {
            return outStatus;
        }

        public void setOutStatus(int outStatus) {
            this.outStatus = outStatus;
        }

        public String getOutStatusStr() {
            return outStatusStr;
        }

        public void setOutStatusStr(String outStatusStr) {
            this.outStatusStr = outStatusStr;
        }

        public int getEntryStatus() {
            return entryStatus;
        }

        public void setEntryStatus(int entryStatus) {
            this.entryStatus = entryStatus;
        }

        public String getEntryStatusStr() {
            return entryStatusStr;
        }

        public void setEntryStatusStr(String entryStatusStr) {
            this.entryStatusStr = entryStatusStr;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
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

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getProp5() {
            return prop5;
        }

        public void setProp5(String prop5) {
            this.prop5 = prop5;
        }

        public List<OutBoundList> getRequireDetailList() {
            return requireDetailList;
        }

        public void setRequireDetailList(List<OutBoundList> requireDetailList) {
            this.requireDetailList = requireDetailList;
        }

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
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

        public String getEntryName() {
            return entryName;
        }

        public void setEntryName(String entryName) {
            this.entryName = entryName;
        }

        public String getProp2() {
            return prop2;
        }

        public void setProp2(String prop2) {
            this.prop2 = prop2;
        }
    }


    public static class OutBoundList implements Serializable{
        private String goodsName="";
        private String stockTypeStr="";
        private String spec="";
        private String brand="";
        private String unitStr="";
        private String typeStr="";
        private int num;
        private String batchNo="";
        private String memo="";
        private String prop2="";
        private String parentStockTypeStr="";

        public String getParentStockTypeStr() {
            return parentStockTypeStr;
        }

        public void setParentStockTypeStr(String parentStockTypeStr) {
            this.parentStockTypeStr = parentStockTypeStr;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getStockTypeStr() {
            return stockTypeStr;
        }

        public void setStockTypeStr(String stockTypeStr) {
            this.stockTypeStr = stockTypeStr;
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

        public String getUnitStr() {
            return unitStr;
        }

        public void setUnitStr(String unitStr) {
            this.unitStr = unitStr;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(String typeStr) {
            this.typeStr = typeStr;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
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

        public String getProp2() {
            return prop2;
        }

        public void setProp2(String prop2) {
            this.prop2 = prop2;
        }
    }


    public static class EntryList implements Serializable{
        private int id;
        private String goodsName="";
        private int stockType;
        private String stockTypeStr="";
        private String spec="";
        private String brand="";
        private String unitStr="";
        private String goodsTypeStr="";
        private String batchNo="";
        private int num;
        private String memo="";
        private String createName="";
        private String createDate="";
        private String parentStockTypeStr="";

        public String getParentStockTypeStr() {
            return parentStockTypeStr;
        }

        public void setParentStockTypeStr(String parentStockTypeStr) {
            this.parentStockTypeStr = parentStockTypeStr;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStockType() {
            return stockType;
        }

        public void setStockType(int stockType) {
            this.stockType = stockType;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getStockTypeStr() {
            return stockTypeStr;
        }

        public void setStockTypeStr(String stockTypeStr) {
            this.stockTypeStr = stockTypeStr;
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

        public String getUnitStr() {
            return unitStr;
        }

        public void setUnitStr(String unitStr) {
            this.unitStr = unitStr;
        }

        public String getGoodsTypeStr() {
            return goodsTypeStr;
        }

        public void setGoodsTypeStr(String goodsTypeStr) {
            this.goodsTypeStr = goodsTypeStr;
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

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
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
    }


    public static class WasteList implements Serializable{
        private int id;
        private String goodsName="";
        private int num;
        private String batchNo="";
        private String spec="";
        private String brand="";
        private String unitsType="";
        private int type;
        private String typeStr="";
        private int deptId;
        private String deptName="";
        private int chargeId;
        private String chargeName="";
        private String createDate="";
        private String memo="";
        private String createName="";
        private String stockTypeStr="";
        private int stockType;
        private String parentStockTypeStr="";

        public String getParentStockTypeStr() {
            return parentStockTypeStr;
        }

        public void setParentStockTypeStr(String parentStockTypeStr) {
            this.parentStockTypeStr = parentStockTypeStr;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStockTypeStr() {
            return stockTypeStr;
        }

        public void setStockTypeStr(String stockTypeStr) {
            this.stockTypeStr = stockTypeStr;
        }

        public int getStockType() {
            return stockType;
        }

        public void setStockType(int stockType) {
            this.stockType = stockType;
        }

        public int getChargeId() {
            return chargeId;
        }

        public void setChargeId(int chargeId) {
            this.chargeId = chargeId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(String typeStr) {
            this.typeStr = typeStr;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getChargeName() {
            return chargeName;
        }

        public void setChargeName(String chargeName) {
            this.chargeName = chargeName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getBatchNo() {
            return batchNo;
        }

        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
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

        public String getUnitsType() {
            return unitsType;
        }

        public void setUnitsType(String unitsType) {
            this.unitsType = unitsType;
        }
    }
}
