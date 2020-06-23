package com.bian.dan.blr.persenter.sales;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.AddOutBoundActivity;
import com.bian.dan.blr.view.CycleWheelView;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.OutBoundP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddOutBoundPersenter {

    private AddOutBoundActivity activity;

    public AddOutBoundPersenter(AddOutBoundActivity activity){
        this.activity=activity;
    }


    /**
     * 选择付款方式
     */
    public void selectText(final TextView textView){
        View view= LayoutInflater.from(activity).inflate(R.layout.min_wheel_select,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            list.add("全款");
            list.add("分期");
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
     * 增加出库单
     */
    public void addOutBound(OutBoundP outBoundP){
        DialogUtil.showProgress(activity,"上传中");
        HttpMethod.addOutBound(outBoundP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    Intent intent=new Intent();
                    activity.setResult(1000,intent);
                    activity.finish();
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
