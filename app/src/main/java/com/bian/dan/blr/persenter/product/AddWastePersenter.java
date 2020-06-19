package com.bian.dan.blr.persenter.product;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.production.AddWasteActivity;
import com.bian.dan.blr.view.CycleWheelView;
import com.bian.dan.blr.view.stocktype.SelectStockDialog;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.StockList;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class AddWastePersenter {

    private AddWasteActivity activity;

    public AddWastePersenter(AddWasteActivity activity){
        this.activity=activity;
    }


    /**
     * 选择类别
     */
    public void selectType(final TextView textView){
        View view= LayoutInflater.from(activity).inflate(R.layout.min_wheel_select,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            final CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            list.add("余料");
            list.add("废料");
            list.add("损耗");
            wheel.setLabels(list);
            wheel.setSelection(0);
            wheel.setWheelSize(3);
            wheel.setCycleEnable(false);
            wheel.setAlphaGradual(0.5f);
            wheel.setDivider(Color.parseColor("#abcdef"),1);
            wheel.setSolid(Color.WHITE,Color.WHITE);
            wheel.setLabelColor(Color.GRAY);
            wheel.setLabelSelectColor(Color.BLACK);

            view.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    popupWindow.dismiss();
                    textView.setText(null);
                }
            });
            view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    popupWindow.dismiss();
                    textView.setText(wheel.getSelectLabel());
                    textView.setTag(wheel.getSelection()+1);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 获取仓库树状列表
     */
    private List<StockList.ListBean> stocks=new ArrayList<>();
    public void getStockList(final TextView textView){
        if(stocks.size()>0){
            SelectStockDialog selectStockDialog=new SelectStockDialog(activity,stocks,textView);
            selectStockDialog.show();
            return;
        }
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getStockList(new NetWorkCallBack() {
            public void onSuccess(Object object) {
                StockList stockList= (StockList) object;
                if(stockList.isSussess()){
                    stocks.addAll(stockList.getPage());
                    getStockList(textView);
                }else{
                    ToastUtil.showLong(stockList.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
