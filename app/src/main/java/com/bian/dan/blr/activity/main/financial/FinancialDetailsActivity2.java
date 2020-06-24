package com.bian.dan.blr.activity.main.financial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.NetGridViewImgAdapter;
import com.bumptech.glide.Glide;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Financial;
import com.zxdc.utils.library.bean.FinancialDetails;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 财务报销详情
 */
public class FinancialDetailsActivity2 extends BaseActivity {

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
    @BindView(R.id.img_transfer)
    ImageView imgTransfer;
    @BindView(R.id.lin_audit)
    LinearLayout linAudit;
    @BindView(R.id.lin_transfer)
    LinearLayout linTransfer;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private Financial.ListBean listBean;
    //详情对象
    private FinancialDetails.DetailsBean detailsBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_details2);
        ButterKnife.bind(this);
        initView();
        //获取财务详情
        getFinancialDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        listBean = (Financial.ListBean) getIntent().getSerializableExtra("listBean");
    }


    @OnClick({R.id.lin_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //确认转账
            case R.id.tv_confirm:
                Intent intent=new Intent(this,TransferActivity.class);
                intent.putExtra("detailsBean",detailsBean);
                startActivityForResult(intent,1000);
                break;
            default:
                break;
        }
    }


    /**
     * 获取财务详情
     */
    private void getFinancialDetails() {
        if (listBean == null) {
            return;
        }
        DialogUtil.showProgress(this, "数据加载中");
        HttpMethod.getFinancialDetails(listBean.getId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                FinancialDetails financialDetails = (FinancialDetails) object;
                if (financialDetails.isSussess()) {
                    detailsBean = financialDetails.getData();
                    if(detailsBean==null){
                        return;
                    }
                    tvCreatePeople.setText(Html.fromHtml("录入人：<font color=\"#000000\">" + detailsBean.getCreateName() + "</font>"));
                    tvCreateTime.setText(Html.fromHtml("录入时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));
                    tvApplyPeple.setText(Html.fromHtml("申请人：<font color=\"#000000\">" + detailsBean.getName() + "</font>"));
                    tvAccount.setText(Html.fromHtml("收款人账号：<font color=\"#000000\">" + detailsBean.getAccount() + "</font>"));
                    tvBank.setText(Html.fromHtml("开户行：<font color=\"#000000\">" + detailsBean.getOpenBank() + "</font>"));
                    tvMobile.setText(Html.fromHtml("手机号：<font color=\"#000000\">" + detailsBean.getMobile() + "</font>"));
                    tvMoney.setText(Html.fromHtml("金额(元)：<font color=\"#000000\">" + detailsBean.getAmount() + "</font>"));
                    tvRemark.setText(Html.fromHtml("款项用途及金额：<font color=\"#000000\">" + detailsBean.getMemo() + "</font>"));
                    //附件
                    gridView.setAdapter(new NetGridViewImgAdapter(activity, detailsBean.getFileList()));

                    /**
                     * 审核信息
                     */
                    if (detailsBean.getState() > 0) {
                        linAudit.setVisibility(View.VISIBLE);
                        tvAuditPeople.setText(Html.fromHtml("审批：<font color=\"#000000\">" + detailsBean.getApprovalName() + "</font>"));
                        tvAuditTime.setText(Html.fromHtml("审批时间：<font color=\"#000000\">" + detailsBean.getApprovalDate() + "</font>"));
                        switch (listBean.getState()){
                            case 0:
                                tvAuditResult.setText(Html.fromHtml("审批结果：<font color=\"#FE8E2C\">" + detailsBean.getStateStr() + "</font>"));
                                break;
                            case 1:
                                tvAuditResult.setText(Html.fromHtml("审批结果：<font color=\"#70DF5D\">" + detailsBean.getStateStr() + "</font>"));
                                break;
                            case 2:
                                tvAuditResult.setText(Html.fromHtml("审批结果：<font color=\"#FF4B4C\">" + detailsBean.getStateStr() + "</font>"));
                                break;
                            default:
                                break;
                        }
                    }

                    /**
                     * 转账信息
                     */
                    if (!TextUtils.isEmpty(detailsBean.getFinanceName())) {
                        linTransfer.setVisibility(View.VISIBLE);
                        tvTransfer.setText(Html.fromHtml("转账：<font color=\"#000000\">" + detailsBean.getFinanceName() + "</font>"));
                        tvTransferTime.setText(Html.fromHtml("填写时间：<font color=\"#000000\">" + detailsBean.getProp5() + "</font>"));
                        tvTransferMoney.setText(Html.fromHtml("转账金额(元)：<font color=\"#000000\">" + detailsBean.getProp2() + "</font>"));
                        Glide.with(activity).load(detailsBean.getProp3()).into(imgTransfer);
                    }

                    /**
                     * 底部按钮
                     */
                    //没审核，或者审核不通过，都不显示转账按钮
                    if (detailsBean.getState() == 0 || detailsBean.getState() == 2) {
                        tvConfirm.setVisibility(View.GONE);
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
                        layoutParams.bottomMargin=5;//将默认的距离底部20dp，改为0，这样底部区域全被listview填满。
                        scrollView.setLayoutParams(layoutParams);
                    }
                    //已经转账了，也隐藏底部按钮
                    if (!TextUtils.isEmpty(detailsBean.getFinanceName())) {
                        tvConfirm.setVisibility(View.GONE);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            //获取财务详情
            getFinancialDetails();
        }
    }
}
