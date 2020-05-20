package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.NetGridViewImgAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.ConstractDetails;
import com.zxdc.utils.library.bean.Contract;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 合同详情
 */
public class ContractDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_entry)
    TextView tvEntry;
    @BindView(R.id.tv_entry_time)
    TextView tvEntryTime;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sign_time)
    TextView tvSignTime;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_invoice)
    TextView tvInvoice;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.gridView)
    MyGridView gridView;
    @BindView(R.id.tv_office)
    TextView tvOffice;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private Contract.ListBean listBean;
    //详情对象
    private ConstractDetails constractDetails;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_details);
        ButterKnife.bind(this);
        initView();
        //获取合同详情
        getConstractDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        tvRight.setText("编辑");
        listBean = (Contract.ListBean) getIntent().getSerializableExtra("listBean");
    }


    @OnClick({R.id.lin_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
           //编辑
            case R.id.tv_right:
                if(constractDetails==null){
                    return;
                }
                Intent intent=new Intent(this,AddContractActivity.class);
                intent.putExtra("constractDetails",constractDetails);
                startActivityForResult(intent,100);
                break;
            default:
                break;
        }
    }


    /**
     * 获取合同详情
     */
    private void getConstractDetails() {
        if (listBean == null) {
            return;
        }
        DialogUtil.showProgress(this, "数据加载中");
        HttpMethod.getConstractDetails(String.valueOf(listBean.getId()), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                constractDetails = (ConstractDetails) object;
                if (constractDetails.isSussess()) {
                    ConstractDetails.DetailsBean detailsBean = constractDetails.getContract();
                    if (detailsBean != null) {
                        tvEntry.setText(Html.fromHtml("录入人：<font color=\"#000000\">" + detailsBean.getCreateName() + "</font>"));
                        tvEntryTime.setText(Html.fromHtml("录入时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));
                        tvCode.setText(Html.fromHtml("合同编号：<font color=\"#000000\">" + detailsBean.getProp2() + "</font>"));
                        tvName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">" + detailsBean.getCustomerName() + "</font>"));
                        tvSignTime.setText(Html.fromHtml("签订时间：<font color=\"#000000\">" + detailsBean.getSignedTime() + "</font>"));
                        tvMoney.setText(Html.fromHtml("订单金额：<font color=\"#000000\">" + detailsBean.getAmount() + "</font>"));
                        if (detailsBean.getPayType() == 1) {
                            tvPayType.setText(Html.fromHtml("支付方式：<font color=\"#000000\">全款</font>"));
                        } else {
                            tvPayType.setText(Html.fromHtml("支付方式：<font color=\"#000000\">分期</font>"));
                        }
                        if (detailsBean.getInvoice() == 0) {
                            tvInvoice.setText(Html.fromHtml("是否开票：<font color=\"#000000\">否</font>"));
                        } else {
                            tvInvoice.setText(Html.fromHtml("是否开票：<font color=\"#000000\">是</font>"));
                        }
                        if (detailsBean.getSellers() == 1) {
                            tvCompany.setText(Html.fromHtml("售卖公司：<font color=\"#000000\">博徕荣</font>"));
                        } else {
                            tvCompany.setText(Html.fromHtml("售卖公司：<font color=\"#000000\">立钻</font>"));
                        }
                        tvOffice.setText(Html.fromHtml("指派内勤：<font color=\"#000000\">" + detailsBean.getAssignerName() + "</font>"));
                        gridView.setAdapter(new NetGridViewImgAdapter(activity,detailsBean.getFileList()));
                    }

                } else {
                    ToastUtil.showLong(constractDetails.getMsg());
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
            //获取合同详情
            getConstractDetails();
        }
    }

}
