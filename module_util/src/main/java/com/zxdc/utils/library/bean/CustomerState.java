package com.zxdc.utils.library.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerState extends BaseBean {

    private  StateBean data;

    public StateBean getData() {
        return data;
    }

    public void setData(StateBean data) {
        this.data = data;
    }

    public static class StateBean implements Serializable{
        @SerializedName("潜在客户")
        private int name1;
        @SerializedName("死单客户")
        private int name2;
        @SerializedName("渠道客户")
        private int name3;
        @SerializedName("流失客户")
        private int name4;
        @SerializedName("意向客户")
        private int name5;
        @SerializedName("培养客户")
        private int name6;
        @SerializedName("成交客户")
        private int name7;
        @SerializedName("客户线索")
        private int name8;

        public int getName1() {
            return name1;
        }

        public void setName1(int name1) {
            this.name1 = name1;
        }

        public int getName2() {
            return name2;
        }

        public void setName2(int name2) {
            this.name2 = name2;
        }

        public int getName3() {
            return name3;
        }

        public void setName3(int name3) {
            this.name3 = name3;
        }

        public int getName4() {
            return name4;
        }

        public void setName4(int name4) {
            this.name4 = name4;
        }

        public int getName5() {
            return name5;
        }

        public void setName5(int name5) {
            this.name5 = name5;
        }

        public int getName6() {
            return name6;
        }

        public void setName6(int name6) {
            this.name6 = name6;
        }

        public int getName7() {
            return name7;
        }

        public void setName7(int name7) {
            this.name7 = name7;
        }

        public int getName8() {
            return name8;
        }

        public void setName8(int name8) {
            this.name8 = name8;
        }
    }
}
