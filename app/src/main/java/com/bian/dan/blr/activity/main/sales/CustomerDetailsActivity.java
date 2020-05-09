package com.bian.dan.blr.activity.main.sales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 客户详情
 */
public class CustomerDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_customer_name)
    TextView tvCustomerName;
    @BindView(R.id.tv_industry)
    TextView tvIndustry;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_url)
    TextView tvUrl;
    @BindView(R.id.tv_procurement_type)
    TextView tvProcurementType;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_ein)
    TextView tvEin;
    @BindView(R.id.tv_landline)
    TextView tvLandline;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_follow_time)
    TextView tvFollowTime;
    @BindView(R.id.tv_follow_result)
    TextView tvFollowResult;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        tvRight.setText("编辑");
    }

    @OnClick({R.id.lin_back, R.id.tv_right, R.id.tv_get})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //编辑
            case R.id.tv_right:
                setClass(AddCustomerActivity.class);
                break;
            case R.id.tv_get:
                break;
            default:
                break;
        }
    }
}
