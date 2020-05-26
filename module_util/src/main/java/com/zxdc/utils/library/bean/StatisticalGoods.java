package com.zxdc.utils.library.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StatisticalGoods extends BaseBean {

    private  GoodBean data;

    public GoodBean getData() {
        return data;
    }

    public void setData(GoodBean data) {
        this.data = data;
    }

    public static class GoodBean implements Serializable{
        @SerializedName("棕色")
        private int name1;
        @SerializedName("黑色")
        private int name2;
        @SerializedName("焊接")
        private int name3;
        @SerializedName("琥珀色")
        private int name4;
        @SerializedName("非标")
        private int name5;
        @SerializedName("低端")
        private int name6;
        @SerializedName("整体聚晶")
        private int name7;

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
    }
}
