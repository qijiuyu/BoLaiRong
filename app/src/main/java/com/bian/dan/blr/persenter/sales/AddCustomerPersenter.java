package com.bian.dan.blr.persenter.sales;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.AddCustomerActivity;
import com.bian.dan.blr.view.CycleWheelView;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Dict;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.AddCustomerP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerPersenter {

    private AddCustomerActivity activity;
    private TextView textView;
    //所属行业集合
    private List<Dict.DictBean> industryList=new ArrayList<>();
    //客户状态集合
    private List<Dict.DictBean> statusList=new ArrayList<>();
    //开户行集合
    private List<Dict.DictBean> bankList=new ArrayList<>();
    /**
     * 11：所属行业
     * 3：客户状态
     * 4：开户行
     * 5：私有状态
     */
    private int type;

    public AddCustomerPersenter(AddCustomerActivity activity){
        this.activity=activity;
    }


    /**
     * 下拉选择
     * @param textView
     */
    public void selectText(final TextView textView,final int type){
        this.textView=textView;
        this.type=type;
        if(type==11 && industryList.size()==0){
            //获取所属行业数据
            getDict(type);
            return;
        }
        if(type==3 && statusList.size()==0){
            //获取客户状态数据
            getDict(type);
            return;
        }
        if(type==4 && bankList.size()==0){
            //获取开户行数据
            getDict(type);
            return;
        }
        View view= LayoutInflater.from(activity).inflate(R.layout.wheel_select,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            if(type==11){
                for (int i=0;i<industryList.size();i++){
                     list.add(industryList.get(i).getName());
                }
            }else if(type==3){
                for (int i=0;i<statusList.size();i++){
                    list.add(statusList.get(i).getName());
                }
            }else if(type==4){
                for (int i=0;i<bankList.size();i++){
                    list.add(bankList.get(i).getName());
                }
            }else{
                list.add("私有");
                list.add("公有");
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
                    if(type==11){
                        textView.setText(industryList.get(position).getName());
                        textView.setTag(industryList.get(position).getId());
                    }else if(type==3){
                        textView.setText(statusList.get(position).getName());
                        textView.setTag(statusList.get(position).getId());
                    }else if(type==4){
                        textView.setText(bankList.get(position).getName());
                        textView.setTag(bankList.get(position).getId());
                    }else{
                        textView.setText(label);
                    }
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
     * 获取字典数据
     */
    public void getDict(int pid){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getDict(pid, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                Dict dict= (Dict) object;
                if(dict.isSussess()){
                    if(type==11){
                        industryList.addAll(dict.getList());
                    }else if(type==3){
                        statusList.addAll(dict.getList());
                    }else{
                        bankList.addAll(dict.getList());
                    }
                    //展示下拉选择框
                    selectText(textView,type);
                }else{
                    ToastUtil.showLong(dict.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 增加客户
     */
    public void addCustomer(AddCustomerP addCustomerP){
        DialogUtil.showProgress(activity,"上传中");
        HttpMethod.addCustomer(addCustomerP, new NetWorkCallBack() {
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
