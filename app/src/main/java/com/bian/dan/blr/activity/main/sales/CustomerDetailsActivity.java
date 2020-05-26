package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.application.MyApplication;
import com.bian.dan.blr.view.CustomerFollow;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.bean.CustomerDetails;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.parameter.UpdateCustomerStateP;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.eventbus.EventStatus;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.tv_customer_status)
    TextView tvCustomerStatus;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_position)
    TextView tvPostion;
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
    @BindView(R.id.tv_get)
    TextView tvGet;
    private Customer customer;
    /**
     * 1：私有
     * 2：公有
     */
    private int privateState;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        ButterKnife.bind(this);
        initView();
        //获取客户详情
        getCustomerDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        tvRight.setText("编辑");
        customer = (Customer) getIntent().getSerializableExtra("customer");
        privateState = getIntent().getIntExtra("privateState", 1);
        if (privateState == 2) {
            tvRight.setVisibility(View.GONE);
        }else{
            tvGet.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.lin_back, R.id.tv_right, R.id.tv_follow_list,R.id.tv_get})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //编辑
            case R.id.tv_right:
                intent.setClass(this,AddCustomerActivity.class);
                intent.putExtra("customer",customer);
                startActivityForResult(intent,1000);
                break;
           //查看跟进记录
            case R.id.tv_follow_list:
                 CustomerFollow customerFollow=new CustomerFollow(this,customer.getId());
                 customerFollow.show();
                 break;
            //客户信息-修改私有状态
            case R.id.tv_get:
                updateCustomerState();
                break;
            default:
                break;
        }
    }

    /**
     * 获取客户详情
     */
    private void getCustomerDetails() {
        if (customer == null) {
            return;
        }
        DialogUtil.showProgress(this, "数据加载中");
        HttpMethod.getCustomerDetails(customer.getId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                try {
                    CustomerDetails customerDetails = (CustomerDetails) object;
                    if (customerDetails.isSussess()) {
                        Customer customer = customerDetails.getCustomer();
                        if (customer == null) {
                            return;
                        }
                        tvCustomerName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">" + customer.getCustomerName() + "</font>"));
                        tvIndustry.setText(Html.fromHtml("所属行业：<font color=\"#000000\">" + customer.getIndustryStr() + "</font>"));
                        tvCustomerStatus.setText(Html.fromHtml("客户状态：<font color=\"#000000\">" + customer.getStatusName() + "</font>"));
                        tvPeople.setText(Html.fromHtml("联系人：<font color=\"#000000\">" + customer.getContacts() + "</font>"));
                        tvMobile.setText(Html.fromHtml("手机号：<font color=\"#000000\">" + customer.getPhone() + "</font>"));
                        tvPostion.setText(Html.fromHtml("职位：<font color=\"#000000\">" + customer.getPosition() + "</font>"));
                        tvWx.setText(Html.fromHtml("微信号：<font color=\"#000000\">" + customer.getWechat() + "</font>"));
                        tvQq.setText(Html.fromHtml("QQ号：<font color=\"#000000\">" + customer.getQq() + "</font>"));
                        tvEmail.setText(Html.fromHtml("邮箱：<font color=\"#000000\">" + customer.getEmail() + "</font>"));
                        tvUrl.setText(Html.fromHtml("网址：<font color=\"#000000\">" + customer.getUrl() + "</font>"));
                        tvProcurementType.setText(Html.fromHtml("采购种类：<font color=\"#000000\">" + customer.getMemo() + "</font>"));
                        tvAccount.setText(Html.fromHtml("对公账户：<font color=\"#000000\">" + customer.getCorAccount() + "</font>"));
                        tvBank.setText(Html.fromHtml("开户行：<font color=\"#000000\">" + customer.getOpenBankStr() + "</font>"));
                        tvCustomerName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">" + customer.getCustomerName() + "</font>"));
                        tvAccountName.setText(Html.fromHtml("户名：<font color=\"#000000\">" + customer.getAccName() + "</font>"));
                        tvEin.setText(Html.fromHtml("税号：<font color=\"#000000\">" + customer.getEin() + "</font>"));
                        tvLandline.setText(Html.fromHtml("座机号：<font color=\"#000000\">" + customer.getLandline() + "</font>"));

                        tvAddress.setText(Html.fromHtml("收件地址：<font color=\"#000000\">" + customer.getPostAddress() + "</font>"));

                    } else {
                        ToastUtil.showLong(customerDetails.getMsg());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }

    /**
     * 客户信息-修改私有状态
     */
    private void updateCustomerState(){
        if (customer == null) {
            return;
        }
        DialogUtil.showProgress(this,"领取中");
        final UserInfo userInfo= MyApplication.getUser();
        UpdateCustomerStateP updateCustomerStateP=new UpdateCustomerStateP(customer.getId(), userInfo.getUser().getUserId(),1);
        HttpMethod.updateCustomerState(updateCustomerStateP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean)object;
                if(baseBean.isSussess()){
                    //加载数据
                    EventBus.getDefault().post(new EventBusType(EventStatus.REFRESH_CUSTOMER_LIST));
                    finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            //重新刷新详情
            case 1000:
                getCustomerDetails();
                break;
            default:
                break;
        }
    }
}
