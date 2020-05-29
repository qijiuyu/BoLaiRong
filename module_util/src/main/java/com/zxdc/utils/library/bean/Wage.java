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
}
