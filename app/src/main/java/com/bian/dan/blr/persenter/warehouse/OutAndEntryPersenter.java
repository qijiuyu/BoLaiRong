package com.bian.dan.blr.persenter.warehouse;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.warehouse.OutAndEntry_DetailsActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.UpdateProductP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

public class OutAndEntryPersenter {

    private OutAndEntry_DetailsActivity activity;

    public OutAndEntryPersenter(OutAndEntry_DetailsActivity activity){
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
//                    activity.getProductProgress();
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 确认入库弹框
     */
    private String status="完成";
    public void showConfirmEntry(){
        View view= LayoutInflater.from(activity).inflate(R.layout.dialog_confirm_entry,null);
        final PopupWindow popupWindow=DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        final Spinner spinner=view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.e(position+"++++++++++++");
                if(position==0){
                    status="完成";
                }else{
                    status="未完成";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
