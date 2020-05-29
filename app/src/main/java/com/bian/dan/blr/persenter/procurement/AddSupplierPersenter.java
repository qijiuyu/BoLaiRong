package com.bian.dan.blr.persenter.procurement;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.procurement.AddSupplierActivity;
import com.bian.dan.blr.view.CycleWheelView;
import com.google.gson.Gson;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Dict;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.AddSupplierMaterialP;
import com.zxdc.utils.library.bean.parameter.AddSupplierP;
import com.zxdc.utils.library.bean.parameter.EditSupplierGoodsP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class AddSupplierPersenter {

    private AddSupplierActivity activity;
    //所属行业集合
    private List<Dict.DictBean> industryList=new ArrayList<>();

    public AddSupplierPersenter(AddSupplierActivity activity){
        this.activity=activity;
    }


    /**
     * 获取所属行业
     */
    public void getDict(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getDict(11, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                Dict dict= (Dict) object;
                if(dict.isSussess()){
                    industryList.addAll(dict.getList());
                    //展示下拉选择框
                    selectIndustry(textView);
                }else{
                    ToastUtil.showLong(dict.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 所属行业下拉框
     */
    private TextView textView;
    public void selectIndustry(final TextView textView){
        this.textView=textView;
        if(industryList.size()==0){
            //获取所属行业数据
            getDict();
            return;
        }
        View view= LayoutInflater.from(activity).inflate(R.layout.wheel_select,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            for (int i=0;i<industryList.size();i++){
                list.add(industryList.get(i).getName());
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
                    textView.setText(industryList.get(position).getName());
                    textView.setTag(industryList.get(position).getId());
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
     * 校验供应商名称唯一性
     */
    public void checkSupplierName(final AddSupplierP addSupplierP){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.checkSupplierName(addSupplierP.getSupplierName(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    if(addSupplierP.getId()==0){
                        //新增供应商
                        addSupplier(addSupplierP);
                    }else{
                        //编辑供应商
                        UpdateSupplier(addSupplierP);
                    }
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }



    /**
     * 新增供应商
     */
    public void addSupplier(AddSupplierP addSupplierP){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.addSupplier(addSupplierP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    activity.setResult(1000,new Intent());
                    activity.finish();
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 编辑供应商基本信息
     */
    public void UpdateSupplier(final AddSupplierP addSupplierP){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.UpdateSupplier(addSupplierP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    if(addSupplierP.getSupplierDetailList().size()>0){
                        //新增供应商物料明细
                        AddSupplierMaterial(addSupplierP);
                    }else{
                        activity.setResult(1000,new Intent());
                        activity.finish();
                    }
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 修改明细物料单价
     */
    public void editSupplierPrice(final Goods goods){
        EditSupplierGoodsP editSupplierGoodsP=new EditSupplierGoodsP();
        editSupplierGoodsP.setId(goods.getId());
        editSupplierGoodsP.setProp1(goods.getPrice());
        LogUtils.e("+++++++++"+new Gson().toJson(editSupplierGoodsP));

        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.editSupplierPrice(editSupplierGoodsP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    //编辑供应物料成功
                    activity.editSuccess(goods);
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 删除供应商明细
     */
    public void deleteSupplierGoods(final Goods goods){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.deleteSupplierGoods(goods.getId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    //删除供应物料成功
                    activity.deleteSuccess(goods);
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }



    /**
     * 新增供应商物料明细
     */
    public void AddSupplierMaterial(AddSupplierP addSupplierP){
        AddSupplierMaterialP addSupplierMaterialP=new AddSupplierMaterialP();
        addSupplierMaterialP.setId(addSupplierP.getId());
        addSupplierMaterialP.setSupplierDetailList(addSupplierP.getSupplierDetailList());
        LogUtils.e("++++++++++++++"+new Gson().toJson(addSupplierMaterialP));

        HttpMethod.AddSupplierMaterial(addSupplierMaterialP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    activity.setResult(1000,new Intent());
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
