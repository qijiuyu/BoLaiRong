package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.warehouse.AddProductAdapter4;
import com.zxdc.utils.library.base.BaseActivity;
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
    @BindView(R.id.tv_enter_code)
    TextView tvEnterCode;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdenter_details);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        listView.setAdapter(new AddProductAdapter4(this));
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }
}
