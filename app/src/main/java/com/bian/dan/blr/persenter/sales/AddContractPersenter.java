package com.bian.dan.blr.persenter.sales;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.AddContractActivity;
import com.bian.dan.blr.utils.SelectPhoto;
import com.bian.dan.blr.view.CycleWheelView;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.CheckCode;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Office;
import com.zxdc.utils.library.bean.Upload;
import com.zxdc.utils.library.bean.parameter.AddContractP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddContractPersenter {

    private AddContractActivity activity;

    //指派内勤列表
    private List<Office.ListBean> officeList=new ArrayList<>();

    public AddContractPersenter(AddContractActivity activity){
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
     * 下拉框选择
     */
    public void selectText(final TextView textView,final int type){
        View view= LayoutInflater.from(activity).inflate(R.layout.min_wheel_select,null);
        final PopupWindow popupWindow=DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            if(type==1){
                list.add("全款");
                list.add("分期");
            }else if(type==2){
                list.add("否");
                list.add("是");
            }else if(type==3){
                list.add("博徕荣");
                list.add("立钻");
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
     * 选择指派内勤
     */
    TextView tvOffice;
    public void selectOffice(final TextView tvOffice){
        this.tvOffice=tvOffice;
        if(officeList.size()==0){
            getOffice();
            return;
        }
        View view= LayoutInflater.from(activity).inflate(R.layout.wheel_select,null);
        final PopupWindow popupWindow=DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            for (int i=0;i<officeList.size();i++){
                  list.add(officeList.get(i).getName());
            }
            wheel.setLabels(list);
            wheel.setSelection(0);
            wheel.setWheelSize(5);
            wheel.setCycleEnable(false);
            wheel.setAlphaGradual(0.5f);
            wheel.setDivider(Color.parseColor("#abcdef"),1);
            wheel.setSolid(Color.WHITE,Color.WHITE);
            wheel.setLabelColor(Color.GRAY);
            wheel.setLabelSelectColor(Color.BLACK);
            wheel.setOnWheelItemSelectedListener(new CycleWheelView.WheelItemSelectedListener() {
                public void onItemSelected(int position, String label) {
                    tvOffice.setText(label);
                    tvOffice.setTag(officeList.get(position).getUserId());
                }
            });

            view.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    popupWindow.dismiss();
                    tvOffice.setText(null);
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
     * 获取指派内勤
     */
    public void getOffice(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getOffice(14, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                Office office= (Office) object;
                if(office==null){
                    return;
                }
                if(office.isSussess()){
                    officeList.addAll(office.getPage().getRows());
                    //选择指派内勤
                    selectOffice(tvOffice);
                }else{
                    ToastUtil.showLong(office.getMsg());
                }
            }
            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 上传文件
     */
    public void uploadFile(final List<LocalMedia> imgList){
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
     * 删除文件
     */
    public void deleteFile(String id){
        DialogUtil.showProgress(activity,"图片删除中");
        HttpMethod.deleteFile(id, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                ToastUtil.showLong(baseBean.getMsg());
            }
            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 编辑文件时上传图片
     */
    public void uploadByFileAndTypeAndFid(final int pid, final List<LocalMedia> imgList){
        List<String> uploadList=new ArrayList<>();
        for (int i=0;i<imgList.size();i++){
            final String imgPath=imgList.get(i).getCompressPath();
            if(!imgPath.startsWith("http://")){
                uploadList.add(imgPath);
            }
        }
        DialogUtil.showProgress(activity,"图片上传中");
        for (int i=0;i<uploadList.size();i++){
            final File file=new File(uploadList.get(i));
            HttpMethod.uploadByFileAndTypeAndFid(file.getName(), file, "1", String.valueOf(pid), new NetWorkCallBack() {
                public void onSuccess(Object object) {
                    DialogUtil.closeProgress();
                    Upload upload= (Upload) object;
                    if(upload.isSussess()){
                        activity.uploadSuccess(upload.getResPath(),upload.getId());
                    }else{
                        ToastUtil.showLong(upload.getMsg());
                    }
                }
                public void onFail(Throwable t) {

                }
            });
        }

    }


    /**
     * 校验合同编码唯一性
     */
    public void checkCode(final AddContractP addContractP){
        DialogUtil.showProgress(activity,"数据校验中");
        HttpMethod.checkCode(addContractP.getProp2().toString(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                CheckCode checkCode= (CheckCode) object;
                if(checkCode.isSussess()){
                    if(checkCode.isResult()){
                        //添加合同
                        addContract(addContractP);
                    }else{
                        ToastUtil.showLong(checkCode.getMsg());
                    }
                }
            }
            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 添加合同
     */
    public void  addContract(AddContractP addContractP){
        DialogUtil.showProgress(activity,"数据上传中");
        HttpMethod.addContract(addContractP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean)object;
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


    /**
     * 编辑合同
     */
    public void editContract(AddContractP addContractP){
        DialogUtil.showProgress(activity,"数据修改中");
        HttpMethod.editContract(addContractP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean)object;
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
