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

/**
 * 添加产品
 */
public class AddProductActivity3 extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_spec)
    TextView tvSpec;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_supplier_name)
    TextView tvSupplierName;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.et_remark)
    EditText etRemark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product3);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lin_back, R.id.tv_name, R.id.tv_type, R.id.tv_spec, R.id.tv_supplier_name, R.id.tv_pay_type, R.id.tv_pay_time, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //物料名称
            case R.id.tv_name:
                break;
            //物料类别
            case R.id.tv_type:
                break;
            //规格/型号
            case R.id.tv_spec:
                break;
            //供应商名称
            case R.id.tv_supplier_name:
                break;
            //付款方式
            case R.id.tv_pay_type:
                break;
            //付款时间
            case R.id.tv_pay_time:
                break;
            case R.id.tv_submit:
                break;
            default:
                break;
        }
    }
}
