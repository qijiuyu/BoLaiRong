package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.production.ProductProgressByOutBoundAdapter;
import com.bian.dan.blr.adapter.production.ProductProgressEntryAdapter;
import com.bian.dan.blr.adapter.production.ProductProgressWasteAdapter;
import com.bian.dan.blr.application.MyApplication;
import com.bian.dan.blr.persenter.warehouse.OutAndEntryPersenter;
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
 * 生产出入库详情
 */
public class OutAndEntry_DetailsActivity extends BaseActivity {

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
    @BindView(R.id.list_entry)
    MeasureListView listEntry;
    @BindView(R.id.tv_entry_num)
    TextView tvEntryNum;
    @BindView(R.id.list_waste)
    MeasureListView listWaste;
    @BindView(R.id.tv_waste_num)
    TextView tvWasteNum;
    @BindView(R.id.lin_waste)
    LinearLayout linWaste;
    @BindView(R.id.lin_entry)
    LinearLayout linEntry;
    @BindView(R.id.tv_entry_people)
    TextView tvEntryPeople;
    @BindView(R.id.tv_entry_time)
    TextView tvEntryTime;
    @BindView(R.id.lin_warehouse)
    LinearLayout linWarehouse;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_send_material)
    ClickTextView tvSendMaterial;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.lin_play)
    LinearLayout linPlay;
    //出库单id
    private int requireId;
    //详情对象
    private ProductProgress.ProductBean productBean;
    private OutAndEntryPersenter outAndEntryPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_and_entry_details);
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
        outAndEntryPersenter = new OutAndEntryPersenter(this);
        requireId = getIntent().getIntExtra("requireId", 0);
    }

    @OnClick({R.id.lin_back, R.id.tv_send_material,R.id.tv_ok,R.id.tv_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //发放物料
            case R.id.tv_play:
                outAndEntryPersenter.updateProductStatus(new UpdateProductP(requireId, productBean.getPlanId(), productBean.getDeptId(), "1", null));
                break;
            //确认入库
            case R.id.tv_ok:
                outAndEntryPersenter.showConfirmEntry();
                 break;
            //拒绝入库
            case R.id.tv_no:
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
                try {
                    ProductProgress productProgress = (ProductProgress) object;
                    if (productProgress.isSussess()) {
                        productBean = productProgress.getData();
                        if (productBean == null) {
                            return;
                        }
                        /**
                         * 基本信息
                         */
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
                        if (productBean.getOutStatus() == 2) {
                            linReceive.setVisibility(View.VISIBLE);
                            tvReceivePeople.setText(Html.fromHtml("领取人：<font color=\"#000000\">" + productBean.getCreateName() + "</font>"));
                            tvReceiveTime.setText(Html.fromHtml("领取时间：<font color=\"#000000\">" + productBean.getProp5() + "</font>"));
                        }


                        /**
                         * 入库产品信息
                         */
                        if (productBean.getEntryDetailList().size() > 0) {
                            linEntry.setVisibility(View.VISIBLE);
                            listEntry.setAdapter(new ProductProgressEntryAdapter(activity, productBean.getEntryDetailList(),productBean.getEntryStatus()));
                            int entryNum = 0;
                            for (int i = 0; i < productBean.getEntryDetailList().size(); i++) {
                                entryNum += productBean.getEntryDetailList().get(i).getNum();
                            }
                            tvEntryNum.setText("数量：" + entryNum);


                            /**
                             * 余废料信息
                             */
                            if (productBean.getOddsDetailList() != null && productBean.getOddsDetailList().size() > 0) {
                                linWaste.setVisibility(View.VISIBLE);
                                listWaste.setAdapter(new ProductProgressWasteAdapter(activity, productBean.getOddsDetailList(),productBean.getEntryStatus()));
                                int wasteNum = 0;
                                for (int i = 0; i < productBean.getOddsDetailList().size(); i++) {
                                    wasteNum += productBean.getOddsDetailList().get(i).getNum();
                                }
                                tvWasteNum.setText("数量：" + wasteNum);
                            }


                            /**
                             * 仓库入库信息
                             */
                            if (productBean.getEntryStatus() == 2) {
                                linWarehouse.setVisibility(View.VISIBLE);
                                tvEntryPeople.setText(Html.fromHtml("入库人：<font color=\"#000000\">" + productBean.getEntryName() + "</font>"));
                                tvEntryTime.setText(Html.fromHtml("入库时间：<font color=\"#000000\">" + productBean.getProp2() + "</font>"));
                            }

                        }


                        scrollView.scrollTo(0, 0);
                        /**
                         * 底部按钮
                         */
                        if (productBean.getOutStatus() == 0) {  //未发放物料，所以显示发放物料的按钮
                            tvSendMaterial.setVisibility(View.VISIBLE);
                            linPlay.setVisibility(View.GONE);
                            return;
                        }
                        if(productBean.getOutStatus()>0 && productBean.getEntryStatus()==0){    //已发放物料了，并且生产那边还没有申请入库
                            tvSendMaterial.setVisibility(View.GONE);
                            linPlay.setVisibility(View.GONE);
                            return;
                        }
                        if (productBean.getEntryStatus() == 1) {   //已申请入库
                            tvSendMaterial.setVisibility(View.GONE);
                            linPlay.setVisibility(View.VISIBLE);
                            return;
                        }
                        if (productBean.getEntryStatus() == 2) {  //已入库，按钮就可以隐藏了
                            tvSendMaterial.setVisibility(View.GONE);
                            linPlay.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
                            layoutParams.bottomMargin = 5;//将默认的距离底部20dp，改为0，这样底部区域全被listview填满。
                            scrollView.setLayoutParams(layoutParams);
                        }
                    } else {
                        ToastUtil.showLong(productProgress.getMsg());
                    }
                } catch (Exception e) {
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
