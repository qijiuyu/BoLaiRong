package com.bian.dan.blr.persenter.sales;

import android.text.TextUtils;
import android.widget.TextView;

import com.bian.dan.blr.activity.main.warehouse.SdEnterActivity;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.zxdc.utils.library.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SdEnterPersenter {

    private SdEnterActivity activity;

    public SdEnterPersenter(SdEnterActivity activity){
        this.activity=activity;
    }


    /**
     * 选择采购开始时间
     */
    public void selectStartTime(final TextView tvStartTime,final TextView tvEndTime){
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        CustomDatePicker customDatePicker = new CustomDatePicker(activity, new CustomDatePicker.ResultHandler() {
            public void handle(String time) { // 回调接口，获得选中的时间
                try {
                    String endTime=tvEndTime.getText().toString().trim();
                    if(!TextUtils.isEmpty(endTime)){
                        long startL=sdf.parse(time.split(" ")[0]).getTime();
                        long endL=sdf.parse(endTime).getTime();
                        if(startL>endL){
                            ToastUtil.showLong("开始日期不能大于结束日期");
                            return;
                        }
                    }
                    tvStartTime.setText(time.split(" ")[0]);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "1920-01-01 00:00", "2050-12-31 23:59");
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(true); // 不允许循环滚动

        customDatePicker.show(sdf.format(new Date()));
    }


    /**
     * 选择采购结束日期
     */
    public void selectEndTime(final TextView tvStartTime,final TextView tvEndTime){
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        CustomDatePicker customDatePicker = new CustomDatePicker(activity, new CustomDatePicker.ResultHandler() {
            public void handle(String time) { // 回调接口，获得选中的时间
                try {
                    String startTime=tvStartTime.getText().toString().trim();
                    if(!TextUtils.isEmpty(startTime)){
                        long startL=sdf.parse(startTime).getTime();
                        long endL=sdf.parse(time.split(" ")[0]).getTime();
                        if(endL<startL){
                            ToastUtil.showLong("结束日期不能小于开始日期");
                            return;
                        }
                    }
                    tvEndTime.setText(time.split(" ")[0]);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "1920-01-01 00:00", "2050-12-31 23:59");
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(true); // 不允许循环滚动

        customDatePicker.show(sdf.format(new Date()));
    }
}
