package com.bian.dan.blr.persenter.warehouse;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.warehouse.AddDeviceActivity;
import com.bian.dan.blr.utils.SelectPhoto;
import com.bian.dan.blr.view.CycleWheelView;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Department;
import com.zxdc.utils.library.bean.DeviceType;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Upload;
import com.zxdc.utils.library.bean.parameter.AddDeviceP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddDevicePersenter {

    private AddDeviceActivity activity;
    //部门列表
    private List<Department.DepartmentBean> depList=new ArrayList<>();
    //设备类型集合
    private List<DeviceType.DeviceTypeBean> typeList=new ArrayList<>();
    //图片上传成功后，服务器返回的链接
    private List<String> imgPath=new ArrayList<>();
    private TextView textView;

    public AddDevicePersenter(AddDeviceActivity activity){
        this.activity=activity;
    }



    /**
     * 选择照片
     */
    public void SelectPhoto(){
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_photo, null);
        final PopupWindow popupWindow = DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        //拍照
        view.findViewById(R.id.tv_picture).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                SelectPhoto.selectPhoto(activity,1);
            }
        });
        //从相册选择
        view.findViewById(R.id.tv_photo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                PictureSelector.create(activity)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(5)
                        .minSelectNum(1)
                        .imageSpanCount(3)
                        .selectionMode(PictureConfig.MULTIPLE)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
        view.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    /**
     * 选择时间
     * @param tvTime
     */
    public void selectTime(final TextView tvTime){
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
     * 1：选择部门
     * 2：设备类型
     */
    public void selectDeartment(final TextView textView,final int type){
        this.textView=textView;
        if(type==1 && depList.size()==0){
            //获取部门列表
            getDepartment();
            return;
        }
        if(type==2 && typeList.size()==0){
            //获取设备类型
            getDeviceType();
            return;
        }
        View view= LayoutInflater.from(activity).inflate(R.layout.min_wheel_select,null);
        final PopupWindow popupWindow=DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            if(type==1){
                for (int i=0;i<depList.size();i++){
                    list.add(depList.get(i).getName());
                }
            }else{
                for (int i=0;i<typeList.size();i++){
                    list.add(typeList.get(i).getName());
                }
            }
            wheel.setLabels(list);
            wheel.setSelection(0);
            wheel.setWheelSize(3);
            wheel.setCycleEnable(false);
            wheel.setAlphaGradual(0.5f);
            wheel.setDivider(Color.parseColor("#abcdef"),1);
            wheel.setSolid(Color.WHITE,Color.WHITE);
            wheel.setLabelColor(Color.GRAY);
            wheel.setLabelSelectColor(Color.BLACK);
            wheel.setOnWheelItemSelectedListener(new CycleWheelView.WheelItemSelectedListener() {
                public void onItemSelected(int position, String label) {
                    textView.setText(label);
                    if(type==1){
                        textView.setTag(depList.get(position).getId());
                    }else{
                        textView.setTag(typeList.get(position).getId());
                    }
                }
            });

            view.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    popupWindow.dismiss();
                    textView.setText(null);
                }
            });
            view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 获取部门列表
     */
    public void getDepartment(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getDepartment(new NetWorkCallBack() {
            public void onSuccess(Object object) {
                Department department= (Department) object;
                if(department==null){
                    return;
                }
                if(department.isSussess()){
                    depList.addAll(department.getPage());
                    //选择部门
                    selectDeartment(textView,1);
                }else{
                    ToastUtil.showLong(department.getMsg());
                }
            }

            public void onFail(Throwable t) {
            }
        });
    }


    /**
     * 获取设备类型
     */
    public void getDeviceType(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getDeviceType(new NetWorkCallBack(){
            public void onSuccess(Object object) {
                DeviceType deviceType= (DeviceType)object;
                if(deviceType==null){
                    return;
                }
                if(deviceType.isSussess()){
                    typeList.addAll(deviceType.getList());
                    //选择设备类型
                    selectDeartment(textView,2);
                }else{
                    ToastUtil.showLong(deviceType.getMsg());
                }
            }
            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 上传文件
     */
    public void uploadFile( final List<LocalMedia> imgList){
        DialogUtil.showProgress(activity,"图片上传中");
        for (int i=0;i<imgList.size();i++){
             final File file=new File(imgList.get(i).getCompressPath());
             HttpMethod.uploadFile(file.getName(), file, new NetWorkCallBack() {
                 public void onSuccess(Object object) {
                     DialogUtil.closeProgress();
                     Upload upload= (Upload) object;
                     if(upload.isSussess()){
                         activity.uploadSuccess(upload.getResPath(),-1);
                     }
                 }
                 public void onFail(Throwable t) {

                 }
             });
        }
    }


    /**
     * 添加设备
     */
    public void  addDevice(AddDeviceP addDeviceP){
        DialogUtil.showProgress(activity,"数据上传中");
        HttpMethod.addDevice(addDeviceP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                DialogUtil.closeProgress();
                BaseBean baseBean= (BaseBean)object;
                if(baseBean==null){
                    return;
                }
                if(baseBean.isSussess()){
                    Intent intent=new Intent();
                    activity.setResult(1000,intent);
                    activity.finish();
                }
                ToastUtil.showLong(baseBean.getMsg());

            }
            public void onFail(Throwable t) {

            }
        });
    }

}
