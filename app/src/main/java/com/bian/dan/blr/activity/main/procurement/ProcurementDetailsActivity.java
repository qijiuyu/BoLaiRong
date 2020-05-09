package com.bian.dan.blr.activity.main.procurement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.procurement.AddProductAdapter3;
import com.bian.dan.blr.adapter.procurement.ProcuDetailsEnterAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 采购单详情
 */
public class ProcurementDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_pro_name)
    TextView tvProName;
    @BindView(R.id.tv_pro_time)
    TextView tvProTime;
    @BindView(R.id.tv_pro_code)
    TextView tvProCode;
    @BindView(R.id.tv_apply_time)
    TextView tvApplyTime;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.tv_product_money)
    TextView tvProductMoney;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    @BindView(R.id.tv_audit)
    TextView tvAudit;
    @BindView(R.id.tv_audit_time)
    TextView tvAuditTime;
    @BindView(R.id.tv_audit_result)
    TextView tvAuditResult;
    @BindView(R.id.tv_enter)
    TextView tvEnter;
    @BindView(R.id.tv_enter_time)
    TextView tvEnterTime;
    @BindView(R.id.list_outbound)
    MeasureListView listOutbound;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    private AddProductAdapter3 addProductAdapter3;
    private ProcuDetailsEnterAdapter procuDetailsEnterAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurement_details);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");

        addProductAdapter3=new AddProductAdapter3(this);
        listView.setAdapter(addProductAdapter3);

        procuDetailsEnterAdapter=new ProcuDetailsEnterAdapter(this);
        listOutbound.setAdapter(procuDetailsEnterAdapter);

    }
}
