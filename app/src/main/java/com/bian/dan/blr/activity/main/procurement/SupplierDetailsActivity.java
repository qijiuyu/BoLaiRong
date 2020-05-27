package com.bian.dan.blr.activity.main.procurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.procurement.SupplierDetails_Product_Adapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SupplierDetails;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplierDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_industry)
    TextView tvIndustry;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.tv_memo)
    TextView tvMemo;
    @BindView(R.id.tv_right)
    TextView tvRight;
    //详情id
    private int detailsId;
    //详情对象
    private SupplierDetails.DetailsBean detailsBean;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_details);
        ButterKnife.bind(this);
        initView();
        //供应商明细
        getSupplierDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        tvRight.setText("编辑");
        detailsId = getIntent().getIntExtra("detailsId", 0);
    }


    @OnClick({R.id.lin_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                 break;
            //编辑
            case R.id.tv_right:
                 if(detailsBean==null){
                     return;
                 }
                 Intent intent=new Intent(this,AddSupplierActivity.class);
                 intent.putExtra("detailsBean",detailsBean);
                 startActivityForResult(intent,1000);
                break;
            default:
                break;
        }
    }


    /**
     * 供应商明细
     */
    private void getSupplierDetails() {
        if (detailsId == 0) {
            return;
        }
        DialogUtil.showProgress(this, "数据加载中");
        HttpMethod.getSupplierDetails(detailsId, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                SupplierDetails supplierDetails = (SupplierDetails) object;
                if (supplierDetails.isSussess()) {
                    detailsBean = supplierDetails.getData();
                    if (detailsBean == null) {
                        return;
                    }
                    tvName.setText(Html.fromHtml("供应商名称：<font color=\"#000000\">" + detailsBean.getSupplierName() + "</font>"));

                    tvIndustry.setText(Html.fromHtml("所属行业：<font color=\"#000000\">" + detailsBean.getIndustryStr() + "</font>"));
                    tvContact.setText(Html.fromHtml("联系人：<font color=\"#000000\">" + detailsBean.getContacts() + "</font>"));
                    tvMobile.setText(Html.fromHtml("电话：<font color=\"#000000\">" + detailsBean.getPhone() + "</font>"));
                    tvAddress.setText(Html.fromHtml("地址：<font color=\"#000000\">" + detailsBean.getSupplierAddress() + "</font>"));
                    tvMemo.setText(Html.fromHtml("备注：<font color=\"#000000\">" + detailsBean.getMemo() + "</font>"));

                    /**
                     * 物料列表
                     */
                    listView.setAdapter(new SupplierDetails_Product_Adapter(activity, detailsBean.getSupplierDetailList()));
                } else {
                    ToastUtil.showLong(supplierDetails.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
