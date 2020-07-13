package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.GridViewImgAdapter;
import com.bian.dan.blr.persenter.warehouse.AddDevicePersenter;
import com.bian.dan.blr.utils.SelectPhoto;
import com.bian.dan.blr.view.MyWatcher;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.DeviceDetails;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.parameter.AddDeviceP;
import com.zxdc.utils.library.bean.parameter.FileList;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_spec)
    EditText etSpec;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_manufacturer)
    EditText etManufacturer;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.gridView)
    MyGridView gridView;
    //选择的照片
    private List<FileBean> imgList=new ArrayList<>();
    private GridViewImgAdapter gridViewImgAdapter;
    private AddDevicePersenter addDevicePersenter;
    //要编辑的对象
    private DeviceDetails.DetailsBean detailsBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        ButterKnife.bind(this);
        initView();
        //编辑前显示对应的数据
        showData();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增设备");
        addDevicePersenter=new AddDevicePersenter(this);
        detailsBean= (DeviceDetails.DetailsBean) getIntent().getSerializableExtra("detailsBean");

        //限制小数点前后
        etMoney.addTextChangedListener(new MyWatcher(7,2));

        //图片列表
        gridViewImgAdapter=new GridViewImgAdapter(this,imgList);
        gridView.setAdapter(gridViewImgAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==imgList.size()){
                    addDevicePersenter.SelectPhoto();
                }
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_department, R.id.tv_type, R.id.tv_time, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择部门
            case R.id.tv_department:
                addDevicePersenter.selectDeartment(tvDepartment,1);
                break;
            //选择设备类型
            case R.id.tv_type:
                addDevicePersenter.selectDeartment(tvType,2);
                break;
            //选择时间
            case R.id.tv_time:
                addDevicePersenter.selectTime(tvTime);
                break;
            //选择提交
            case R.id.tv_submit:
                String department=tvDepartment.getText().toString().trim();
                String type=tvType.getText().toString().trim();
                String name=etName.getText().toString().trim();
                String spec=etSpec.getText().toString().trim();
                String code=etCode.getText().toString().trim();
                String manufacturer=etManufacturer.getText().toString().trim();
                String time=tvTime.getText().toString().trim();
                String money=etMoney.getText().toString().trim();
                if(TextUtils.isEmpty(department)){
                    ToastUtil.showLong("请选择归属部门");
                    return;
                }
                if(TextUtils.isEmpty(type)){
                    ToastUtil.showLong("请选择设备类型");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请输入设备名称");
                    return;
                }
                if(TextUtils.isEmpty(spec)){
                    ToastUtil.showLong("请输入规格/型号");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showLong("请输入设备编码");
                    return;
                }
                if(TextUtils.isEmpty(manufacturer)){
                    ToastUtil.showLong("请输入生产厂家");
                    return;
                }
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择采购时间");
                    return;
                }
                if(TextUtils.isEmpty(money)){
                    ToastUtil.showLong("请输入采购金额");
                    return;
                }
                if(Double.parseDouble(money)==0){
                    ToastUtil.showLong("采购金额不能为0");
                    return;
                }
                if(imgList.size()==0){
                    ToastUtil.showLong("请选择设备图片");
                    return;
                }
                AddDeviceP addDeviceP=new AddDeviceP();
                addDeviceP.setEquipName(name);
                addDeviceP.setSpec(spec);
                addDeviceP.setEquipType((int)tvType.getTag());
                addDeviceP.setCode(code);
                addDeviceP.setManufacturers(manufacturer);
                addDeviceP.setAmount(Double.parseDouble(money));
                addDeviceP.setDeptId((int)tvDepartment.getTag());
                addDeviceP.setPurcTime(time);

                //解析要提交的图片链接
                if(detailsBean==null){
                    List<FileList> list=new ArrayList<>();
                    for (int i = 0; i < imgList.size(); i++) {
                        FileList fileList=new FileList();
                        fileList.setUrl(imgList.get(i).getUrl());
                        list.add(fileList);
                    }
                    addDeviceP.setFileList(list);
                }

                if(detailsBean==null){ //新增
                    addDevicePersenter.addDevice(addDeviceP);
                }else{                 //编辑
                    addDeviceP.setId(detailsBean.getId());
                    if(!detailsBean.getEquipName().equals(name) || !detailsBean.getSpec().equals(spec) || !detailsBean.getCode().equals(code)){
                        addDeviceP.setFlag(1);
                    }
                    addDevicePersenter.updateDevice(addDeviceP);
                }
                LogUtils.e("+++++++++++++++"+new Gson().toJson(addDeviceP));
                break;
            default:
                break;
        }
    }



    /**
     * 编辑前显示对应的数据
     */
    private void showData(){
        if(detailsBean==null){
            return;
        }
        tvHead.setText("编辑设备");
        tvDepartment.setText(detailsBean.getDeptName());
        tvDepartment.setTag(detailsBean.getDeptId());
        tvType.setText(detailsBean.getTypeName());
        tvType.setTag(detailsBean.getEquipType());
        etName.setText(detailsBean.getEquipName());
        etSpec.setText(detailsBean.getSpec());
        etCode.setText(detailsBean.getCode());
        etManufacturer.setText(detailsBean.getManufacturers());
        tvTime.setText(detailsBean.getPurcTime());
        etMoney.setText(detailsBean.getAmount()+"");
        /**
         * 显示图片
         */
        imgList.addAll(detailsBean.getFileList());
        gridViewImgAdapter=new GridViewImgAdapter(this,imgList);
        gridView.setAdapter(gridViewImgAdapter);
    }


    /**
     * 编辑的时候删除图片
     */
    public void deleteImg(FileBean fileBean){
        addDevicePersenter.deleteFile(String.valueOf(fileBean.getId()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //返回拍照图片
            case SelectPhoto.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File tempFile = new File(SelectPhoto.pai);
                    if (tempFile.isFile()) {
                        List<LocalMedia> list=new ArrayList<>();
                        LocalMedia localMedia=new LocalMedia();
                        localMedia.setCompressPath(SelectPhoto.pai);
                        list.add(localMedia);
                        if(detailsBean==null){
                            //增加设备-上传图片
                            addDevicePersenter.uploadFile(list);
                        }else{
                            //编辑设备-上传图片
                            addDevicePersenter.uploadByFileAndTypeAndFid(detailsBean.getId(),list);
                        }
                    }
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                if(list.size()==0){
                    return;
                }
                if(detailsBean==null){
                    //增加设备-上传图片
                    addDevicePersenter.uploadFile(list);
                }else{
                    //编辑设备-上传图片
                    addDevicePersenter.uploadByFileAndTypeAndFid(detailsBean.getId(),list);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 图片上传成功
     * @param path
     */
    public void uploadSuccess(final String path, final int pid){
        runOnUiThread(new Runnable() {
            public void run() {
                FileBean fileBean;
                fileBean = new FileBean(pid,path);
                imgList.add(fileBean);
                gridViewImgAdapter=new GridViewImgAdapter(activity,imgList);
                gridView.setAdapter(gridViewImgAdapter);
            }
        });
    }
}
