package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class DeviceType extends BaseBean {

    private List<DeviceTypeBean> list;

    public List<DeviceTypeBean> getList() {
        return list;
    }

    public void setList(List<DeviceTypeBean> list) {
        this.list = list;
    }

    public static class DeviceTypeBean implements Serializable{
        private int id;
        private String name;

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
