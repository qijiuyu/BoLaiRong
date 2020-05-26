package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Financial extends BaseBean{

    private FinancialBean data;

    public FinancialBean getData() {
        return data;
    }

    public void setData(FinancialBean data) {
        this.data = data;
    }

    public static class FinancialBean implements Serializable{

        private List<ListBean> rows;

        private int total;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getRows() {
            return rows;
        }

        public void setRows(List<ListBean> rows) {
            this.rows = rows;
        }
    }

    public static class ListBean implements Serializable{
        private int id;
        private int userId;
        private String name="";
        private double amount;
        private int state;
        private String stateStr="";
        private int approvalId;
        private String approvalName="";
        private String approvalDate="";
        private String account="";
        private String createName="";
        private String createDate="";
        private String memo="";
        private String openBankStr="";
        private String financeName="";
        private double prop2;
        private String prop3="";
        private String prop5="";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getApprovalId() {
            return approvalId;
        }

        public void setApprovalId(int approvalId) {
            this.approvalId = approvalId;
        }

        public String getApprovalName() {
            return approvalName;
        }

        public void setApprovalName(String approvalName) {
            this.approvalName = approvalName;
        }

        public String getApprovalDate() {
            return approvalDate;
        }

        public void setApprovalDate(String approvalDate) {
            this.approvalDate = approvalDate;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getStateStr() {
            return stateStr;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }

        public String getOpenBankStr() {
            return openBankStr;
        }

        public void setOpenBankStr(String openBankStr) {
            this.openBankStr = openBankStr;
        }

        public String getFinanceName() {
            return financeName;
        }

        public void setFinanceName(String financeName) {
            this.financeName = financeName;
        }

        public double getProp2() {
            return prop2;
        }

        public void setProp2(double prop2) {
            this.prop2 = prop2;
        }

        public String getProp3() {
            return prop3;
        }

        public void setProp3(String prop3) {
            this.prop3 = prop3;
        }

        public String getProp5() {
            return prop5;
        }

        public void setProp5(String prop5) {
            this.prop5 = prop5;
        }
    }
}
