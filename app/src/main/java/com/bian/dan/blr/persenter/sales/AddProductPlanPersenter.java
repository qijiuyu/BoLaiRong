package com.bian.dan.blr.persenter.sales;

import android.content.Intent;
import android.widget.TextView;

import com.bian.dan.blr.activity.main.sales.AddProductPlanActivity;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.AddProductPlanP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddProductPlanPersenter {

    private AddProductPlanActivity activity;

    public AddProductPlanPersenter(AddProductPlanActivity activity){
        this.activity=activity;
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
        tvTime.setText(now.split(" ")[0]);
        customDatePicker.show(tvTime.getText().toString());
    }


    /**
     * 添加生产计划
     * @param addProductPlanP
     */
    public void addPlan(AddProductPlanP addProductPlanP){
        DialogUtil.showProgress(activity,"添加中");
        HttpMethod.addPlan(addProductPlanP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
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
