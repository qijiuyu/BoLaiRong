package com.bian.dan.blr.persenter.warehouse;

import android.content.Intent;
import android.widget.TextView;

import com.bian.dan.blr.activity.main.warehouse.AddSdEnterActivity;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.AddSdEnterP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddSdEnterPersenter {

    private AddSdEnterActivity activity;

    public AddSdEnterPersenter(AddSdEnterActivity activity){
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
     * 添加手动入库单
     */
    public void addSdEnter(AddSdEnterP addSdEnterP){
        DialogUtil.showProgress(activity,"数据上传中");
        HttpMethod.addSdEnter(addSdEnterP, new NetWorkCallBack() {
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
