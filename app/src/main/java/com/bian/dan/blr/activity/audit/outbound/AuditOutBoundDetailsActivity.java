package com.bian.dan.blr.activity.audit.outbound;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.OutBoundGoodAdapter;
import com.bian.dan.blr.persenter.audit.AuditPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.OutBound;
import com.zxdc.utils.library.bean.OutBoundDetails;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BigDecimalUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 审核-出库单详情
 */
public class AuditOutBoundDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_making_people)
    TextView tvMakingPeople;
    @BindView(R.id.tv_making_time)
    TextView tvMakingTime;
    @BindView(R.id.tv_manager)
    TextView tvManager;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_contract_code)
    TextView tvContractCode;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_receivable_time)
    TextView tvReceivableTime;
    @BindView(R.id.tv_unpaid_money)
    TextView tvUnpaidMoney;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.tv_product_money)
    TextView tvProductMoney;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
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
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private AuditPersenter auditPersenter;
    private OutBound.ListBean listBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_outbound_details);
        ButterKnife.bind(this);
        initView();
        //查询出库单详情
        getOutBoundDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("出库单详情");
        auditPersenter = new AuditPersenter(this);
        listBean= (OutBound.ListBean) getIntent().getSerializableExtra("listBean");
    }

    @OnClick({R.id.lin_back, R.id.tv_ok, R.id.tv_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //同意
            case R.id.tv_ok:
                break;
            //驳回
            case R.id.tv_no:
                auditPersenter.showAuditDialog();
                break;
            default:
                break;
        }
    }


    /**
     * 查询出库单详情
     */
    private void getOutBoundDetails(){
        if(listBean==null){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getOutBoundDetails(listBean.getId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                try{
                    OutBoundDetails outBoundDetails= (OutBoundDetails) object;
                    if(outBoundDetails.isSussess()){
                        OutBoundDetails.DetailsBean detailsBean=outBoundDetails.getOutOrder();
                        if(detailsBean==null){
                            return;
                        }
                        tvMakingPeople.setText(Html.fromHtml("制单人：<font color=\"#000000\">" + detailsBean.getCreateName() + "</font>"));
                        tvMakingTime.setText(Html.fromHtml("制单时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));
                        tvManager.setText(Html.fromHtml("销售经理：<font color=\"#000000\">" + detailsBean.getSalesName() + "</font>"));
                        tvCompany.setText(Html.fromHtml("客户公司：<font color=\"#000000\">" + detailsBean.getCustomerName() + "</font>"));
                        tvPeople.setText(Html.fromHtml("联系人：<font color=\"#000000\">" + detailsBean.getContacts() + "</font>"));
                        tvMobile.setText(Html.fromHtml("手机号：<font color=\"#000000\">" + detailsBean.getPhone() + "</font>"));
                        tvAddress.setText(Html.fromHtml("地址：<font color=\"#000000\">" + detailsBean.getPostAddress() + "</font>"));
                        tvContractCode.setText(Html.fromHtml("合同编号：<font color=\"#000000\">" + detailsBean.getProp2() + "</font>"));
                        if(detailsBean.getPayType()==1){
                            tvPayType.setText(Html.fromHtml("付款方式：<font color=\"#000000\">全款</font>"));
                        }else{
                            tvPayType.setText(Html.fromHtml("付款方式：<font color=\"#000000\">分期</font>"));
                        }
                        tvReceivableTime.setText(Html.fromHtml("回款日期：<font color=\"#000000\">" + detailsBean.getReceivableDate() + "</font>"));
                        tvUnpaidMoney.setText(Html.fromHtml("未付金额：<font color=\"#000000\">" + detailsBean.getUnpaidAmount() + "</font>"));
                        tvTotalMoney.setText(Html.fromHtml("累计金额：<font color=\"#000000\">" + detailsBean.getAddAmount() + "</font>"));

                        /**
                         * 产品列表
                         */
                        OutBoundGoodAdapter outBoundGoodAdapter=new OutBoundGoodAdapter(activity,outBoundDetails.getGoodsList());
                        listView.setAdapter(outBoundGoodAdapter);
                        /**
                         * 计算总数量，总金额
                         */
                        int totalNum=0;
                        double totalMoney=0;
                        for (int i=0;i<outBoundDetails.getGoodsList().size();i++){
                            final OutBoundDetails.GoodList goodList=outBoundDetails.getGoodsList().get(i);
                            totalNum=totalNum+goodList.getNum();
                            totalMoney= BigDecimalUtil.add(totalMoney,Double.parseDouble(goodList.getProp2()));
                        }
                        tvProductNum.setText("数量："+totalNum);
                        tvProductMoney.setText(Html.fromHtml("金额：<font color=\"#FF4B4C\">" + totalMoney + "</font>"));

                        tvAuditName.setText(Html.fromHtml("审核：<font color=\"#000000\">" + detailsBean.getApproveName() + "</font>"));
                        tvAuditTime.setText(Html.fromHtml("审核时间：<font color=\"#000000\">" + detailsBean.getProp5() + "</font>"));
                        String auditResult=null;
                        if(detailsBean.getState()==0){
                            auditResult="未审核";
                        }else if(detailsBean.getState()==1){
                            auditResult="通过";
                        }else{
                            auditResult="未通过";
                        }
                        tvAuditResult.setText(Html.fromHtml("审核结果：<font color=\"#FF4B4C\">" + auditResult+ "</font>"));
                        scrollView.scrollTo(0,0);
                    }else{
                        ToastUtil.showLong(outBoundDetails.getMsg());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}