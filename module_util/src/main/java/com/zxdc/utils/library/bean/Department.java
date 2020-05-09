package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Department extends BaseBean {

    private List<DepartmentBean> page;

    public List<DepartmentBean> getPage() {
        return page;
    }

    public void setPage(List<DepartmentBean> page) {
        this.page = page;
    }

    public static class DepartmentBean implements Serializable{
        private int id;
        private int parentId;
        private String name;
        private int createId;
        private int updateId;

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

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public int getUpdateId() {
            return updateId;
        }

        public void setUpdateId(int updateId) {
            this.updateId = updateId;
        }
    }
}
