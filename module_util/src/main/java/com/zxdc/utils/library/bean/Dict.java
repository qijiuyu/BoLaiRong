package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Dict extends BaseBean {

    private List<DictBean> list;

    public List<DictBean> getList() {
        return list;
    }

    public void setList(List<DictBean> list) {
        this.list = list;
    }

    public static class DictBean implements Serializable{
        private int id;
        private String name="";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
