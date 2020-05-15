package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Dept extends BaseBean {

    private List<DeptBean> page;

    public List<DeptBean> getPage() {
        return page;
    }

    public void setPage(List<DeptBean> page) {
        this.page = page;
    }

    public static class DeptBean implements Serializable{
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
