package com.bian.dan.blr.persenter.procurement;

import android.widget.TextView;

import com.bian.dan.blr.activity.main.procurement.AddProcurementActivity;
import com.bian.dan.blr.view.time.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddProcurementPersenter {

    private AddProcurementActivity activity;

    public AddProcurementPersenter(AddProcurementActivity activity){
        this.activity=activity;
    }


    /**
     * 选择时间
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
        tvTime.setText(now.split(" ")[0]);
        customDatePicker.show(tvTime.getText().toString());
    }
}
