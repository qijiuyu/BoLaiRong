package com.bian.dan.blr.activity.main.sales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Financial;
import com.zxdc.utils.library.view.MyGridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 财务报销详情
 */
public class FinancialDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_create_people)
    TextView tvCreatePeople;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_apply_peple)
    TextView tvApplyPeple;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.gridView)
    MyGridView gridView;
    @BindView(R.id.tv_audit_people)
    TextView tvAuditPeople;
    @BindView(R.id.tv_audit_time)
    TextView tvAuditTime;
    @BindView(R.id.tv_audit_result)
    TextView tvAuditResult;
    @BindView(R.id.tv_transfer)
    TextView tvTransfer;
    @BindView(R.id.tv_transfer_time)
    TextView tvTransferTime;
    @BindView(R.id.tv_transfer_money)
    TextView tvTransferMoney;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_details);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("详情");
        Financial.ListBean listBean= (Financial.ListBean) getIntent().getSerializableExtra("listBean");
        if(listBean==null){
            return;
        }
        tvCreatePeople.setText(Html.fromHtml("录入人：<font color=\"#000000\">" + listBean.getCreateName() + "</font>"));
        tvCreateTime.setText(Html.fromHtml("录入时间：<font color=\"#000000\">" + listBean.getCreateDate() + "</font>"));
        tvApplyPeple.setText(Html.fromHtml("申请人：<font color=\"#000000\">" + listBean.getName() + "</font>"));
        tvAccount.setText(Html.fromHtml("收款人账号：<font color=\"#000000\">" + listBean.getAccount() + "</font>"));
        tvBank.setText(Html.fromHtml("开户行：<font color=\"#000000\">" + listBean.getOpenBankStr() + "</font>"));
        tvMobile.setText(Html.fromHtml("tv_mobile：<font color=\"#000000\">" + listBean.getCreateName() + "</font>"));

        tvCreatePeople.setText(Html.fromHtml("录入人：<font color=\"#000000\">" + listBean.getCreateName() + "</font>"));
        tvMoney.setText(Html.fromHtml("金额：<font color=\"#000000\">" + listBean.getAmount() + "</font>"));

    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }
}
