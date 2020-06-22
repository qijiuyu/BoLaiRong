package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SellingOutBound extends BaseBean {

    private SellingBean data;

    public SellingBean getData() {
        return data;
    }

    public void setData(SellingBean data) {
        this.data = data;
    }

    public static class SellingBean implements Serializable{

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
        private int approvalId;
        private String approvalDate="";
        private String approvalName="";
        private String sellName="";
        private String sellTime="";
        private int status;
        private int state;
        private String createDate="";
        private int createId;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getApprovalId() {
            return approvalId;
        }

        public void setApprovalId(int approvalId) {
            this.approvalId = approvalId;
        }

        public String getApprovalDate() {
            return approvalDate;
        }

        public void setApprovalDate(String approvalDate) {
            this.approvalDate = approvalDate;
        }

        public String getApprovalName() {
            return approvalName;
        }

        public void setApprovalName(String approvalName) {
            this.approvalName = approvalName;
        }

        public String getSellName() {
            return sellName;
        }

        public void setSellName(String sellName) {
            this.sellName = sellName;
        }

        public String getSellTime() {
            return sellTime;
        }

        public void setSellTime(String sellTime) {
            this.sellTime = sellTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }
    }
}
