package com.bian.dan.blr.activity.main.production;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.production.ProductProgressByOutBoundAdapter;
import com.bian.dan.blr.application.MyApplication;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.ProductProgress;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.ClickTextView;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 生产--出库单,入库单详情
 */
public class ProductProgressDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_apply_peple)
    TextView tvApplyPeple;
    @BindView(R.id.tv_apply_time)
    TextView tvApplyTime;
    @BindView(R.id.tv_plan)
    TextView tvPlan;
    @BindView(R.id.list_outbound)
    MeasureListView listOutbound;
    @BindView(R.id.tv_play)
    ClickTextView tvPlay;
    //生产计划id
    private int planId;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outbound_details_by_product);
        ButterKnife.bind(this);
        initView();
        //根据出库id查询生产出入库、余废料明细
        getProductProgress();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        planId=getIntent().getIntExtra("planId",0);
    }

    @OnClick({R.id.lin_back, R.id.tv_play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_play:
                break;
            default:
                break;
        }
    }


    /**
     * 根据出库id查询生产出入库、余废料明细
     */
    private void getProductProgress(){
        DialogUtil.showProgress(this,"数据加载中");
        UserInfo userInfo= MyApplication.getUser();
        HttpMethod.getProductProgress(planId, userInfo.getUser().getDeptId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                ProductProgress productProgress= (ProductProgress) object;
                if(productProgress.isSussess()){
                    /**
                     * 基本信息
                     */
                    ProductProgress.ProductBean productBean=productProgress.getData();
                    if(productBean==null){
                        return;
                    }
                    tvApplyPeple.setText(Html.fromHtml("申请人：<font color=\"#000000\">" + productBean.getCreateName() + "</font>"));
                    tvApplyTime.setText(Html.fromHtml("申请时间：<font color=\"#000000\">" + productBean.getCreateDate() + "</font>"));
                    tvPlan.setText(Html.fromHtml("生产计划：<font color=\"#000000\">" + productBean.getPlanCode() + "</font>"));

                    /**
                     * 出库单列表
                     */
                    listOutbound.setAdapter(new ProductProgressByOutBoundAdapter(activity,productBean.getRequireDetailList()));

                }else{
                    ToastUtil.showLong(productProgress.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
