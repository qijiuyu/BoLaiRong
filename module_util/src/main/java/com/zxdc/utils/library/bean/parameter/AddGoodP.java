package com.zxdc.utils.library.bean.parameter;

public class AddGoodP {

    private int goodsId;
    private int num;
    private String prop1;
    private String prop2;
    private String memo;

    public AddGoodP(){}

    public AddGoodP(int goodsId, int num, String prop1, String prop2) {
        this.goodsId = goodsId;
        this.num = num;
        this.prop1 = prop1;
        this.prop2 = prop2;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
