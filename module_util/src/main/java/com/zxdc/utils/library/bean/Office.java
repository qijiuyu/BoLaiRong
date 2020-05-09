package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Office extends BaseBean {

    private OfficeBean page;

    public OfficeBean getPage() {
        return page;
    }

    public void setPage(OfficeBean page) {
        this.page = page;
    }

    public static class OfficeBean implements Serializable{

        private List<ListBean> rows;

        public List<ListBean> getRows() {
            return rows;
        }

        public void setRows(List<ListBean> rows) {
            this.rows = rows;
        }
    }


    public static class ListBean implements Serializable{
        private int userId;
        private String name;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
