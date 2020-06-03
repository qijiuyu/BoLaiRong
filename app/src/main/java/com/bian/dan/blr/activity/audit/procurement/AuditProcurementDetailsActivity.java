package com.bian.dan.blr.activity.audit.procurement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.audit.AuditProcurementGoodsAdapter;
import com.bian.dan.blr.persenter.audit.AuditPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.ProcurementDetails;
import com.zxdc.utils.library.bean.parameter.AuditOutBoundP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BigDecimalUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 审核-采购单详情
 */
public class AuditProcurementDetailsActivity extends BaseActivity {

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
    @BindView(R.id.tv_audit_remark)
    TextView tvAuditRemark;
    @BindView(R.id.lin_play)
    LinearLayout linPlay;
    @BindView(R.id.lin_audit)
    LinearLayout linAudit;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    //详情id
    private int detailsId;
    private AuditPersenter auditPersenter;
    //详情对象
    private ProcurementDetails.DetailsBean detailsBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_procurement_details);
        ButterKnife.bind(this);
        initView();
        //获取采购单详情
        getProcurementDetails();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("详情");
        auditPersenter=new AuditPersenter(this);
        detailsId=getIntent().getIntExtra("detailsId",0);
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
                auditOutBoundP=new AuditOutBoundP(detailsBean.getId(),detailsBean.getCreateId(),1,null);
                auditPersenter.showAuditDialog(auditOutBoundP,3);
                break;
            //驳回
            case R.id.tv_no:
                auditOutBoundP=new AuditOutBoundP(detailsBean.getId(),detailsBean.getCreateId(),2,null);
                auditPersenter.showAuditDialog(auditOutBoundP,3);
                break;
            default:
                break;
        }
    }


    /**
     * 获取采购单详情
     */
    public void getProcurementDetails(){
        if(detailsId==0){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getProcurementDetails(detailsId, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                ProcurementDetails procurementDetails= (ProcurementDetails) object;
                if(procurementDetails.isSussess()){
                    detailsBean=procurementDetails.getData();
                    if(detailsBean==null){
                        return;
                    }
                    tvProName.setText(Html.fromHtml("采购员：<font color=\"#000000\">" + detailsBean.getPurcName()+ "</font>"));
                    tvProTime.setText(Html.fromHtml("采购时间：<font color=\"#000000\">" + detailsBean.getPurcDate()+ "</font>"));
                    tvProCode.setText(Html.fromHtml("采购单号：<font color=\"#000000\">" + detailsBean.getPurcOrder()+ "</font>"));
                    tvApplyTime.setText(Html.fromHtml("申请时间：<font color=\"#000000\">" + detailsBean.getCreateDate()+ "</font>"));

                    /**
                     * 产品列表
                     */
                    listView.setAdapter(new AuditProcurementGoodsAdapter(activity,detailsBean.getPurchaseDetailList()));
                    /**
                     * 计算总数量，总金额
                     */
                    int totalNum=0;
                    double totalMoney=0;
                    for (int i=0;i<detailsBean.getPurchaseDetailList().size();i++){
                        final ProcurementDetails.GoodList goodList=detailsBean.getPurchaseDetailList().get(i);
                        totalNum=totalNum+goodList.getNum();
                        totalMoney= BigDecimalUtil.add(totalMoney,goodList.getAmount());
                    }
                    tvProductNum.setText("数量："+totalNum);
                    tvProductMoney.setText(Html.fromHtml("金额：<font color=\"#FF4B4C\">" + totalMoney + "</font>"));


                    /**
                     * 审核信息
                     */
                    if(detailsBean.getState()>0){
                        linAudit.setVisibility(View.VISIBLE);
                        tvAudit.setText(Html.fromHtml("审核：<font color=\"#000000\">" + detailsBean.getApproveName()+ "</font>"));
                        tvAuditTime.setText(Html.fromHtml("审核时间：<font color=\"#000000\">" + detailsBean.getProp5()+ "</font>"));
                        tvAuditResult.setText(Html.fromHtml("审核结果：<font color=\"#000000\">" + detailsBean.getStateStr()+ "</font>"));
                        tvAuditRemark.setText(Html.fromHtml("审核意见：<font color=\"#000000\">" + detailsBean.getProp4()+ "</font>"));
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

                }else{
                    ToastUtil.showLong(procurementDetails.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
