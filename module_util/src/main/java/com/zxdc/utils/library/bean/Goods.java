package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class Goods implements Serializable {

    private int id;
    //物料名称
    private String name="";
    //物料型号
    private String spec="";
    private int units;
    //单位
    private String unitStr="";
    //规格
    private String brand="";
    /**
     * 物料类型
     * 废料类型
     */
    private int type;
    //物料类别
    private String typeStr="";
    //数量
    private int num;
    //单价
    private String price="";
    //总价
    private String totalMoney="";
    //备注
    private String memo="";
    //是否开票
    private String isInvoice="";
    //地址
    private String address;
    //付款方式
    private int payType;
    //付款时间
    private String payTime;
    //供应商id
    private int companyId;
    //供应商名称
    private String company;
    //联系人
    private String contract;
    //电话
    private String mobile;
    //仓库类型
    private int stockType;
    //商品批号
    private String batchNo;
    //交付日期
    private String deliveryTime;
    //奖励金额
    private String rewardMoney;
    //奖励说明
    private String rewardDes;
    //罚款金额
    private String fineMoney;
    //罚款说明
    private String fineDes;
    //责任部门id
    private int deptId;
    //部门名称
    private String deptName;
    //责任人id
    private int chargeId;
    //余废料类别名称
    private String waste;


    public  Goods(){}

    public Goods(int id,String name, String spec, String unitStr, String brand, int num, String price, String totalMoney, String memo, String isInvoice) {
        this.id=id;
        this.name = name;
        this.spec = spec;
        this.unitStr = unitStr;
        this.brand = brand;
        this.num = num;
        this.price = price;
        this.totalMoney = totalMoney;
        this.memo = memo;
        this.isInvoice = isInvoice;
    }

    public Goods(int id, String name, String spec, String brand, int num, String memo) {
        this.id = id;
        this.name = name;
        this.spec = spec;
        this.brand = brand;
        this.num = num;
        this.memo = memo;
    }

    public Goods(int id, String name, String spec, String unitStr, String brand, int num, String price, String totalMoney, String memo, int stockType, String batchNo) {
        this.id = id;
        this.name = name;
        this.spec = spec;
        this.unitStr = unitStr;
        this.brand = brand;
        this.num = num;
        this.price = price;
        this.totalMoney = totalMoney;
        this.memo = memo;
        this.stockType = stockType;
        this.batchNo = batchNo;
    }


    public Goods(int id, String name, String spec, String unitStr, String brand, int num, String deliveryTime,String memo, int stockType, String batchNo) {
        this.id = id;
        this.name = name;
        this.spec = spec;
        this.unitStr = unitStr;
        this.brand = brand;
        this.num = num;
        this.deliveryTime=deliveryTime;
        this.memo = memo;
        this.stockType = stockType;
        this.batchNo = batchNo;
    }


    public Goods(int id, String name, String spec, String unitStr, String brand, String typeStr, int num, String memo, int stockType, String batchNo, String rewardMoney,String rewardDes, String fineMoney,String fineDes) {
        this.id = id;
        this.name = name;
        this.spec = spec;
        this.unitStr = unitStr;
        this.brand = brand;
        this.typeStr = typeStr;
        this.num = num;
        this.memo = memo;
        this.stockType = stockType;
        this.batchNo = batchNo;
        this.rewardMoney = rewardMoney;
        this.rewardDes=rewardDes;
        this.fineMoney = fineMoney;
        this.fineDes=fineDes;
    }


    public Goods(int id, String name, String spec, String unitStr, String brand, int type,String waste, int num, int stockType, String batchNo, int deptId,String deptName, int chargeId) {
        this.id = id;
        this.name = name;
        this.spec = spec;
        this.unitStr = unitStr;
        this.brand = brand;
        this.type = type;
        this.waste=waste;
        this.num = num;
        this.stockType = stockType;
        this.batchNo = batchNo;
        this.deptId = deptId;
        this.deptName=deptName;
        this.chargeId = chargeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(String rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public String getFineMoney() {
        return fineMoney;
    }

    public void setFineMoney(String fineMoney) {
        this.fineMoney = fineMoney;
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

    public String getWaste() {
        return waste;
    }

    public void setWaste(String waste) {
        this.waste = waste;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRewardDes() {
        return rewardDes;
    }

    public void setRewardDes(String rewardDes) {
        this.rewardDes = rewardDes;
    }

    public String getFineDes() {
        return fineDes;
    }

    public void setFineDes(String fineDes) {
        this.fineDes = fineDes;
    }
}
