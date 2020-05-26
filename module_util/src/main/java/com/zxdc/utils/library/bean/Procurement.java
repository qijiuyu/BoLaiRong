package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Procurement  extends BaseBean{

    private ProcurementBean data;

    public ProcurementBean getData() {
        return data;
    }

    public void setData(ProcurementBean data) {
        this.data = data;
    }

    public static class ProcurementBean implements Serializable{

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
        private String purcName="";
        private String purcDate="";
        private String purcOrder="";
        private String createDate="";
        private int state;
        private String stateStr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPurcName() {
            return purcName;
        }

        public void setPurcName(String purcName) {
            this.purcName = purcName;
        }

        public String getPurcDate() {
            return purcDate;
        }

        public void setPurcDate(String purcDate) {
            this.purcDate = purcDate;
        }

        public String getPurcOrder() {
            return purcOrder;
        }

        public void setPurcOrder(String purcOrder) {
            this.purcOrder = purcOrder;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getStateStr() {
            return stateStr;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }
    }
}
