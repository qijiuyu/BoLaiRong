package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.production.OutBoundProductAdapter;
import com.bian.dan.blr.adapter.sales.PlanDetailsGoodAdapter;
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
import butterknife.OnClick;

/**
 * 生产计划详情
 */
public class ProductPlanDetailsActivity extends BaseActivity {

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
    @BindView(R.id.tv_outbound)
    TextView tvOutBound;
    @BindView(R.id.list_outbound)
    MeasureListView listOutBound;
    private ProductPlan.ListBean listBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_plan_details2);
        ButterKnife.bind(this);
        initView();
        //获取生产计划详情
        getPlanDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        listBean = (ProductPlan.ListBean) getIntent().getSerializableExtra("listBean");
    }


    @OnClick({R.id.lin_back, R.id.tv_outbound})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //出库申请
            case R.id.tv_outbound:
                Intent intent=new Intent(this,AddOutBoundByProductActivity.class);
                intent.putExtra("listBean",listBean);
                startActivityForResult(intent,1000);
                break;
            default:
                break;
        }
    }


    /**
     * 获取生产计划详情
     */
    private void getPlanDetails() {
        if (listBean == null) {
            return;
        }
        DialogUtil.showProgress(this, "数据加载中");
        final UserInfo userInfo = MyApplication.getUser();
        HttpMethod.getPlanDetails(listBean.getId(), userInfo.getUser().getDeptId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                PlanDetails planDetails = (PlanDetails) object;
                if (planDetails == null) {
                    return;
                }
                PlanDetails.DetailsBean detailsBean = planDetails.getData();
                tvApplyPeple.setText(Html.fromHtml("申请人：<font color=\"#000000\">" + detailsBean.getCreateName() + "</font>"));
                tvApplyTime.setText(Html.fromHtml("申请时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));
                tvPlan.setText(Html.fromHtml("生产计划：<font color=\"#000000\">" + detailsBean.getPlanCode() + "</font>"));
                tvDeliveryTime.setText(Html.fromHtml("交付日期：<font color=\"#000000\">" + detailsBean.getDeliveryDate().split(" ")[0] + "</font>"));
                tvRemark.setText(Html.fromHtml("备注：<font color=\"#000000\">" + detailsBean.getMemo() + "</font>"));

                /**
                 * 产品列表
                 */
                PlanDetailsGoodAdapter addProductAdapter = new PlanDetailsGoodAdapter(activity, detailsBean.getDetailList());
                listView.setAdapter(addProductAdapter);
                /**
                 * 计算总数量
                 */
                int totalNum = 0;
                for (int i = 0; i < detailsBean.getDetailList().size(); i++) {
                    final PlanDetails.GoodBean goodBean = detailsBean.getDetailList().get(i);
                    totalNum = totalNum + goodBean.getNum();
                }
                tvProductNum.setText("数量：" + totalNum);

                tvAuditName.setText(Html.fromHtml("审批：<font color=\"#000000\">" + detailsBean.getApproveName() + "</font>"));
                tvAuditTime.setText(Html.fromHtml("审批时间：<font color=\"#000000\">" + detailsBean.getApproveDate() + "</font>"));
                tvAuditResult.setText(Html.fromHtml("审批结果：<font color=\"#000000\">" + detailsBean.getStatusStr() + "</font>"));

                /**
                 * 判断显示出库的产品列表
                 */
                listOutBound.setAdapter(new OutBoundProductAdapter(activity,detailsBean.getOutRequireList()));
                listOutBound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(activity, ProductProgressDetailsActivity.class);
                        intent.putExtra("planId",listBean.getId());
                        startActivity(intent);
                    }
                });
            }

            public void onFail(Throwable t) {

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            //获取生产计划详情
            getPlanDetails();
        }
    }
}
