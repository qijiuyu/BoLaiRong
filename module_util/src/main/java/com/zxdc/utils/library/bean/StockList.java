package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class StockList extends BaseBean {

    private List<ListBean> page;

    public List<ListBean> getPage() {
        return page;
    }

    public void setPage(List<ListBean> page) {
        this.page = page;
    }

    public static class ListBean implements Serializable{
        private int id;
        private int parentId;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
