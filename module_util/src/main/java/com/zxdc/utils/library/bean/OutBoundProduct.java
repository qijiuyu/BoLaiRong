package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class OutBoundProduct extends BaseBean{

    private ProductBean data;

    public ProductBean getData() {
        return data;
    }

    public void setData(ProductBean data) {
        this.data = data;
    }

    public static class ProductBean implements Serializable{

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
        private String planCode="";
        private String deptName="";
        private int outStatus;
        private String outStatusStr="";
        private int entryStatus;
        private String entryStatusStr="";
        private String createName="";
        private String createDate="";

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
}
