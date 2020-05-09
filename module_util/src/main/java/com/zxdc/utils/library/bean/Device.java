package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Device extends BaseBean {

    private DeviceBean data;

    public DeviceBean getData() {
        return data;
    }

    public void setData(DeviceBean data) {
        this.data = data;
    }

    public static class DeviceBean implements Serializable{

        private List<ListBean> rows;

        public List<ListBean> getRows() {
            return rows;
        }

        public void setRows(List<ListBean> rows) {
            this.rows = rows;
        }
    }


    public static class ListBean implements Serializable{
        private int id;//设备id
        private int deptId;//部门id
        private String deptName;//部门名称
        private int equipType;//设备类型id
        private String equipName;//设备名称
        private String typeName;//设备类型
        private String spec;//规格型号
        private String code;//设备编码
        private String manufacturers;//生产厂家
        private String purcTime;//采购时间
        private double amount;
        private String memo;
        private int state;//设备状态：1，使用中 2，已出售

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public int getEquipType() {
            return equipType;
        }

        public void setEquipType(int equipType) {
            this.equipType = equipType;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getManufacturers() {
            return manufacturers;
        }

        public void setManufacturers(String manufacturers) {
            this.manufacturers = manufacturers;
        }

        public String getPurcTime() {
            return purcTime;
        }

        public void setPurcTime(String purcTime) {
            this.purcTime = purcTime;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getEquipName() {
            return equipName;
        }

        public void setEquipName(String equipName) {
            this.equipName = equipName;
        }
    }
}
