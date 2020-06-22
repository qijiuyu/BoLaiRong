package com.bian.dan.blr.activity.audit.customer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.persenter.audit.AuditPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.bean.CustomerDetails;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuditCustomerDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_customer_name)
    TextView tvCustomerName;
    @BindView(R.id.tv_industry)
    TextView tvIndustry;
    @BindView(R.id.tv_customer_status)
    TextView tvCustomerStatus;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_position)
    TextView tvPostion;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_url)
    TextView tvUrl;
    @BindView(R.id.tv_procurement_type)
    TextView tvProcurementType;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_ein)
    TextView tvEin;
    @BindView(R.id.tv_landline)
    TextView tvLandline;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.lin_play)
    LinearLayout linPlay;
    @BindView(R.id.tv_pri_account)
    TextView tvPriAccount;
    @BindView(R.id.tv_pri_bank)
    TextView tvPriBank;
    @BindView(R.id.tv_pri_account_name)
    TextView tvPriAccountName;
    @BindView(R.id.tv_audit_name)
    TextView tvAuditName;
    @BindView(R.id.tv_audit_time)
    TextView tvAuditTime;
    @BindView(R.id.tv_audit_result)
    TextView tvAuditResult;
    @BindView(R.id.tv_audit_remark)
    TextView tvAuditRemark;
    @BindView(R.id.lin_audit)
    LinearLayout linAudit;
    //详情id
    private int detailsId;
    //详情对象
    private Customer customer;
    private AuditPersenter auditPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_customer_details);
        ButterKnife.bind(this);
        initView();
        //获取客户详情
        getCustomerDetails();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("客户新增详情");
        auditPersenter = new AuditPersenter(this);
        detailsId = getIntent().getIntExtra("detailsId",0);
    }


    @OnClick({R.id.lin_back, R.id.tv_ok, R.id.tv_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //审核同意
            case R.id.tv_ok:
                auditPersenter.showOkAudit(customer);
                break;
            //审核驳回
            case R.id.tv_no:
                auditPersenter.showNoAudit(customer);
                break;
            default:
                break;
        }
    }


    /**
     * 获取客户详情
     */
    public void getCustomerDetails() {
        DialogUtil.showProgress(this, "数据加载中");
        HttpMethod.getCustomerDetails(detailsId, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                try {
                    CustomerDetails customerDetails = (CustomerDetails) object;
                    if (customerDetails.isSussess()) {
                        customer = customerDetails.getCustomer();
                        if (customer == null) {
                            return;
                        }
                        tvCustomerName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">" + customer.getCustomerName() + "</font>"));
                        tvIndustry.setText(Html.fromHtml("所属行业：<font color=\"#000000\">" + customer.getIndustryStr() + "</font>"));
                        tvCustomerStatus.setText(Html.fromHtml("客户状态：<font color=\"#000000\">" + customer.getStatusName() + "</font>"));
                        tvPeople.setText(Html.fromHtml("联系人：<font color=\"#000000\">" + customer.getContacts() + "</font>"));
                        tvMobile.setText(Html.fromHtml("手机号：<font color=\"#000000\">" + customer.getPhone() + "</font>"));
                        tvPostion.setText(Html.fromHtml("职位：<font color=\"#000000\">" + customer.getPosition() + "</font>"));
                        tvWx.setText(Html.fromHtml("微信号：<font color=\"#000000\">" + customer.getWechat() + "</font>"));
                        tvQq.setText(Html.fromHtml("QQ号：<font color=\"#000000\">" + customer.getQq() + "</font>"));
                        tvEmail.setText(Html.fromHtml("邮箱：<font color=\"#000000\">" + customer.getEmail() + "</font>"));
                        tvUrl.setText(Html.fromHtml("网址：<font color=\"#000000\">" + customer.getUrl() + "</font>"));
                        tvProcurementType.setText(Html.fromHtml("采购种类：<font color=\"#000000\">" + customer.getMemo() + "</font>"));
                        tvAccount.setText(Html.fromHtml("对公账户：<font color=\"#000000\">" + customer.getOpenAccount() + "</font>"));
                        tvBank.setText(Html.fromHtml("对公开户行：<font color=\"#000000\">" + customer.getOpenBank() + "</font>"));
                        tvCustomerName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">" + customer.getCustomerName() + "</font>"));
                        tvAccountName.setText(Html.fromHtml("对公户名：<font color=\"#000000\">" + customer.getOpenAccName() + "</font>"));
                        tvPriAccount.setText(Html.fromHtml("私有账户：<font color=\"#000000\">" + customer.getPrivateAccount() + "</font>"));
                        tvPriBank.setText(Html.fromHtml("私有开户行：<font color=\"#000000\">" + customer.getPrivateBank() + "</font>"));
                        tvPriAccountName.setText(Html.fromHtml("私有户名：<font color=\"#000000\">" + customer.getPrivateAccName() + "</font>"));
                        tvEin.setText(Html.fromHtml("税号：<font color=\"#000000\">" + customer.getEin() + "</font>"));
                        tvLandline.setText(Html.fromHtml("座机号：<font color=\"#000000\">" + customer.getLandline() + "</font>"));
                        tvAddress.setText(Html.fromHtml("收件地址：<font color=\"#000000\">" + customer.getPostAddress() + "</font>"));


                        /**
                         * 审核信息
                         */
                        if (customer.getState() > 0) {
                            linAudit.setVisibility(View.VISIBLE);
                            tvAuditName.setText(Html.fromHtml("审批人：<font color=\"#000000\">" + customer.getApprovalName() + "</font>"));
                            tvAuditTime.setText(Html.fromHtml("审批时间：<font color=\"#000000\">" + customer.getApprovalDate() + "</font>"));
                            tvAuditResult.setText(Html.fromHtml("审批结果：<font color=\"#000000\">" + customer.getStateStr() + "</font>"));
                            tvAuditRemark.setText(Html.fromHtml("审核意见：<font color=\"#000000\"></font>"));
                        }


                        /**
                         * 底部按钮
                         */
                        if (customer.getState() > 0) {
                            linPlay.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
                            layoutParams.bottomMargin = 5;//将默认的距离底部20dp，改为0，这样底部区域全被listview填满。
                            scrollView.setLayoutParams(layoutParams);
                        }
                        scrollView.scrollTo(0, 0);
                    } else {
                        ToastUtil.showLong(customerDetails.getMsg());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
