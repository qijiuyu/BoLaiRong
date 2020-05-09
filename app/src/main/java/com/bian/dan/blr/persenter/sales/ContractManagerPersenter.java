package com.bian.dan.blr.persenter.sales;

import android.widget.TextView;

import com.bian.dan.blr.activity.main.sales.ContractManagerActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;

public class ContractManagerPersenter {

    public ContractManagerActivity activity;
    public ContractManagerPersenter(ContractManagerActivity activity){
        this.activity=activity;
    }


    /**
     * 获取合同列表
     */
    public void getContractList(TextView tvCode, TextView tvName, int page){
        HttpMethod.getContractList(tvCode.getText().toString().trim(), (String) tvName.getTag(), String.valueOf(page), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                activity.refresh(object);
            }
            public void onFail(Throwable t) {

            }
        });
    }

}
