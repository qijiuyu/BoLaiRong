package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class PlanDetails extends BaseBean {

    private DetailsBean data;
    public DetailsBean getData() {
        return data;
    }

    public void setData(DetailsBean data) {
        this.data = data;
    }

    public static class DetailsBean implements Serializable{
        private int id;
        private String planCode="";
        private String deliveryDate="";
        private int status;
        private String statusStr="";
        private String approveName="";
        private String approveDate="";
        private String memo="";
        private int createId;
        private String createName="";
        private String createDate="";
        private String prop1="";
        private List<GoodBean> detailList;
        private List<ProgressBean> progressList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlanCode() {
            return planCode;
        }

        public void setPlanCode(String planCode) {
            this.planCode = planCode;
        }

        public String getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
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

        public String getApproveName() {
            return approveName;
        }

        public void setApproveName(String approveName) {
            this.approveName = approveName;
        }

        public String getApproveDate() {
            return approveDate;
        }

        public void setApproveDate(String approveDate) {
            this.approveDate = approveDate;
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

        public String getProp1() {
            return prop1;
        }

        public void setProp1(String prop1) {
            this.prop1 = prop1;
        }

        public List<GoodBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<GoodBean> detailList) {
            this.detailList = detailList;
        }

        public List<ProgressBean> getProgressList() {
            return progressList;
        }

        public void setProgressList(List<ProgressBean> progressList) {
            this.progressList = progressList;
        }
    }


    public static class GoodBean implements Serializable{
        private int id;
        private String goodsName="";
        private String spec="";
        private String unitStr="";
        private String brand="";
        private int num;
        private int finishNum;
        private String memo="";

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

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getFinishNum() {
            return finishNum;
        }

        public void setFinishNum(int finishNum) {
            this.finishNum = finishNum;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }


    public static class ProgressBean implements Serializable{
        private int id;
        private int planId;
        private int deptId;
        private String deptName="";
        private String statusStr="";
        private int status;

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

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

}
