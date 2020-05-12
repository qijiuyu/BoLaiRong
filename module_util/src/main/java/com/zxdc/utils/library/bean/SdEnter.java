package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SdEnter extends BaseBean {

    private SdEnterBean data;

    public SdEnterBean getData() {
        return data;
    }

    public void setData(SdEnterBean data) {
        this.data = data;
    }

    public static class SdEnterBean implements Serializable{

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
        private String memo="";
        private String createName="";
        private String purcName="";
        private String createDate="";
        private String purcDate="";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getPurcName() {
            return purcName;
        }

        public void setPurcName(String purcName) {
            this.purcName = purcName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getPurcDate() {
            return purcDate;
        }

        public void setPurcDate(String purcDate) {
            this.purcDate = purcDate;
        }
    }
}
