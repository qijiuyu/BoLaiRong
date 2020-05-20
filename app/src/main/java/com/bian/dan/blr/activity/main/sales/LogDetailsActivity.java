package com.bian.dan.blr.activity.main.sales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.bean.Log;
import com.zxdc.utils.library.bean.LogDetails;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 日志详情
 */
public class LogDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.tv_play_name)
    TextView tvPlayName;
    @BindView(R.id.tv_play_time)
    TextView tvPlayTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_result)
    TextView tvResult;
    private Log.ListBean listBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_details);
        ButterKnife.bind(this);
        initView();
        //获取日志详情
        getLogDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        listBean= (Log.ListBean) getIntent().getSerializableExtra("listBean");
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 获取日志详情
     */
    private void getLogDetails(){
        if(listBean==null){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getLogDetails(listBean.getId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                try {
                    LogDetails logDetails= (LogDetails) object;
                    LogDetails.DetailsBean detailsBean=logDetails.getData();
                    if(detailsBean==null){
                        return;
                    }
                    tvPlayName.setText(Html.fromHtml("操作人：<font color=\"#000000\">" + detailsBean.getOperater() + "</font>"));
                    tvPlayTime.setText(Html.fromHtml("操作时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));
                    Customer customer=detailsBean.getCustomer();
                    if(customer==null){
                        return;
                    }
                    tvName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">" + customer.getCustomerName()+ "</font>"));
                    tvPeople.setText(Html.fromHtml("联系人：<font color=\"#000000\">" + customer.getContacts()+ "</font>"));
                    tvMobile.setText(Html.fromHtml("手机号：<font color=\"#000000\">" + customer.getPhone()+ "</font>"));
                    tvPosition.setText(Html.fromHtml("职位：<font color=\"#000000\">" + customer.getPosition()+ "</font>"));
                    tvWx.setText(Html.fromHtml("微信号：<font color=\"#000000\">" + customer.getWechat()+ "</font>"));
                    tvQq.setText(Html.fromHtml("QQ：<font color=\"#000000\">" + customer.getQq()+ "</font>"));
                    tvEmail.setText(Html.fromHtml("邮箱：<font color=\"#000000\">" + customer.getEmail()+ "</font>"));
                    tvAddress.setText(Html.fromHtml("收件地址：<font color=\"#000000\">" + customer.getPostAddress()+ "</font>"));
                    tvResult.setText(Html.fromHtml("跟进结果：<font color=\"#000000\">" + detailsBean.getFollowResult()+ "</font>"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
