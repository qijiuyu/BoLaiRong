package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.production.ProductProgressByOutBoundAdapter;
import com.bian.dan.blr.application.MyApplication;
import com.bian.dan.blr.persenter.product.ProductProgressPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.ProductProgress;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.parameter.UpdateProductP;
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
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    @BindView(R.id.tv_send_people)
    TextView tvSendPeople;
    @BindView(R.id.tv_send_time)
    TextView tvSendTime;
    @BindView(R.id.lin_send)
    LinearLayout linSend;
    @BindView(R.id.tv_receive_people)
    TextView tvReceivePeople;
    @BindView(R.id.tv_receive_time)
    TextView tvReceiveTime;
    @BindView(R.id.lin_receive)
    LinearLayout linReceive;
    //出库单id
    private int requireId;
    private ProductProgressPersenter productProgressPersenter;

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
        productProgressPersenter = new ProductProgressPersenter(this);
        requireId = getIntent().getIntExtra("requireId", 0);
    }

    @OnClick({R.id.lin_back, R.id.tv_play})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //确认领取/申请入库
            case R.id.tv_play:
                final String name = tvPlay.getText().toString().trim();
                if (name.equals("确认领取")) {
                    productProgressPersenter.updateProductStatus(new UpdateProductP(requireId, "1", null));
                } else if (name.equals("入库申请")) {
                    intent.setClass(this, PutStorageActivity.class);
                    intent.putExtra("requireId", requireId);
                    startActivityForResult(intent, 100);
                }
                break;
            default:
                break;
        }
    }


    /**
     * 根据出库id查询生产出入库、余废料明细
     */
    public void getProductProgress() {
        DialogUtil.showProgress(this, "数据加载中");
        UserInfo userInfo = MyApplication.getUser();
        HttpMethod.getProductProgress(requireId, userInfo.getUser().getDeptId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                ProductProgress productProgress = (ProductProgress) object;
                if (productProgress.isSussess()) {
                    /**
                     * 基本信息
                     */
                    ProductProgress.ProductBean productBean = productProgress.getData();
                    if (productBean == null) {
                        return;
                    }
                    tvApplyPeple.setText(Html.fromHtml("申请人：<font color=\"#000000\">" + productBean.getCreateName() + "</font>"));
                    tvApplyTime.setText(Html.fromHtml("申请时间：<font color=\"#000000\">" + productBean.getCreateDate() + "</font>"));
                    tvPlan.setText(Html.fromHtml("生产计划：<font color=\"#000000\">" + productBean.getPlanCode() + "</font>"));

                    /**
                     * 出库单列表
                     */
                    listOutbound.setAdapter(new ProductProgressByOutBoundAdapter(activity, productBean.getRequireDetailList()));
                    int totalNum = 0;
                    for (int i = 0; i < productBean.getRequireDetailList().size(); i++) {
                        totalNum += productBean.getRequireDetailList().get(i).getNum();
                    }
                    tvProductNum.setText("数量：" + totalNum);

                    /**
                     * 发放人信息
                     */
                    if (productBean.getOutStatus() > 0) {
                        linSend.setVisibility(View.VISIBLE);
                        tvSendPeople.setText(Html.fromHtml("发放人：<font color=\"#000000\">" + productBean.getUpdateName() + "</font>"));
                        tvSendTime.setText(Html.fromHtml("发放时间：<font color=\"#000000\">" + productBean.getUpdateDate() + "</font>"));
                    }


                    /**
                     * 领取人信息
                     */
                    if (productBean.getOutStatus()==2) {
                        linReceive.setVisibility(View.VISIBLE);
                        tvReceivePeople.setText(Html.fromHtml("领取人：<font color=\"#000000\">" + productBean.getCreateName() + "</font>"));
                        tvReceiveTime.setText(Html.fromHtml("领取时间：<font color=\"#000000\">" + productBean.getProp5() + "</font>"));
                    }


                    /**
                     * 按钮状态
                     * 1：仓库已发放，所以可以领取了
                     * 2：已经领取成功了，所以接下来就是入库申请了
                     */
                    if (productBean.getOutStatus() == 1) {
                        tvPlay.setVisibility(View.VISIBLE);
                        tvPlay.setText("确认领取");
                    } else if (productBean.getOutStatus() == 2) {
                        tvPlay.setVisibility(View.VISIBLE);
                        tvPlay.setText("入库申请");
                    }

                } else {
                    ToastUtil.showLong(productProgress.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            //申请入库成功后的回执
            case 100:
                //刷新数据
                getProductProgress();
                break;
            default:
                break;
        }
    }
}
