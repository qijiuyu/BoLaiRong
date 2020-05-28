package com.bian.dan.blr.activity.audit.production;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.PlanDetailsGoodAdapter;
import com.bian.dan.blr.application.MyApplication;
import com.bian.dan.blr.persenter.audit.AuditPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.PlanDetails;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.parameter.AuditOutBoundP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 审核-生产计划详情
 */
public class AuditProductPlanDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_apply_peple)
    TextView tvApplyPeple;
    @BindView(R.id.tv_apply_time)
    TextView tvApplyTime;
    @BindView(R.id.tv_plan)
    TextView tvPlan;
    @BindView(R.id.tv_delivery_time)
    TextView tvDeliveryTime;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.listView)
    MeasureListView listView;
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
    @BindView(R.id.lin_play)
    LinearLayout linPlay;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    //详情id
    private int detailsId;
    //详情对象
    private  PlanDetails.DetailsBean detailsBean;
    private AuditPersenter auditPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_product_plan_details);
        ButterKnife.bind(this);
        initView();
        //获取生产计划详情
        getPlanDetails();
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
                auditOutBoundP=new AuditOutBoundP(detailsBean.getId(),detailsBean.getCreateId(),"1",null);
                auditPersenter.showAuditDialog(auditOutBoundP,2);
                break;
            //驳回
            case R.id.tv_no:
                auditOutBoundP=new AuditOutBoundP(detailsBean.getId(),detailsBean.getCreateId(),"2",null);
                auditPersenter.showAuditDialog(auditOutBoundP,2);
                break;
            default:
                break;
        }
    }


    /**
     * 获取生产计划详情
     */
    private void getPlanDetails(){
        if(detailsId==0){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        final UserInfo userInfo= MyApplication.getUser();
        HttpMethod.getPlanDetails(detailsId, userInfo.getUser().getDeptId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                PlanDetails planDetails= (PlanDetails) object;
                if(planDetails==null){
                    return;
                }
                detailsBean=planDetails.getData();
                tvApplyPeple.setText(Html.fromHtml("申请人：<font color=\"#000000\">" + detailsBean.getCreateName() + "</font>"));
                tvApplyTime.setText(Html.fromHtml("申请时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));
                tvPlan.setText(Html.fromHtml("生产计划：<font color=\"#000000\">" + detailsBean.getPlanCode() + "</font>"));
                tvDeliveryTime.setText(Html.fromHtml("交付日期：<font color=\"#000000\">" + detailsBean.getDeliveryDate().split(" ")[0] + "</font>"));
                tvRemark.setText(Html.fromHtml("备注：<font color=\"#000000\">" + detailsBean.getMemo() + "</font>"));

                /**
                 * 产品列表
                 */
                PlanDetailsGoodAdapter addProductAdapter=new PlanDetailsGoodAdapter(activity,detailsBean.getDetailList());
                listView.setAdapter(addProductAdapter);
                /**
                 * 计算总数量，总金额
                 */
                int totalNum=0;
                for (int i=0;i<detailsBean.getDetailList().size();i++){
                     final PlanDetails.GoodBean goodBean=detailsBean.getDetailList().get(i);
                     totalNum=totalNum+goodBean.getNum();
                }
                tvProductNum.setText("数量："+totalNum);


                /**
                 * 审核信息
                 */
                if(detailsBean.getStatus()>0){
                    linAudit.setVisibility(View.VISIBLE);
                    tvAuditName.setText(Html.fromHtml("审批：<font color=\"#000000\">" + detailsBean.getApproveName() + "</font>"));
                    tvAuditTime.setText(Html.fromHtml("审批时间：<font color=\"#000000\">" + detailsBean.getApproveDate() + "</font>"));
                    tvAuditResult.setText(Html.fromHtml("审批结果：<font color=\"#000000\">" + detailsBean.getStatusStr() + "</font>"));
                    tvAuditRemark.setText(Html.fromHtml("审批意见：<font color=\"#000000\">" + detailsBean.getProp1() + "</font>"));
                }

                /**
                 * 底部按钮
                 */
                if(detailsBean.getStatus()>0){
                    linPlay.setVisibility(View.GONE);
                }
                scrollView.scrollTo(0,0);
            }
            public void onFail(Throwable t) {

            }
        });
    }
}
