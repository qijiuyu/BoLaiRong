package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.NetGridViewImgAdapter;
import com.bian.dan.blr.adapter.sales.PayMentAdapter;
import com.bian.dan.blr.view.MyWatcher;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.google.gson.Gson;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.ConstractDetails;
import com.zxdc.utils.library.bean.Contract;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.AddPaymentP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.MyGridView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 合同详情
 */
public class ContractDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_entry)
    TextView tvEntry;
    @BindView(R.id.tv_entry_time)
    TextView tvEntryTime;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sign_time)
    TextView tvSignTime;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_invoice)
    TextView tvInvoice;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.gridView)
    MyGridView gridView;
    @BindView(R.id.tv_office)
    TextView tvOffice;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private Contract.ListBean listBean;
    //详情对象
    private ConstractDetails constractDetails;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_details);
        ButterKnife.bind(this);
        initView();
        //获取合同详情
        getConstractDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        tvRight.setText("编辑");
        listBean = (Contract.ListBean) getIntent().getSerializableExtra("listBean");
    }


    @OnClick({R.id.lin_back, R.id.tv_right,R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
           //编辑
            case R.id.tv_right:
                if(constractDetails==null){
                    return;
                }
                Intent intent=new Intent(this,AddContractActivity.class);
                intent.putExtra("constractDetails",constractDetails);
                startActivityForResult(intent,100);
                break;
            //添加付款明细
            case R.id.tv_add:
                 showAddPayment();
                 break;
            default:
                break;
        }
    }


    /**
     * 获取合同详情
     */
    private void getConstractDetails() {
        if (listBean == null) {
            return;
        }
        DialogUtil.showProgress(this, "数据加载中");
        HttpMethod.getConstractDetails(String.valueOf(listBean.getId()), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                try {
                    constractDetails = (ConstractDetails) object;
                    if (constractDetails.isSussess()) {
                        ConstractDetails.DetailsBean detailsBean = constractDetails.getContract();
                        if(detailsBean==null){
                            return;
                        }
                        tvEntry.setText(Html.fromHtml("录入人：<font color=\"#000000\">" + detailsBean.getCreateName() + "</font>"));
                        tvEntryTime.setText(Html.fromHtml("录入时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));
                        tvCode.setText(Html.fromHtml("合同编号：<font color=\"#000000\">" + detailsBean.getProp2() + "</font>"));
                        tvName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">" + detailsBean.getCustomerName() + "</font>"));
                        tvSignTime.setText(Html.fromHtml("签订时间：<font color=\"#000000\">" + detailsBean.getSignedTime() + "</font>"));
                        tvMoney.setText(Html.fromHtml("订单金额(元)：<font color=\"#FF4B4C\">" + detailsBean.getAmount() + "</font>"));
                        if (detailsBean.getPayType() == 1) {
                            tvPayType.setText(Html.fromHtml("支付方式：<font color=\"#000000\">全款</font>"));
                        } else {
                            tvPayType.setText(Html.fromHtml("支付方式：<font color=\"#000000\">分期</font>"));
                        }
                        if (detailsBean.getInvoice() == 0) {
                            tvInvoice.setText(Html.fromHtml("是否开票：<font color=\"#000000\">否</font>"));
                        } else {
                            tvInvoice.setText(Html.fromHtml("是否开票：<font color=\"#000000\">是</font>"));
                        }
                        if (detailsBean.getSellers() == 1) {
                            tvCompany.setText(Html.fromHtml("售卖公司：<font color=\"#000000\">博徕荣</font>"));
                        } else {
                            tvCompany.setText(Html.fromHtml("售卖公司：<font color=\"#000000\">立钻</font>"));
                        }
                        tvOffice.setText(Html.fromHtml("指派内勤：<font color=\"#000000\">" + detailsBean.getAssignerName() + "</font>"));

                        /**
                         * 付款明细列表
                         */
                        listView.setAdapter(new PayMentAdapter(activity,detailsBean.getDetailList()));

                        /**
                         * 图片列表
                         */
                        gridView.setAdapter(new NetGridViewImgAdapter(activity,detailsBean.getFileList()));

                        scrollView.scrollTo(0,0);
                    } else {
                        ToastUtil.showLong(constractDetails.getMsg());
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
     * 添加付款明细弹框
     */
    private void showAddPayment(){
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_add_payment,null);
        final PopupWindow popupWindow=DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        final TextView tvTime=view.findViewById(R.id.tv_time);
        final EditText etMoney=view.findViewById(R.id.et_money);
        final EditText etMemo=view.findViewById(R.id.et_memo);
        //限制小数点前后
        etMoney.addTextChangedListener(new MyWatcher(7,2));
        //选择时间
        tvTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectTime(tvTime);
            }
        });
        //提交
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String time=tvTime.getText().toString().trim();
                String money=etMoney.getText().toString().trim();
                String memo=etMemo.getText().toString().trim();
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择付款时间");
                    return;
                }
                if(TextUtils.isEmpty(money)){
                    ToastUtil.showLong("请输入付款金额");
                    return;
                }
                if(Double.parseDouble(money)==0){
                    ToastUtil.showLong("付款金额不能输入0");
                    return;
                }
                popupWindow.dismiss();
                //新增付款明细
                AddPaymentP addPaymentP=new AddPaymentP(constractDetails.getContract().getId(),time,Double.parseDouble(money),memo);
                addPayMent(addPaymentP);
            }
        });
        //关闭
        view.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    /**
     * 选择时间
     * @param tvTime
     */
    private void selectTime(final TextView tvTime){
        CustomDatePicker customDatePicker = new CustomDatePicker(activity, new CustomDatePicker.ResultHandler() {
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTime.setText(time.split(" ")[0]);
            }
        }, "1920-01-01 00:00", "2050-12-31 23:59");
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(true); // 不允许循环滚动

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String now = sdf.format(new Date());
        customDatePicker.show(now.split(" ")[0]);
    }


    /**
     * 新增付款明细
     */
    private void addPayMent(AddPaymentP addPaymentP){
        LogUtils.e("++++++++++++"+new Gson().toJson(addPaymentP));
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.addPayMent(addPaymentP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    //获取合同详情
                    getConstractDetails();
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
        if(resultCode==1000){
            //获取合同详情
            getConstractDetails();
        }
    }

}
