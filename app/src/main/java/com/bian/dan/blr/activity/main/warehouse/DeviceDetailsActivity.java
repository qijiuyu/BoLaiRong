package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.NetGridViewImgAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Device;
import com.zxdc.utils.library.bean.DeviceDetails;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 设备详情
 */
public class DeviceDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_spce)
    TextView tvSpce;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_manufacturer)
    TextView tvManufacturer;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.gridView)
    MyGridView gridView;
    @BindView(R.id.tv_right)
    TextView tvRight;
    //列表对象
    private Device.ListBean listBean;
    //详情对象
    private DeviceDetails.DetailsBean detailsBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);
        ButterKnife.bind(this);
        initView();
        //获取设备详情
        getDeviceDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("设备管理");
        tvRight.setText("编辑");
        listBean= (Device.ListBean) getIntent().getSerializableExtra("listBean");
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
                 Intent intent=new Intent(this,AddDeviceActivity.class);
                 intent.putExtra("detailsBean",detailsBean);
                 startActivityForResult(intent,100);
                break;
            default:
                break;
        }
    }


    /**
     * 获取设备详情
     */
    private void getDeviceDetails(){
        if(listBean==null){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getDeviceDetails(listBean.getId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                DeviceDetails deviceDetails= (DeviceDetails) object;
                if(deviceDetails.isSussess()){
                    detailsBean=deviceDetails.getData();
                    if(detailsBean!=null){
                        tvDepartment.setText(Html.fromHtml("归属部门：<font color=\"#000000\">"+detailsBean.getDeptName()+"</font>"));
                        tvType.setText(Html.fromHtml("设备类型：<font color=\"#000000\">"+detailsBean.getTypeName()+"</font>"));
                        tvName.setText(Html.fromHtml("设备名称：<font color=\"#000000\">"+detailsBean.getEquipName()+"</font>"));
                        tvSpce.setText(Html.fromHtml("规格型号：<font color=\"#000000\">"+detailsBean.getSpec()+"</font>"));
                        tvCode.setText(Html.fromHtml("设备编码：<font color=\"#000000\">"+detailsBean.getCode()+"</font>"));
                        tvManufacturer.setText(Html.fromHtml("生产厂家：<font color=\"#000000\">"+detailsBean.getManufacturers()+"</font>"));
                        tvTime.setText(Html.fromHtml("采购时间：<font color=\"#000000\">"+detailsBean.getPurcTime()+"</font>"));
                        tvMoney.setText(Html.fromHtml("采购金额：<font color=\"#000000\">"+detailsBean.getAmount()+"</font>"));
                        gridView.setAdapter(new NetGridViewImgAdapter(activity,detailsBean.getFileList()));
                    }
                }else{
                    ToastUtil.showLong(deviceDetails.getMsg());
                }
            }
            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            //获取设备详情
            getDeviceDetails();
        }
    }
}
