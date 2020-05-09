package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Device;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("设备管理");
        Device.ListBean listBean= (Device.ListBean) getIntent().getSerializableExtra("listBean");
        if(listBean!=null){
            //获取设备详情
            getDeviceDetails(String.valueOf(listBean.getId()));
        }
    }

    @OnClick({R.id.lin_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                break;
            case R.id.tv_right:
                break;
        }
    }


    /**
     * 获取设备详情
     */
    private void getDeviceDetails(String id){
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getDeviceList(null, id,null, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                DialogUtil.closeProgress();
                Device device= (Device) object;
                if(device==null){
                    return;
                }
                if(device.isSussess() && device.getData()!=null && device.getData().getRows()!=null){
                    if(device.getData().getRows().size()>0){
                        Device.ListBean listBean=device.getData().getRows().get(0);
                        tvDepartment.setText(Html.fromHtml("归属部门：<font color=\"#000000\">"+listBean.getDeptName()+"</font>"));
                        tvType.setText(Html.fromHtml("设备类型：<font color=\"#000000\">"+listBean.getTypeName()+"</font>"));
                        tvName.setText(Html.fromHtml("设备名称：<font color=\"#000000\">"+listBean.getEquipName()+"</font>"));
                        tvSpce.setText(Html.fromHtml("规格型号：<font color=\"#000000\">"+listBean.getSpec()+"</font>"));
                        tvCode.setText(Html.fromHtml("设备编码：<font color=\"#000000\">"+listBean.getCode()+"</font>"));
                        tvManufacturer.setText(Html.fromHtml("生产厂家：<font color=\"#000000\">"+listBean.getManufacturers()+"</font>"));
                        tvTime.setText(Html.fromHtml("采购时间：<font color=\"#000000\">"+listBean.getPurcTime()+"</font>"));
                        tvMoney.setText(Html.fromHtml("采购金额：<font color=\"#000000\">"+listBean.getAmount()+"</font>"));
                    }
                }else{
                    ToastUtil.showLong(device.getMsg());
                }
            }
            public void onFail(Throwable t) {

            }
        });
    }
}
