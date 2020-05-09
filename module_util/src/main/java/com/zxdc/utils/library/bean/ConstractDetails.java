package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class ConstractDetails extends BaseBean {

    private DetailsBean contract;
    private List<FileBean> fileList;

    public DetailsBean getContract() {
        return contract;
    }

    public void setContract(DetailsBean contract) {
        this.contract = contract;
    }

    public List<FileBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileBean> fileList) {
        this.fileList = fileList;
    }

    public static class DetailsBean implements Serializable{
        private int id;
        private int customerId;//客户id，关联客户表
        private String customerName;//客户名称
        private String signedTime;//签订时间
        private double amount;//订单金额
        private int payType;//支付方式： 1，全款 2，分期
        private int assignerId;//指派人id
        private String assignerName;//指派人名称
        private int createId;//创建人id
        private int updateId;//修改人id
        private int invoice;//是否开票 0，否 1，是
        private int sellers;//售卖公司 1，博徕荣 2，立钻
        private String createDate;
        private String updateDate;
        private String prop2;//合同编码
        private String prop3;//出库单号
        private int prop4;//发货状态（0：未发货 1：部分发货 2：全部发货）
        private int state;//审核状态（0：未审核 1：通过审核 2：审核未通过）
        private int approvalId;//	审批人id
        private String approvalName;//审批人名称
        private String approvalDate;//审批时间
        private String createName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getSignedTime() {
            return signedTime;
        }

        public void setSignedTime(String signedTime) {
            this.signedTime = signedTime;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public int getAssignerId() {
            return assignerId;
        }

        public void setAssignerId(int assignerId) {
            this.assignerId = assignerId;
        }

        public String getAssignerName() {
            return assignerName;
        }

        public void setAssignerName(String assignerName) {
            this.assignerName = assignerName;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public int getUpdateId() {
            return updateId;
        }

        public void setUpdateId(int updateId) {
            this.updateId = updateId;
        }

        public int getInvoice() {
            return invoice;
        }

        public void setInvoice(int invoice) {
            this.invoice = invoice;
        }

        public int getSellers() {
            return sellers;
        }

        public void setSellers(int sellers) {
            this.sellers = sellers;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getProp2() {
            return prop2;
        }

        public void setProp2(String prop2) {
            this.prop2 = prop2;
        }

        public String getProp3() {
            return prop3;
        }

        public void setProp3(String prop3) {
            this.prop3 = prop3;
        }

        public int getProp4() {
            return prop4;
        }

        public void setProp4(int prop4) {
            this.prop4 = prop4;
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

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }
    }

}
