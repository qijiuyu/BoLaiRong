package com.bian.dan.blr.activity.main.procurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.SelectMaterialActivity;
import com.bian.dan.blr.persenter.procurement.AddProductPersenter3;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.Util;

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
    private AddProductPersenter3 addProductPersenter3;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product3);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("添加产品");
        addProductPersenter3=new AddProductPersenter3(this);

        /**
         * 监听数量输入框
         */
        etNum.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()==0 || TextUtils.isEmpty(etPrice.getText().toString().trim())){
                    tvTotalMoney.setText(null);
                    return;
                }
                final int num=Integer.parseInt(s.toString());
                final double price=Double.parseDouble(etPrice.getText().toString().trim());
                tvTotalMoney.setText(Util.setDouble(num*price,2));
            }
        });

        /**
         * 监听单价输入框
         */
        etPrice.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()==0 || TextUtils.isEmpty(etNum.getText().toString().trim())){
                    tvTotalMoney.setText(null);
                    return;
                }
                final int num=Integer.parseInt(etNum.getText().toString().trim());
                final double price=Double.parseDouble(s.toString().trim());
                tvTotalMoney.setText(Util.setDouble(num*price,2));
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_name, R.id.tv_supplier_name, R.id.tv_pay_type, R.id.tv_pay_time, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //物料名称
            case R.id.tv_name:
                 setClass(SelectMaterialActivity.class,100);
                break;
            //供应商名称
            case R.id.tv_supplier_name:
                break;
            //付款方式
            case R.id.tv_pay_type:
                addProductPersenter3.selectPayType(tvPayType);
                break;
            //付款时间
            case R.id.tv_pay_time:
                addProductPersenter3.selectTime(tvPayTime);
                break;
            //提交
            case R.id.tv_submit:
                String name=tvName.getText().toString().trim();
                String num=etNum.getText().toString().trim();
                String price=etPrice.getText().toString().trim();
                String money=tvTotalMoney.getText().toString().trim();
                String remark=etRemark.getText().toString().trim();
                String peyType=tvPayType.getText().toString().trim();
                String payTime=tvPayTime.getText().toString().trim();
                String supplier=tvSupplierName.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请选择物料名称");
                    return;
                }
                if(TextUtils.isEmpty(num)){
                    ToastUtil.showLong("请输入数量");
                    return;
                }
                if(TextUtils.isEmpty(price)){
                    ToastUtil.showLong("请输入单价");
                    return;
                }
                if(TextUtils.isEmpty(supplier)){
                    ToastUtil.showLong("请选择供应商");
                    return;
                }
                if(TextUtils.isEmpty(peyType)){
                    ToastUtil.showLong("请选择付款方式");
                    return;
                }
                if(TextUtils.isEmpty(payTime)){
                    ToastUtil.showLong("请选择付款时间");
                    return;
                }
                if(TextUtils.isEmpty(remark)){
                    ToastUtil.showLong("请输入备注");
                    return;
                }
                break;
            default:
                break;
        }
    }


    private Material.ListBean listBean;//物料对象
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && data!=null){
            listBean= (Material.ListBean) data.getSerializableExtra("listBean");
            if(listBean==null){
                return;
            }
            tvName.setText(listBean.getName());
            tvType.setText(listBean.getTypeStr());
            tvSpec.setText(listBean.getSpec());
            tvUnit.setText(listBean.getUnitStr());
            etPrice.setText(Util.setDouble(listBean.getPrice(),2));
        }
    }
}
