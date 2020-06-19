package com.bian.dan.blr.persenter.product;

import android.widget.TextView;

import com.bian.dan.blr.activity.main.production.AddStorageProductActivity;
import com.bian.dan.blr.view.stocktype.SelectStockDialog;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.StockList;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class AddStoragePersenter {

    private AddStorageProductActivity activity;

    public AddStoragePersenter(AddStorageProductActivity activity){
        this.activity=activity;
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
