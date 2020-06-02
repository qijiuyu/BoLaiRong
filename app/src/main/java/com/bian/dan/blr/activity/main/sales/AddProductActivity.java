package com.bian.dan.blr.activity.main.sales;

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
import com.bian.dan.blr.persenter.sales.AddProductPersenter;
import com.bian.dan.blr.view.MyWatcher;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加产品
 */
public class AddProductActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_brand)
    TextView tvBrand;
    @BindView(R.id.tv_spec)
    TextView tvSpace;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.tv_invoice)
    TextView tvInvoice;
    private AddProductPersenter addProductPersenter;
    //物料对象
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        addProductPersenter=new AddProductPersenter(this);
        tvHead.setText("添加产品");
        //限制小数点前后
        etPrice.addTextChangedListener(new MyWatcher(7,2));

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
                    tvMoney.setText(null);
                    return;
                }
                final int num=Integer.parseInt(s.toString());
                final double price=Double.parseDouble(etPrice.getText().toString().trim());
                tvMoney.setText(Util.setDouble(num*price,2));

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
                    tvMoney.setText(null);
                    return;
                }
                final int num=Integer.parseInt(etNum.getText().toString().trim());
                final double price=Double.parseDouble(s.toString().trim());
                tvMoney.setText(Util.setDouble(num*price,2));
            }
        });
    }


    /**
     * 按钮点击事件
     * @param view
     */
    @OnClick({R.id.lin_back,R.id.tv_name,R.id.tv_invoice,R.id.tv_submit})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择物料名称
            case R.id.tv_name:
                intent.setClass(this,SelectMaterialActivity.class);
                startActivityForResult(intent,100);
                break;
            //选择是否开票
            case R.id.tv_invoice:
                addProductPersenter.selectInvoice(tvInvoice);
                break;
            case R.id.tv_submit:
                  String name=tvName.getText().toString().trim();
                  String spce=tvSpace.getText().toString().trim();
                  String unit=tvUnit.getText().toString().trim();
                  String brand=tvBrand.getText().toString().trim();
                  String num=etNum.getText().toString().trim();
                  String price=etPrice.getText().toString().trim();
                  String money=tvMoney.getText().toString().trim();
                  String remark=etRemark.getText().toString().trim();
                  String invoice=tvInvoice.getText().toString().trim();
                  if(TextUtils.isEmpty(name)){
                      ToastUtil.showLong("请选择物料名称");
                      return;
                  }
                if(TextUtils.isEmpty(num)){
                    ToastUtil.showLong("请输入数量");
                    return;
                }
                if(Integer.parseInt(num)==0){
                    ToastUtil.showLong("数量不能为0");
                    return;
                }
                if(TextUtils.isEmpty(price)){
                    ToastUtil.showLong("请输入单价");
                    return;
                }
                if(Double.parseDouble(price)==0){
                    ToastUtil.showLong("单价不能为0");
                    return;
                }
                if(TextUtils.isEmpty(invoice)){
                    ToastUtil.showLong("是否开票");
                    return;
                }
                String isInvoice="0";
                if(invoice.equals("是")){
                    isInvoice="1";
                }
                Goods goods=new Goods(listBean.getId(),name,spce,unit,brand,Integer.parseInt(num),price,money,remark,isInvoice);
                intent.putExtra("goods",goods);
                setResult(200,intent);
                finish();
                  break;
            default:
                break;
        }
    }


    private Material.ListBean listBean;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && data!=null){
            listBean= (Material.ListBean) data.getSerializableExtra("listBean");
            if(listBean==null){
                return;
            }
            tvName.setText(listBean.getName());
            tvBrand.setText(listBean.getBrand());
            tvSpace.setText(listBean.getSpec());
            tvUnit.setText(listBean.getUnitStr());
            etPrice.setText(listBean.getProp1());
        }
    }
}
