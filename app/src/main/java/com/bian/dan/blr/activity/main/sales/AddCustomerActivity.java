package com.bian.dan.blr.activity.main.sales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.persenter.sales.AddCustomerPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.parameter.AddCustomerP;
import com.zxdc.utils.library.util.ToastUtil;
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
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.tv_bank)
    TextView tvBank;
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
    private AddCustomerPersenter addCustomerPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        addCustomerPersenter=new AddCustomerPersenter(this);
        tvHead.setText("新增客户");
    }

    @OnClick({R.id.lin_back, R.id.tv_industry, R.id.tv_customer_status, R.id.tv_bank,R.id.tv_private, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //所属行业
            case R.id.tv_industry:
                addCustomerPersenter.selectText(tvIndustry,11);
                break;
            //客户状态
            case R.id.tv_customer_status:
                addCustomerPersenter.selectText(tvCustomerStatus,3);
                break;
            //开户行
            case R.id.tv_bank:
                addCustomerPersenter.selectText(tvBank,4);
                break;
            //私有状态
            case R.id.tv_private:
                addCustomerPersenter.selectText(tvPrivate,5);
                 break;
            //提交
            case R.id.tv_submit:
                String name=etName.getText().toString().trim();
                String industry=tvIndustry.getText().toString().trim();
                String customerStatus=tvCustomerStatus.getText().toString().trim();
                String people=etPeople.getText().toString().trim();
                String mobile=etMobile.getText().toString().trim();
                String position=etPosition.getText().toString().trim();
                String wx=etWx.getText().toString().trim();
                String qq=etQq.getText().toString().trim();
                String email=etEmail.getText().toString().trim();
                String url=etUrl.getText().toString().trim();
                String type=tvType.getText().toString().trim();
                String account=etAccount.getText().toString().trim();
                String bank=tvBank.getText().toString().trim();
                String accountName=etAccountName.getText().toString().trim();
                String ein=etEin.getText().toString().trim();
                String landLine=etLandline.getText().toString().trim();
                String address=etAddress.getText().toString().trim();
                String privateStr=tvPrivate.toString().trim();
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请输入客户名称");
                    return;
                }
//                if(TextUtils.isEmpty(industry)){
//                    ToastUtil.showLong("请选择所属行业");
//                    return;
//                }
//                if(TextUtils.isEmpty(customerStatus)){
//                    ToastUtil.showLong("请选择客户状态");
//                    return;
//                }
                if(TextUtils.isEmpty(people)){
                    ToastUtil.showLong("请输入联系人");
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入手机号");
                    return;
                }
                if(mobile.length()!=11){
                    ToastUtil.showLong("请输入正确的手机号");
                    return;
                }
//                if(TextUtils.isEmpty(position)){
//                    ToastUtil.showLong("请输入职位");
//                    return;
//                }
//                if(TextUtils.isEmpty(wx)){
//                    ToastUtil.showLong("请输入微信号");
//                    return;
//                }
//                if(TextUtils.isEmpty(qq)){
//                    ToastUtil.showLong("请输入QQ号");
//                    return;
//                }
                if(!TextUtils.isEmpty(email) && !email.contains("@")){
                    ToastUtil.showLong("请输入正确的邮箱");
                    return;
                }

//                if(TextUtils.isEmpty(url)){
//                    ToastUtil.showLong("请输入网址");
//                    return;
//                }
//                if(TextUtils.isEmpty(type)){
//                    ToastUtil.showLong("请输入采购种类");
//                    return;
//                }
//                if(TextUtils.isEmpty(account)){
//                    ToastUtil.showLong("请输入对公账号");
//                    return;
//                }
//                if(TextUtils.isEmpty(bank)){
//                    ToastUtil.showLong("请选择开户行");
//                    return;
//                }
//                if(TextUtils.isEmpty(accountName)){
//                    ToastUtil.showLong("请输入户名");
//                    return;
//                }
//                if(TextUtils.isEmpty(ein)){
//                    ToastUtil.showLong("请输入税号");
//                    return;
//                }
//                if(TextUtils.isEmpty(landLine)){
//                    ToastUtil.showLong("请输入座机号");
//                    return;
//                }
//                if(TextUtils.isEmpty(address)){
//                    ToastUtil.showLong("请输入收件地址");
//                    return;
//                }
                if(TextUtils.isEmpty(privateStr)){
                    ToastUtil.showLong("请输入私有状态");
                    return;
                }
                AddCustomerP addCustomerP=new AddCustomerP();
                addCustomerP.setCustomerName(name);
                if(!TextUtils.isEmpty(customerStatus)){
                    addCustomerP.setStatus((int)tvCustomerStatus.getTag());
                }
                if(!TextUtils.isEmpty(industry)){
                    addCustomerP.setIndustry((int)tvIndustry.getTag());
                }
                addCustomerP.setContacts(people);
                addCustomerP.setPosition(position);
                addCustomerP.setPhone(mobile);
                addCustomerP.setWechat(wx);
                addCustomerP.setQq(qq);
                addCustomerP.setEmail(email);
                addCustomerP.setUrl(url);
                addCustomerP.setCorAccount(account);
                addCustomerP.setEin(ein);
                if(!TextUtils.isEmpty(bank)){
                    addCustomerP.setOpenBank((int)tvBank.getTag());
                }
                addCustomerP.setAccName(accountName);
                addCustomerP.setLandline(landLine);
                addCustomerP.setPostAddress(address);
                if(privateStr.equals("私有")){
                    addCustomerP.setPrivateState(1);
                }else{
                    addCustomerP.setPrivateState(2);
                }
                addCustomerP.setMemo(type);
                //增加客户
                addCustomerPersenter.addCustomer(addCustomerP);
                break;
            default:
                break;
        }
    }
}
