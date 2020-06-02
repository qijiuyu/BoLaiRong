package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Wage extends BaseBean {

    private WageBean data;

    public WageBean getData() {
        return data;
    }

    public void setData(WageBean data) {
        this.data = data;
    }

    public static class WageBean implements Serializable{

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
        private String userName="";
        private int totalNum;
        private int rejectNum;
        private double pieceWage;
        private int saleNum;
        private double sales;
        private double cutWages;
        private double entryFee;
        private String month="";

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public int getRejectNum() {
            return rejectNum;
        }

        public void setRejectNum(int rejectNum) {
            this.rejectNum = rejectNum;
        }

        public double getPieceWage() {
            return pieceWage;
        }

        public void setPieceWage(double pieceWage) {
            this.pieceWage = pieceWage;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public double getSales() {
            return sales;
        }

        public void setSales(double sales) {
            this.sales = sales;
        }

        public double getCutWages() {
            return cutWages;
        }

        public void setCutWages(double cutWages) {
            this.cutWages = cutWages;
        }

        public double getEntryFee() {
            return entryFee;
        }

        public void setEntryFee(double entryFee) {
            this.entryFee = entryFee;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }
    }


    public static String str="{\n" +
            "    \"msg\": \"操作成功\",\n" +
            "    \"code\": 0,\n" +
            "    \"data\": {\n" +
            "        \"total\": 2,\n" +
            "        \"rows\": [\n" +
            "            {\n" +
            "                \"id\": 62,\n" +
            "                \"userId\": 2,\n" +
            "\t\t\t    \"userName\": \"张学友\",\n" +
            "                \"totalNum\": 900,\n" +
            "                \"rejectNum\": 0,\n" +
            "                \"pieceWage\": 2700,\n" +
            "                \"cutWages\": 0,\n" +
            "                \"memo\": \"测试数据\",\n" +
            "                \"createId\": 1,\n" +
            "                \"updateId\": 1,\n" +
            "                \"createDate\": \"2020-04-03 11:27:25\",\n" +
            "                \"updateDate\": \"2020-04-03 11:27:25\",\n" +
            "                \"deptName\": \"生产-配料\",\n" +
            "                \"deptId\": 3,\n" +
            "                \"month\": \"2020-03\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 63,\n" +
            "                \"userId\": 11,\n" +
            "\t\t\t    \"userName\": \"张学友\",\n" +
            "                \"totalNum\": 800,\n" +
            "                \"rejectNum\": 0,\n" +
            "                \"pieceWage\": 2400,\n" +
            "                \"memo\": \"测试数据\",\n" +
            "                \"createId\": 1,\n" +
            "                \"updateId\": 1,\n" +
            "                \"createDate\": \"2020-04-03 14:26:09\",\n" +
            "                \"updateDate\": \"2020-04-03 14:26:09\",\n" +
            "                \"deptName\": \"生产-配料\",\n" +
            "                \"deptId\": 3,\n" +
            "                \"month\": \"2020-03\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
}
