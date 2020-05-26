package com.bian.dan.blr.persenter.audit;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.bian.dan.blr.R;
import com.google.gson.Gson;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.AuditOutBoundP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

public class AuditPersenter {

    private Activity activity;

    public AuditPersenter(Activity activity){
        this.activity=activity;
    }


    /**
     * 显示驳回弹框
     * type:1出库单，  2生产计划，  3采购单，  4报销单
     */
    public void showAuditDialog(final AuditOutBoundP auditOutBoundP,final int type){
        View view= LayoutInflater.from(activity).inflate(R.layout.dialog_audit,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        final EditText etRemark=view.findViewById(R.id.et_remark);
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String remark=etRemark.getText().toString().trim();
                if(auditOutBoundP.getState()==2 && TextUtils.isEmpty(remark)){
                    ToastUtil.showLong("请输入审核意见");
                    return;
                }
                popupWindow.dismiss();
                auditOutBoundP.setProp4(remark);
                switch (type){
                    case 1:
                         AuditOutBound(auditOutBoundP);
                         break;
                    case 2:
                         AuditPlan(auditOutBoundP);
                         break;
                    case 3:
                         AuditProcurement(auditOutBoundP);
                         break;
                    case 4:
                        AuditFinancial(auditOutBoundP);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    /**
     * 出库单审核
     * @param auditOutBoundP
     */
    public void AuditOutBound(final AuditOutBoundP auditOutBoundP){
        DialogUtil.showProgress(activity,"数据提交中");
        LogUtils.e(new Gson().toJson(auditOutBoundP)+"+++++++++++++++++++");
        HttpMethod.AuditOutBound(auditOutBoundP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    Intent intent=new Intent();
                    intent.putExtra("outboundId",auditOutBoundP.getId());
                    activity.finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 生产计划审核
     * @param auditOutBoundP
     */
    public void AuditPlan(final AuditOutBoundP auditOutBoundP){
        DialogUtil.showProgress(activity,"数据提交中");
        LogUtils.e(new Gson().toJson(auditOutBoundP)+"+++++++++++++++++++");
        HttpMethod.AuditPlan(auditOutBoundP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    Intent intent=new Intent();
                    activity.setResult(1000,intent);
                    activity.finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            public void onFail(Throwable t) {

            }
        });
    }



    /**
     * 采购单审核
     * @param auditOutBoundP
     */
    public void AuditProcurement(final AuditOutBoundP auditOutBoundP){
        DialogUtil.showProgress(activity,"数据提交中");
        LogUtils.e(new Gson().toJson(auditOutBoundP)+"+++++++++++++++++++");
        HttpMethod.AuditProcurement(auditOutBoundP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    Intent intent=new Intent();
                    activity.setResult(1000,intent);
                    activity.finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 财务审核
     * @param auditOutBoundP
     */
    public void AuditFinancial(final AuditOutBoundP auditOutBoundP){
        DialogUtil.showProgress(activity,"数据提交中");
        LogUtils.e(new Gson().toJson(auditOutBoundP)+"+++++++++++++++++++");
        HttpMethod.AuditFinancial(auditOutBoundP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    Intent intent=new Intent();
                    activity.setResult(1000,intent);
                    activity.finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
