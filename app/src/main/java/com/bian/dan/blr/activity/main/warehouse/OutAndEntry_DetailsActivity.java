package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.persenter.warehouse.OutAndEntryPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.OutAndEntry;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 生产出入库详情
 */
public class OutAndEntry_DetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_entry_peple)
    TextView tvEntryPeple;
    @BindView(R.id.tv_entry_time)
    TextView tvEntryTime;
    @BindView(R.id.tv_stockType)
    TextView tvStockType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_spec)
    TextView tvSpec;
    @BindView(R.id.tv_uinit)
    TextView tvUinit;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_batchNo)
    TextView tvBatchNo;
    @BindView(R.id.tv_memo)
    TextView tvMemo;
    private OutAndEntryPersenter outAndEntryPersenter;
    private OutAndEntry.ListBean listBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_and_entry_details);
        ButterKnife.bind(this);
        initView();
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
        listBean= (OutAndEntry.ListBean) getIntent().getSerializableExtra("listBean");
        if(listBean!=null){
            tvEntryPeple.setText(Html.fromHtml("入库人：<font color=\"#000000\">" + listBean.getCreateName() + "</font>"));
            tvEntryTime.setText(Html.fromHtml("入库时间：<font color=\"#000000\">" + listBean.getCreateDate() + "</font>"));
            tvStockType.setText(Html.fromHtml("仓库类型：<font color=\"#000000\">" + listBean.getParentStockTypeStr()+"-"+listBean.getStockTypeStr() + "</font>"));
            tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + listBean.getGoodsName() + "</font>"));
            tvSpec.setText(Html.fromHtml("规格/型号：<font color=\"#000000\">" + listBean.getSpec() + "</font>"));
            tvUinit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + listBean.getUnitStr() + "</font>"));
            tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + listBean.getNum() + "</font>"));
            tvBatchNo.setText(Html.fromHtml("批号：<font color=\"#000000\">" + listBean.getBatchNo() + "</font>"));
            tvMemo.setText(Html.fromHtml("备注：<font color=\"#000000\">" + listBean.getMemo() + "</font>"));
        }
    }


}
