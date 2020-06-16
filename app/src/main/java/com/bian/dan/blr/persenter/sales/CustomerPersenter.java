package com.bian.dan.blr.persenter.sales;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.CustomerDetailsActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.CustomerAuditP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class CustomerPersenter {

    private CustomerDetailsActivity activity;

    public CustomerPersenter(CustomerDetailsActivity activity){
        this.activity=activity;
    }

    /**
     * 驳回审核
     */
    public void showNoAudit(final Customer customer){
        View view= LayoutInflater.from(activity).inflate(R.layout.dialog_audit,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        final EditText etRemark=view.findViewById(R.id.et_remark);
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String remark=etRemark.getText().toString().trim();
                if(TextUtils.isEmpty(remark)){
                    ToastUtil.showLong("请输入驳回意见");
                    return;
                }
                popupWindow.dismiss();
                CustomerAuditP customerAuditP=new CustomerAuditP(customer.getId(),2,0,customer.getCreateId(),remark);
                //客户审核
                auditCustomer(customerAuditP);
            }
        });
    }



    /**
     * 审核同意
     */
    public void showOkAudit(final Customer customer){
        View view= LayoutInflater.from(activity).inflate(R.layout.dialog_customer_audit_ok,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        final EditText etMoney=view.findViewById(R.id.et_money);
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String money=etMoney.getText().toString().trim();
                if(TextUtils.isEmpty(money)){
                    ToastUtil.showLong("请输入奖励金额");
                    return;
                }
                popupWindow.dismiss();
                CustomerAuditP customerAuditP=new CustomerAuditP(customer.getId(),1,Double.parseDouble(money),customer.getCreateId(),null);
                //客户审核
                auditCustomer(customerAuditP);
            }
        });
    }


    /**
     * 客户审核
     */
    public void auditCustomer(CustomerAuditP customerAuditP){
        DialogUtil.showProgress(activity,"审核中");
        HttpMethod.auditCustomer(customerAuditP, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    //刷新详情
                    activity.getCustomerDetails();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }

}
