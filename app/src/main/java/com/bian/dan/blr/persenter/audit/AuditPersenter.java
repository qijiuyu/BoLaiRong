package com.bian.dan.blr.persenter.audit;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class AuditPersenter {

    private Activity activity;

    public AuditPersenter(Activity activity){
        this.activity=activity;
    }


    /**
     * 显示驳回弹框
     */
    public void showAuditDialog(){
        View view= LayoutInflater.from(activity).inflate(R.layout.dialog_audit,null);
        PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        final EditText etRemark=view.findViewById(R.id.et_remark);
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String remark=etRemark.getText().toString().trim();
                if(TextUtils.isEmpty(remark)){
                    ToastUtil.showLong("请输入驳回备注");
                    return;
                }
            }
        });
    }
}
