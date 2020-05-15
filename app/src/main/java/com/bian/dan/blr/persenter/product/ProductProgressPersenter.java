package com.bian.dan.blr.persenter.product;

import com.bian.dan.blr.activity.main.production.ProductProgressDetailsActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.UpdateProductP;
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
}
