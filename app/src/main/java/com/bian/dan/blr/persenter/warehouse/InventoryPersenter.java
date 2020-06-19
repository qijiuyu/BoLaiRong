package com.bian.dan.blr.persenter.warehouse;

import android.widget.TextView;

import com.bian.dan.blr.activity.main.warehouse.InventoryDetailsListActivity;
import com.bian.dan.blr.view.stocktype.SelectChildDialog;
import com.bian.dan.blr.view.stocktype.SelectParentDialog;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.StockList;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class InventoryPersenter {

    private InventoryDetailsListActivity activity;

    //仓库列表
    private List<StockList.ListBean> stocks=new ArrayList<>();

    public InventoryPersenter(InventoryDetailsListActivity activity){
        this.activity=activity;
    }


    /**
     * 获取仓库树状列表
     */
    public void getStockList(final TextView textView){
        if(stocks.size()>0){
            SelectParentDialog selectParentDialog=new SelectParentDialog(activity,stocks,textView);
            selectParentDialog.show();
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


    /**
     * 显示子类仓库弹框
     * @param textView
     * @param parentId
     */
    public void showChildStock(final TextView textView,final int parentId){
        SelectChildDialog selectChildDialog=new SelectChildDialog(activity,stocks,textView,parentId);
        selectChildDialog.show();
    }
}
