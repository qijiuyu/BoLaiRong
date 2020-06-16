package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.warehouse.SellingGoodsAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SellingDetails;
import com.zxdc.utils.library.bean.SellingOutBound;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BigDecimalUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 售卖详情
 */
public class SellingDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    @BindView(R.id.tv_audit_name)
    TextView tvAuditName;
    @BindView(R.id.tv_audit_time)
    TextView tvAuditTime;
    @BindView(R.id.tv_audit_remark)
    TextView tvAuditRemark;
    @BindView(R.id.lin_audit)
    LinearLayout linAudit;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_product_money)
    TextView tvProductMoney;
    private SellingOutBound.ListBean listBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling_details);
        ButterKnife.bind(this);
        initView();
        //售卖明细
        getSellingDetails();
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
        listBean= (SellingOutBound.ListBean) getIntent().getSerializableExtra("listBean");
    }


    /**
     * 售卖明细
     */
    private void getSellingDetails(){
        if(listBean==null){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getSellingDetails(listBean.getId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                SellingDetails sellingDetails= (SellingDetails) object;
                if(sellingDetails.isSussess()){
                    final SellingDetails.DetailsBean detailsBean=sellingDetails.getSelling();
                    if(detailsBean==null){
                        return;
                    }
                    tvPeople.setText(Html.fromHtml("售卖人：<font color=\"#000000\">" + detailsBean.getSellName() + "</font>"));
                    tvTime.setText(Html.fromHtml("售卖时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));

                    /**
                     * 售卖列表
                     */
                    listView.setAdapter(new SellingGoodsAdapter(activity,sellingDetails.getSellingDetailList()));
                    /**
                     * 计算总数量，总金额
                     */
                    int totalNum=0;
                    double totalMoney=0;
                    for (int i=0;i<sellingDetails.getSellingDetailList().size();i++){
                        final SellingDetails.GoodsList goodList=sellingDetails.getSellingDetailList().get(i);
                        totalNum=totalNum+goodList.getNum();
                        totalMoney= BigDecimalUtil.add(totalMoney,goodList.getTotalPrice());
                    }
                    tvProductNum.setText("数量："+totalNum);
                    tvProductMoney.setText(Html.fromHtml("金额(元)：<font color=\"#FF4B4C\">" + totalMoney + "</font>"));


                    /**
                     * 审批信息
                     */
                    tvAuditName.setText(Html.fromHtml("审批人：<font color=\"#000000\">" + detailsBean.getApprovalName() + "</font>"));
                    tvAuditTime.setText(Html.fromHtml("审批时间：<font color=\"#000000\">" + detailsBean.getApprovalDate() + "</font>"));
                    tvAuditRemark.setText(Html.fromHtml("审批意见：<font color=\"#000000\">" + detailsBean.getMemo() + "</font>"));
                    scrollView.scrollTo(0,0);
                }else{
                    ToastUtil.showLong(sellingDetails.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
