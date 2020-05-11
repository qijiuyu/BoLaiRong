package com.bian.dan.blr.persenter.sales;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.AddFinancialActivity;
import com.bian.dan.blr.utils.SelectPhoto;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Upload;
import com.zxdc.utils.library.bean.parameter.AddFinancialP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.io.File;
import java.util.List;

public class AddFinancialPersenter {

    public AddFinancialActivity activity;

    public AddFinancialPersenter(AddFinancialActivity activity){
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
     * 添加财务报销
     */
    public void addFinancial(AddFinancialP addFinancialP){
        DialogUtil.showProgress(activity,"上传中");
        HttpMethod.addFinancial(addFinancialP, new NetWorkCallBack() {
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
