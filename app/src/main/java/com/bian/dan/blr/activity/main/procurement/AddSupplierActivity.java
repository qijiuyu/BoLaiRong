package com.bian.dan.blr.activity.main.procurement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSupplierActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_industry)
    TextView tvIndustry;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_material)
    TextView tvMaterial;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增供应商");
    }


    @OnClick({R.id.lin_back, R.id.tv_industry, R.id.tv_material, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_industry:
                break;
            case R.id.tv_material:
                break;
            case R.id.tv_submit:
                break;
            default:
                break;
        }
    }
}
