package com.bian.dan.blr.persenter.warehouse;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.warehouse.Confirm_Procurement_EntryActivity;
import com.bian.dan.blr.adapter.warehouse.AddBatchNoAdapter;
import com.bian.dan.blr.view.CycleWheelView;
import com.zxdc.utils.library.bean.Dict;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.ProcurementDetails;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class Confirm_Procurement_Entry_Persenter {

    private Confirm_Procurement_EntryActivity activity;

    public Confirm_Procurement_Entry_Persenter(Confirm_Procurement_EntryActivity activity){
        this.activity=activity;
    }


    /**
     * 显示入库弹框
     * @param goodList
     */
    public void showDialog(ProcurementDetails.GoodList goodList){
        View view= LayoutInflater.from(activity).inflate(R.layout.dialog_confirm_procurement_entry,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        final TextView tvStock=view.findViewById(R.id.tv_stock);
        final TextView tvStockType=view.findViewById(R.id.tv_stockType);
        final RecyclerView listView=view.findViewById(R.id.listView);

        //初始化批次，数量的列表
        final AddBatchNoAdapter addBatchNoAdapter=new AddBatchNoAdapter(activity,null);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(manager);
        listView.setAdapter(addBatchNoAdapter);

        /**
         * 选择仓库
         */
        tvStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectType(tvStock);
            }
        });

        /**
         * 选择仓库类型
         */
        tvStockType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(tvStock.getText().toString())){
                    ToastUtil.showLong("请先选择仓库");
                    return;
                }
                getDict((int)tvStock.getTag(),tvStockType);
            }
        });


        /**
         * 提交
         */
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<addBatchNoAdapter.list.size();i++){
                    LogUtils.e(addBatchNoAdapter.list.get(i).getBatchNo()+"++++++++++++++++++"+addBatchNoAdapter.list.get(i).getNum());
                }
            }
        });
    }


    /**
     * 选择仓库
     */
    public void selectType(final TextView textView){
        View view= LayoutInflater.from(activity).inflate(R.layout.min_wheel_select,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            final CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            list.add("一楼仓库");
            list.add("二楼仓库");
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
                    textView.setText(wheel.getSelectLabel());
                    if(wheel.getSelection()==0){
                        textView.setTag(5);
                    }else{
                        textView.setTag(6);
                    }
                    popupWindow.dismiss();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 选择仓库类型
     */
    public void selectTypeList(final TextView textView, final List<Dict.DictBean> dictList){
        if(dictList==null || dictList.size()==0){
            ToastUtil.showLong("没有可选择的数据");
            return;
        }
        View view= LayoutInflater.from(activity).inflate(R.layout.wheel_select,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            for (int i=0;i<dictList.size();i++){
                list.add(dictList.get(i).getName());
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
                    textView.setText(label);
                    textView.setTag(dictList.get(position).getId());
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
     * 查询仓库列表
     * @param pid
     */
    public void getDict(final int pid, final TextView textView) {
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getDict(pid, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                Dict dict= (Dict) object;
                if(dict.isSussess()){
                    /**
                     * 显示仓库类型列表
                     */
                    selectTypeList(textView,dict.getList());
                }else{
                    ToastUtil.showLong(dict.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }

}
