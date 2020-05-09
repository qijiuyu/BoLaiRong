package com.bian.dan.blr.activity.main.sales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MyGridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加财务报销
 */
public class AddFinancialActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.gridView)
    MyGridView gridView;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_financial);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增报销单");
    }

    @OnClick({R.id.lin_back, R.id.tv_name, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                 break;
            case R.id.tv_name:
                break;
            case R.id.tv_submit:
                break;
            default:
                break;
        }
    }
}
