package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.OutBoundDetailsAdapter;
import com.bian.dan.blr.adapter.sales.OutBoundGoodAdapter;
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
 * 销售出库单详情
 */
public class SalesOutBoundDetailsActivity extends BaseActivity {

    @BindView(R.id.scrollView)
    ScrollView scrollView;
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
    @BindView(R.id.tv_audit_memo)
    TextView tvAuditMemo;
    @BindView(R.id.tv_outbound_name)
    TextView tvOutboundName;
    @BindView(R.id.tv_outbound_time)
    TextView tvOutboundTime;
    @BindView(R.id.list_outbound)
    MeasureListView listOutbound;
    @BindView(R.id.tv_logistics)
    TextView tvLogistics;
    @BindView(R.id.tv_order_code)
    TextView tvOrderCode;
    @BindView(R.id.lin_out)
    LinearLayout linOut;
    @BindView(R.id.tv_send)
    TextView tvSend;
    private OutBound.ListBean listBean;
    //详情对象
    private OutBoundDetails outBoundDetails;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_outbound_details);
        ButterKnife.bind(this);
        initView();
        //查询出库单详情
        getOutBoundDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        listBean= (OutBound.ListBean) getIntent().getSerializableExtra("listBean");
    }

    @OnClick({R.id.lin_back, R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
             //出库发货
            case R.id.tv_send:
                Intent intent=new Intent(this,SendDeliveryActivity.class);
                intent.putExtra("outBoundDetails",outBoundDetails);
                startActivityForResult(intent,1000);
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
                    outBoundDetails= (OutBoundDetails) object;
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
                        tvProductMoney.setText(Html.fromHtml("金额(元)：<font color=\"#FF4B4C\">" + totalMoney + "</font>"));


                        /**
                         * 审核信息
                         */
                        tvAuditName.setText(Html.fromHtml("审核：<font color=\"#000000\">" + detailsBean.getApproveName() + "</font>"));
                        tvAuditTime.setText(Html.fromHtml("审核时间：<font color=\"#000000\">" + detailsBean.getProp5() + "</font>"));
                        tvAuditResult.setText(Html.fromHtml("审核结果：<font color=\"#000000\">" + detailsBean.getStateStr()+ "</font>"));
                        tvAuditMemo.setText(Html.fromHtml("审核意见：<font color=\"#000000\">" + detailsBean.getProp4()+ "</font>"));


                        /**
                         * 显示出库信息
                         */
                        if(outBoundDetails.getSaleDetailList()!=null && outBoundDetails.getSaleDetailList().size()>0){
                            linOut.setVisibility(View.VISIBLE);
                            OutBoundDetails.SaleDetails saleDetails=outBoundDetails.getSaleDetailList().get(0);
                            tvOutboundName.setText(Html.fromHtml("出库人：<font color=\"#000000\">" + saleDetails.getCreateName() + "</font>"));
                            tvOutboundTime.setText(Html.fromHtml("出库时间：<font color=\"#000000\">" + saleDetails.getCreateDate() + "</font>"));
                            listOutbound.setAdapter(new OutBoundDetailsAdapter(activity,outBoundDetails.getSaleDetailList()));
                        }


                        /**
                         * 物流信息
                         */
                        tvLogistics.setText(Html.fromHtml("物流名称：<font color=\"#000000\">" + detailsBean.getExpressTypeStr() + "</font>"));
                        tvOrderCode.setText(Html.fromHtml("物流单号：<font color=\"#000000\">" + detailsBean.getExpressNo() + "</font>"));


                        /**
                         * 底部按钮变更
                         */
//                        if(detailsBean.getState()==1 && detailsBean.getStatus()==0){  //已审核同意，并且还没有出库
//                            tvSend.setVisibility(View.VISIBLE);
//                        }else{
//                            tvSend.setVisibility(View.GONE);
//                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
//                            layoutParams.bottomMargin=5;//将默认的距离底部20dp，改为0，这样底部区域全被listview填满。
//                            scrollView.setLayoutParams(layoutParams);
//                        }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
             //查询出库单详情
            getOutBoundDetails();
        }
    }
}
