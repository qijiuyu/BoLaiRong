package com.bian.dan.blr.activity.main.sales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.PlanDetailsGoodAdapter;
import com.bian.dan.blr.adapter.sales.PlanProgressAdapter;
import com.bian.dan.blr.application.MyApplication;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.PlanDetails;
import com.zxdc.utils.library.bean.ProductPlan;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 生产计划详情
 */
public class ProductPlanDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.list_progress)
    RecyclerView listProgress;
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
    private ProductPlan.ListBean listBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_plan_details);
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
        listBean= (ProductPlan.ListBean) getIntent().getSerializableExtra("listBean");
        //返回
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ProductPlanDetailsActivity.this.finish();
            }
        });
    }


    /**
     * 获取生产计划详情
     */
    private void getPlanDetails(){
        if(listBean==null){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        final UserInfo userInfo= MyApplication.getUser();
        HttpMethod.getPlanDetails(listBean.getId(), userInfo.getUser().getDeptId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                try {
                    PlanDetails planDetails= (PlanDetails) object;
                    PlanDetails.DetailsBean detailsBean=planDetails.getData();
                    if(detailsBean==null){
                        return;
                    }
                    LinearLayoutManager layoutManager = new LinearLayoutManager(activity);//LinearLayout默认VERTICAL排列
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//将排列方式设为水平
                    listProgress.setLayoutManager(layoutManager);
                    listProgress.setAdapter(new PlanProgressAdapter(activity,detailsBean.getProgressList()));


                    tvApplyPeple.setText(Html.fromHtml("申请人：<font color=\"#000000\">" + detailsBean.getCreateName() + "</font>"));
                    tvApplyTime.setText(Html.fromHtml("申请时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));
                    tvPlan.setText(Html.fromHtml("生产计划：<font color=\"#000000\">" + detailsBean.getPlanCode() + "</font>"));
                    tvDeliveryTime.setText(Html.fromHtml("交付日期：<font color=\"#000000\">" + detailsBean.getDeliveryDate() + "</font>"));
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
                    tvAuditName.setText(Html.fromHtml("审批：<font color=\"#000000\">" + detailsBean.getApproveName() + "</font>"));
                    tvAuditTime.setText(Html.fromHtml("审批时间：<font color=\"#000000\">" + detailsBean.getApproveDate() + "</font>"));
                    switch (detailsBean.getStatus()){
                        case 0:
                            tvAuditResult.setText(Html.fromHtml("审批结果：<font color=\"#FE8E2C\">" + detailsBean.getStatusStr() + "</font>"));
                            break;
                        case 1:
                            tvAuditResult.setText(Html.fromHtml("审批结果：<font color=\"#70DF5D\">" + detailsBean.getStatusStr() + "</font>"));
                            break;
                        case 2:
                            tvAuditResult.setText(Html.fromHtml("审批结果：<font color=\"#FF4B4C\">" + detailsBean.getStatusStr() + "</font>"));
                            break;
                        default:
                            break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            public void onFail(Throwable t) {

            }
        });
    }
}
