package com.bian.dan.blr.activity.audit.financial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.NetGridViewImgAdapter;
import com.bian.dan.blr.persenter.audit.AuditPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.FinancialDetails;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.AuditOutBoundP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuditFinancialDetailsActivity extends BaseActivity {

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
    @BindView(R.id.tv_audit_remark)
    TextView tvAuditRemark;
    @BindView(R.id.lin_audit)
    LinearLayout linAudit;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.linPlay)
    LinearLayout linPlay;
    //详情id
    private int detailsId;
    //详情对象
    private FinancialDetails.DetailsBean detailsBean;
    private AuditPersenter auditPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_financial_details);
        ButterKnife.bind(this);
        initView();
        //获取财务报销详情
        getFinancialDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("报销单详情");
        auditPersenter = new AuditPersenter(this);
        detailsId = getIntent().getIntExtra("detailsId", 0);
    }

    @OnClick({R.id.lin_back, R.id.tv_ok, R.id.tv_no})
    public void onViewClicked(View view) {
        AuditOutBoundP auditOutBoundP;
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //同意
            case R.id.tv_ok:
                auditOutBoundP=new AuditOutBoundP(detailsBean.getId(),String.valueOf(detailsBean.getUserId()),1,null);
                auditPersenter.showAuditDialog(auditOutBoundP,4);
                break;
            //驳回
            case R.id.tv_no:
                auditOutBoundP=new AuditOutBoundP(detailsBean.getId(),String.valueOf(detailsBean.getUserId()),2,null);
                auditPersenter.showAuditDialog(auditOutBoundP,4);
                break;
            default:
                break;
        }
    }


    /**
     * 获取财务报销详情
     */
    private void getFinancialDetails() {
        if (detailsId == 0) {
            return;
        }
        DialogUtil.showProgress(this, "数据加载中");
        HttpMethod.getFinancialDetails(detailsId, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                FinancialDetails financialDetails = (FinancialDetails) object;
                if (financialDetails.isSussess()) {
                    detailsBean = financialDetails.getData();
                    tvCreatePeople.setText(Html.fromHtml("录入人：<font color=\"#000000\">" + detailsBean.getCreateName() + "</font>"));
                    tvCreateTime.setText(Html.fromHtml("录入时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));
                    tvApplyPeple.setText(Html.fromHtml("申请人：<font color=\"#000000\">" + detailsBean.getName() + "</font>"));
                    tvAccount.setText(Html.fromHtml("收款人账号：<font color=\"#000000\">" + detailsBean.getAccount() + "</font>"));
                    tvBank.setText(Html.fromHtml("开户行：<font color=\"#000000\">" + detailsBean.getOpenBankStr() + "</font>"));
                    tvMobile.setText(Html.fromHtml("手机号：<font color=\"#000000\">" + detailsBean.getMobile() + "</font>"));
                    tvMoney.setText(Html.fromHtml("金额：<font color=\"#000000\">" + detailsBean.getAmount() + "</font>"));
                    tvRemark.setText(Html.fromHtml("款项用途及金额：<font color=\"#000000\">" + detailsBean.getMemo() + "</font>"));
                    //附件
                    gridView.setAdapter(new NetGridViewImgAdapter(activity, detailsBean.getFileList()));

                    /**
                     * 审核信息
                     */
                    if(detailsBean.getState()>0){
                        linAudit.setVisibility(View.VISIBLE);
                        tvAuditPeople.setText(Html.fromHtml("审批：<font color=\"#000000\">" + detailsBean.getApprovalName() + "</font>"));
                        tvAuditTime.setText(Html.fromHtml("审批时间：<font color=\"#000000\">" + detailsBean.getApprovalDate() + "</font>"));
                        tvAuditResult.setText(Html.fromHtml("审批结果：<font color=\"#70DF5D\">" + detailsBean.getStateStr() + "</font>"));
                        tvAuditRemark.setText(Html.fromHtml("审核意见：<font color=\"#000000\">" + detailsBean.getProp4() + "</font>"));
                    }

                    /**
                     * 底部按钮
                     */
                    if(detailsBean.getState()>0){
                        linPlay.setVisibility(View.GONE);
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
                        layoutParams.bottomMargin=5;//将默认的距离底部20dp，改为0，这样底部区域全被listview填满。
                        scrollView.setLayoutParams(layoutParams);
                    }
                    scrollView.scrollTo(0,0);
                } else {
                    ToastUtil.showLong(financialDetails.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
