package com.bian.dan.blr.activity.audit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.audit.customer.AuditCustomerActivity;
import com.bian.dan.blr.activity.audit.financial.AuditFinancialActivity;
import com.bian.dan.blr.activity.audit.outbound.AuditOutBoundActivity;
import com.bian.dan.blr.activity.audit.procurement.AuditProcurementActivity;
import com.bian.dan.blr.activity.audit.production.AuditProductionActivity;
import com.bian.dan.blr.activity.audit.selling.AuditSellingActivity;
import com.bian.dan.blr.fragment.audit.AuditSellingFragment;
import com.bian.dan.blr.persenter.audit.AuditPersenter;
import com.zxdc.utils.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 审核
 */
public class AuditActivity extends BaseActivity {

    @BindView(R.id.tv_ckd)
    TextView tvCkd;
    @BindView(R.id.tv_scjh)
    TextView tvScjh;
    @BindView(R.id.tv_cgd)
    TextView tvCgd;
    @BindView(R.id.tv_bxd)
    TextView tvBxd;
    @BindView(R.id.tv_khxz)
    TextView tvKhxz;
    @BindView(R.id.tv_smsqb)
    TextView tvSmsqb;
    private AuditPersenter auditPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit);
        ButterKnife.bind(this);
        auditPersenter=new AuditPersenter(this);
    }

    @OnClick({R.id.rel_ckd, R.id.rel_scjh, R.id.rel_cgd, R.id.rel_bxd,R.id.rel_khxz,R.id.rel_smsqb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //出库单
            case R.id.rel_ckd:
                setClass(AuditOutBoundActivity.class);
                break;
            //生产计划
            case R.id.rel_scjh:
                setClass(AuditProductionActivity.class);
                break;
            //采购单
            case R.id.rel_cgd:
                setClass(AuditProcurementActivity.class);
                break;
            //报销单
            case R.id.rel_bxd:
                setClass(AuditFinancialActivity.class);
                break;
            //客户新增
            case R.id.rel_khxz:
                setClass(AuditCustomerActivity.class);
                 break;
            //售卖申请表
            case R.id.rel_smsqb:
                setClass(AuditSellingActivity.class);
                 break;
            default:
                break;
        }
    }


    /**
     * 显示消息数量
     * @param type
     * @param total
     */
    public void showNewsNum(int type,int total){
        switch (type){
            case 1:
                 if(total>0){
                     tvCkd.setVisibility(View.VISIBLE);
                     tvCkd.setText(String.valueOf(total));
                 }else{
                     tvCkd.setVisibility(View.GONE);
                 }
                 break;
            case 2:
                if(total>0){
                    tvScjh.setVisibility(View.VISIBLE);
                    tvScjh.setText(String.valueOf(total));
                }else{
                    tvScjh.setVisibility(View.GONE);
                }
                break;
            case 3:
                if(total>0){
                    tvCgd.setVisibility(View.VISIBLE);
                    tvCgd.setText(String.valueOf(total));
                }else{
                    tvCgd.setVisibility(View.GONE);
                }
                break;
            case 4:
                if(total>0){
                    tvBxd.setVisibility(View.VISIBLE);
                    tvBxd.setText(String.valueOf(total));
                }else{
                    tvBxd.setVisibility(View.GONE);
                }
                break;
            case 5:
                if(total>0){
                    tvKhxz.setVisibility(View.VISIBLE);
                    tvKhxz.setText(String.valueOf(total));
                }else{
                    tvKhxz.setVisibility(View.GONE);
                }
                 break;
            case 6:
                if(total>0){
                    tvSmsqb.setVisibility(View.VISIBLE);
                    tvSmsqb.setText(String.valueOf(total));
                }else{
                    tvSmsqb.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        auditPersenter.getOutBoundList();
        auditPersenter.getAuditFinancialList();
        auditPersenter.getAuditProcurementList();
        auditPersenter.getPlanList();
        auditPersenter.getCustomer();
        auditPersenter.getSellingList();
    }
}
