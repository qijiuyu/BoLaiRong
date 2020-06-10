package com.bian.dan.blr.persenter.product;

import com.bian.dan.blr.activity.main.production.ProductProgressDetailsActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.UpdateEntryGoodP;
import com.zxdc.utils.library.bean.parameter.UpdateProductP;
import com.zxdc.utils.library.bean.parameter.UpdateWasteP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class ProductProgressPersenter {

    private ProductProgressDetailsActivity activity;

    public ProductProgressPersenter(ProductProgressDetailsActivity activity){
        this.activity=activity;
    }


    /**
     * 修改出入库状态
     * @param updateProductP
     */
    public void updateProductStatus(UpdateProductP updateProductP){
        DialogUtil.showProgress(activity,"状态更新中");
        HttpMethod.updateProductStatus(updateProductP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    //重置详情页面
                    activity.getProductProgress();
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 更新单条入库明细
     */
    public void updateEntryGood(UpdateEntryGoodP updateEntryGoodP){
        DialogUtil.showProgress(activity,"更新中");
        HttpMethod.updateEntryGood(updateEntryGoodP, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    //重置详情页面
                    activity.getProductProgress();
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 更新余废料单条记录
     */
    public void updateWaste(UpdateWasteP updateWasteP){
        HttpMethod.updateWaste(updateWasteP, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    //重置详情页面
                    activity.getProductProgress();
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
