package com.bian.dan.blr.persenter.financial;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.financial.TransferActivity;
import com.bian.dan.blr.utils.SelectPhoto;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Upload;
import com.zxdc.utils.library.bean.parameter.TransferP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TransferPersenter {

    private TransferActivity activity;

    public TransferPersenter(TransferActivity activity){
        this.activity=activity;
    }


    /**
     * 选择时间
     * @param tvTime
     */
    public void selectTime(final TextView tvTime){
        CustomDatePicker customDatePicker = new CustomDatePicker(activity, new CustomDatePicker.ResultHandler() {
            public void handle(String time) { // 回调接口，获得选中的时间
                Calendar calendar = Calendar.getInstance();
                //秒钟
                int intSeconds=calendar.get(Calendar.SECOND);
                if(intSeconds<10){
                    tvTime.setText(time+":0"+intSeconds);
                }else{
                    tvTime.setText(time+":"+intSeconds);
                }
            }
        }, "1920-01-01 00:00", "2050-12-31 23:59");
        customDatePicker.showSpecificTime(true); // 不显示时和分
        customDatePicker.setIsLoop(true); // 不允许循环滚动

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String now = sdf.format(new Date());
        customDatePicker.show(now);
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
                        .maxSelectNum(1)
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
     * 上传文件
     */
    public void uploadFile(final LocalMedia localMedia){
        DialogUtil.showProgress(activity,"图片上传中");
        File file=new File(localMedia.getCompressPath());
        HttpMethod.uploadFile(file.getName(), file, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                DialogUtil.closeProgress();
                Upload upload= (Upload) object;
                if(upload.isSussess()){
                    activity.uploadSuccess(upload.getResPath());
                }
            }
            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 修改财务报销
     */
    public void addTransferP(TransferP transferP){
        DialogUtil.showProgress(activity,"转账中");
        HttpMethod.addTransferP(transferP, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    activity.setResult(1000,new Intent());
                    activity.finish();
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
