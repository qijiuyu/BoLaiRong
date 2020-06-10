package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class OutBoundDetails extends BaseBean {

    private List<GoodList> goodsList;
    private List<SaleDetails> saleDetailList;
    private DetailsBean outOrder;

    public List<GoodList> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodList> goodsList) {
        this.goodsList = goodsList;
    }

    public List<SaleDetails> getSaleDetailList() {
        return saleDetailList;
    }

    public void setSaleDetailList(List<SaleDetails> saleDetailList) {
        this.saleDetailList = saleDetailList;
    }

    public DetailsBean getOutOrder() {
        return outOrder;
    }

    public void setOutOrder(DetailsBean outOrder) {
        this.outOrder = outOrder;
    }

    public static class GoodList implements Serializable{
        private int id;
        private int goodsId;
        //物料名称
        private String goodsName="";
        //物料型号
        private String spec="";
        private int units;
        //单位
        private String unitStr="";
        //规格
        private String brand="";
        private int type;
        //物料类别
        private String typeStr="";
        //数量
        private int num;
        //单价
        private String prop1="0";
        //总价
        private String prop2="0";
        //备注
        private String memo="";
        //是否开票
        private String prop3="";

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getUnits() {
            return units;
        }

        public void setUnits(int units) {
            this.units = units;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getProp3() {
            return prop3;
        }

        public void setProp3(String prop3) {
            this.prop3 = prop3;
        }
    }


    public static class SaleDetails implements Serializable{
        private int id;
        private int outId;
        private String goodsName="";
        private String brand="";
        private String spec="";
        private int units;
        private String unitStr="";
        private int stockType;
        private String stockTypeStr="";
        private String batchNo="";
        private int num;
        private double prop1;
        private double prop2;
        private String createName="";
        private String createDate="";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOutId() {
            return outId;
        }

        public void setOutId(int outId) {
            this.outId = outId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
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

        public int getUnits() {
            return units;
        }

        public void setUnits(int units) {
            this.units = units;
        }

        public String getUnitStr() {
            return unitStr;
        }

        public void setUnitStr(String unitStr) {
            this.unitStr = unitStr;
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

        public double getProp1() {
            return prop1;
        }

        public void setProp1(double prop1) {
            this.prop1 = prop1;
        }

        public double getProp2() {
            return prop2;
        }

        public void setProp2(double prop2) {
            this.prop2 = prop2;
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

    public static class DetailsBean implements Serializable{
        private int id;
        private int prop1;
        private String salesName="";
        private String prop2="";
        private int customerId;
        private String outDate="";
        private int payType;
        private String receivableDate="";
        private double unpaidAmount;
        private double addAmount;
        private String orderNum="";
        private int state;
        private String stateStr;
        private String memo="";
        private int createId;
        private int updateId;
        private String createDate="";
        private String updateDate="";
        private int status;
        private String statusStr="";
        private String expressTypeStr="";
        private String expressNo="";
        private String customerName="";
        private String contacts="";
        private String phone="";
        private String createName="";
        private String approveName="";
        private String postAddress="";
        private String prop5="";
        private String prop4="";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProp1() {
            return prop1;
        }

        public void setProp1(int prop1) {
            this.prop1 = prop1;
        }

        public String getSalesName() {
            return salesName;
        }

        public void setSalesName(String salesName) {
            this.salesName = salesName;
        }

        public String getProp2() {
            return prop2;
        }

        public void setProp2(String prop2) {
            this.prop2 = prop2;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getOutDate() {
            return outDate;
        }

        public void setOutDate(String outDate) {
            this.outDate = outDate;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getReceivableDate() {
            return receivableDate;
        }

        public void setReceivableDate(String receivableDate) {
            this.receivableDate = receivableDate;
        }

        public double getUnpaidAmount() {
            return unpaidAmount;
        }

        public void setUnpaidAmount(double unpaidAmount) {
            this.unpaidAmount = unpaidAmount;
        }

        public double getAddAmount() {
            return addAmount;
        }

        public void setAddAmount(double addAmount) {
            this.addAmount = addAmount;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public int getUpdateId() {
            return updateId;
        }

        public void setUpdateId(int updateId) {
            this.updateId = updateId;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public String getExpressTypeStr() {
            return expressTypeStr;
        }

        public void setExpressTypeStr(String expressTypeStr) {
            this.expressTypeStr = expressTypeStr;
        }

        public String getExpressNo() {
            return expressNo;
        }

        public void setExpressNo(String expressNo) {
            this.expressNo = expressNo;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getApproveName() {
            return approveName;
        }

        public void setApproveName(String approveName) {
            this.approveName = approveName;
        }

        public String getPostAddress() {
            return postAddress;
        }

        public void setPostAddress(String postAddress) {
            this.postAddress = postAddress;
        }

        public String getProp5() {
            return prop5;
        }

        public void setProp5(String prop5) {
            this.prop5 = prop5;
        }

        public String getProp4() {
            return prop4;
        }

        public void setProp4(String prop4) {
            this.prop4 = prop4;
        }

        public String getStateStr() {
            return stateStr;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }
    }


}
