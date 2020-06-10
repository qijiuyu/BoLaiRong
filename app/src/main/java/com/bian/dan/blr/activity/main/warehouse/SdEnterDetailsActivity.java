package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.warehouse.SdEnterGoodsAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SdEnter;
import com.zxdc.utils.library.bean.SdEnterDetails;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BigDecimalUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手动入库单详情
 */
public class SdEnterDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_pro_name)
    TextView tvProName;
    @BindView(R.id.tv_pro_time)
    TextView tvProTime;
    @BindView(R.id.tv_enter_people)
    TextView tvEnterPeople;
    @BindView(R.id.tv_apply_time)
    TextView tvApplyTime;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.tv_product_money)
    TextView tvProductMoney;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    private SdEnter.ListBean listBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdenter_details);
        ButterKnife.bind(this);
        initView();
        //获取手动入库详情
        getSdEnterDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        listBean= (SdEnter.ListBean) getIntent().getSerializableExtra("listBean");
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 获取手动入库详情
     */
    private void getSdEnterDetails(){
        if(listBean==null){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getSdEnterDetails(listBean.getId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                SdEnterDetails sdEnterDetails= (SdEnterDetails) object;
                if(sdEnterDetails.isSussess()){
                    SdEnterDetails.DetailsBean detailsBean=sdEnterDetails.getData();
                    tvProName.setText(Html.fromHtml("采购员：<font color=\"#000000\">" + detailsBean.getPurcName() + "</font>"));
                    tvProTime.setText(Html.fromHtml("采购日期：<font color=\"#000000\">" + detailsBean.getPurcDate() + "</font>"));
                    tvEnterPeople.setText(Html.fromHtml("录入人：<font color=\"#000000\">" + detailsBean.getCreateName() + "</font>"));
                    tvApplyTime.setText(Html.fromHtml("申请时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));
                    /**
                     * 产品列表
                     */
                    listView.setAdapter(new SdEnterGoodsAdapter(activity,detailsBean.getDetailList()));
                    /**
                     * 计算总数量，总金额
                     */
                    int totalNum=0;
                    double totalMoney=0;
                    for (int i=0;i<detailsBean.getDetailList().size();i++){
                        final SdEnterDetails.GoodList goodList=detailsBean.getDetailList().get(i);
                        totalNum=totalNum+goodList.getNum();
                        totalMoney= BigDecimalUtil.add(totalMoney,goodList.getAmount());
                    }
                    tvProductNum.setText("数量："+totalNum);
                    tvProductMoney.setText(Html.fromHtml("金额：<font color=\"#FF4B4C\">" + totalMoney + "元</font>"));
                }else{
                    ToastUtil.showLong(sdEnterDetails.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
