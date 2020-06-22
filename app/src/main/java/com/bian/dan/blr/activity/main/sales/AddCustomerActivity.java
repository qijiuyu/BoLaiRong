package com.bian.dan.blr.activity.main.sales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.persenter.sales.AddCustomerPersenter;
import com.google.gson.Gson;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.bean.parameter.AddCustomerP;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.Util;
import com.zxdc.utils.library.view.ClickTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增客户
 */
public class AddCustomerActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_industry)
    TextView tvIndustry;
    @BindView(R.id.tv_customer_status)
    TextView tvCustomerStatus;
    @BindView(R.id.et_people)
    EditText etPeople;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_position)
    EditText etPosition;
    @BindView(R.id.et_wx)
    EditText etWx;
    @BindView(R.id.et_qq)
    EditText etQq;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_url)
    EditText etUrl;
    @BindView(R.id.et_type)
    EditText etType;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_bank)
    EditText etBank;
    @BindView(R.id.et_account_name)
    EditText etAccountName;
    @BindView(R.id.et_ein)
    EditText etEin;
    @BindView(R.id.et_landline)
    EditText etLandline;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_submit)
    ClickTextView tvSubmit;
    @BindView(R.id.tv_private)
    TextView tvPrivate;
    @BindView(R.id.et_pri_account)
    EditText etPriAccount;
    @BindView(R.id.et_pri_bank)
    EditText etPriBank;
    @BindView(R.id.et_pri_account_name)
    EditText etPriAccountName;
    //客户对象
    private Customer customer;
    private AddCustomerPersenter addCustomerPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        ButterKnife.bind(this);
        initView();
        //展示客户信息--编辑使用
        showCustomer();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增客户");
        addCustomerPersenter = new AddCustomerPersenter(this);
        customer = (Customer) getIntent().getSerializableExtra("customer");
    }

    @OnClick({R.id.lin_back, R.id.tv_industry, R.id.tv_customer_status, R.id.tv_private, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //所属行业
            case R.id.tv_industry:
                addCustomerPersenter.selectText(tvIndustry, 11);
                break;
            //客户状态
            case R.id.tv_customer_status:
                addCustomerPersenter.selectText(tvCustomerStatus, 3);
                break;
            //私有状态
            case R.id.tv_private:
                addCustomerPersenter.selectText(tvPrivate, 5);
                break;
            //提交
            case R.id.tv_submit:
                String name = etName.getText().toString().trim();
                String industry = tvIndustry.getText().toString().trim();
                String customerStatus = tvCustomerStatus.getText().toString().trim();
                String people = etPeople.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String position = etPosition.getText().toString().trim();
                String wx = etWx.getText().toString().trim();
                String qq = etQq.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String url = etUrl.getText().toString().trim();
                String type = etType.getText().toString().trim();
                String account = etAccount.getText().toString().trim();
                String bank = etBank.getText().toString().trim();
                String accountName = etAccountName.getText().toString().trim();
                String priAccount=etPriAccount.getText().toString().trim();
                String priBank=etPriBank.getText().toString().trim();
                String priAccountName=etPriAccountName.getText().toString().trim();
                String ein = etEin.getText().toString().trim();
                String landLine = etLandline.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String privateStr = tvPrivate.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.showLong("请输入客户名称");
                    return;
                }
                if (TextUtils.isEmpty(people)) {
                    ToastUtil.showLong("请输入联系人");
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    ToastUtil.showLong("请输入手机号");
                    return;
                }
                if (mobile.length() != 11) {
                    ToastUtil.showLong("请输入正确的手机号");
                    return;
                }
                if (!TextUtils.isEmpty(email) && !Util.isEmail(email)) {
                    ToastUtil.showLong("请输入正确的邮箱");
                    return;
                }
                if (TextUtils.isEmpty(privateStr)) {
                    ToastUtil.showLong("请选择私有状态");
                    return;
                }
                AddCustomerP addCustomerP = new AddCustomerP();
                addCustomerP.setCustomerName(name);
                if (!TextUtils.isEmpty(customerStatus)) {
                    addCustomerP.setStatus((int) tvCustomerStatus.getTag());
                }
                if (!TextUtils.isEmpty(industry)) {
                    addCustomerP.setIndustry((int) tvIndustry.getTag());
                }
                addCustomerP.setContacts(people);
                addCustomerP.setPosition(position);
                addCustomerP.setPhone(mobile);
                addCustomerP.setWechat(wx);
                addCustomerP.setQq(qq);
                addCustomerP.setEmail(email);
                addCustomerP.setUrl(url);
                addCustomerP.setEin(ein);
                addCustomerP.setOpenAccount(account);
                addCustomerP.setOpenBank(bank);
                addCustomerP.setOpenAccName(accountName);
                addCustomerP.setPrivateAccount(priAccount);
                addCustomerP.setPrivateBank(priBank);
                addCustomerP.setPrivateAccName(priAccountName);
                addCustomerP.setLandline(landLine);
                addCustomerP.setPostAddress(address);
                if (privateStr.equals("私有")) {
                    addCustomerP.setPrivateState(1);
                } else {
                    addCustomerP.setPrivateState(2);
                }
                addCustomerP.setMemo(type);

                if (customer == null) {
                    //增加客户
                    addCustomerPersenter.checkCustomerName(1, addCustomerP);
                } else {
                    //修改客户
                    addCustomerP.setId(customer.getId());
                    //如果修改前是审核未通过状态 需要将state改为0 即未审核
                    if(customer.getState()==2){
                        addCustomerP.setState(0);
                    }
                    if (name.equals(customer.getCustomerName())) {
                        addCustomerPersenter.updateCustomer(addCustomerP);
                    } else {
                        addCustomerPersenter.checkCustomerName(2, addCustomerP);
                    }
                }
                LogUtils.e("++++++++++++" + new Gson().toJson(addCustomerP));
                break;
            default:
                break;
        }
    }


    /**
     * 展示客户信息--编辑使用
     */
    private void showCustomer() {
        if (customer == null) {
            return;
        }
        etName.setText(customer.getCustomerName());
        tvIndustry.setText(customer.getIndustryStr());
        tvIndustry.setTag(customer.getIndustry());
        tvCustomerStatus.setText(customer.getStatusName());
        tvCustomerStatus.setTag(customer.getStatus());
        etPeople.setText(customer.getContacts());
        etMobile.setText(customer.getPhone());
        etPosition.setText(customer.getPosition());
        etWx.setText(customer.getWechat());
        etQq.setText(customer.getQq());
        etEmail.setText(customer.getEmail());
        etUrl.setText(customer.getUrl());
        etType.setText(customer.getMemo());
        etAccount.setText(customer.getOpenAccount());
        etBank.setText(customer.getOpenBank());
        etAccountName.setText(customer.getOpenAccName());
        etPriAccount.setText(customer.getPrivateAccount());
        etPriBank.setText(customer.getPrivateBank());
        etPriAccountName.setText(customer.getPrivateAccName());
        etEin.setText(customer.getEin());
        etLandline.setText(customer.getLandline());
        if (customer.getPrivateState() == 1) {
            tvPrivate.setText("私有");
        } else {
            tvPrivate.setText("公有");
        }
        etAddress.setText(customer.getPostAddress());

    }
}
