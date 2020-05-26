package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class FinancialDetails extends BaseBean {

    private DetailsBean data;

    public DetailsBean getData() {
        return data;
    }

    public void setData(DetailsBean data) {
        this.data = data;
    }

    public static class DetailsBean implements Serializable{
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
        private String mobile="";
        private int createId;
        private String prop4="";
        private List<FileBean> fileList;

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

        public String getStateStr() {
            return stateStr;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
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

        public List<FileBean> getFileList() {
            return fileList;
        }

        public void setFileList(List<FileBean> fileList) {
            this.fileList = fileList;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public String getProp4() {
            return prop4;
        }

        public void setProp4(String prop4) {
            this.prop4 = prop4;
        }
    }
}
