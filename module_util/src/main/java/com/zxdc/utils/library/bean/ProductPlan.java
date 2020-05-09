package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class ProductPlan extends BaseBean {

    private ProductPlanBean data;

    public ProductPlanBean getData() {
        return data;
    }

    public void setData(ProductPlanBean data) {
        this.data = data;
    }

    public static class ProductPlanBean implements Serializable{

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
        private String planCode;
        private String deliveryDate;
        private int status;
        private String statusStr;
        private String memo;
        private int approveId;
        private String createDate;

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

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getApproveId() {
            return approveId;
        }

        public void setApproveId(int approveId) {
            this.approveId = approveId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
