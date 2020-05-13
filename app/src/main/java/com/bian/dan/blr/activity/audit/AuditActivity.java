package com.bian.dan.blr.activity.audit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rel_ckd, R.id.rel_scjh, R.id.rel_cgd, R.id.rel_bxd})
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
            default:
                break;
        }
    }
}
