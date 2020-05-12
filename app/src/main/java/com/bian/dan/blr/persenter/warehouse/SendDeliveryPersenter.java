package com.bian.dan.blr.persenter.warehouse;

import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.warehouse.SendDeliveryActivity;
import com.bian.dan.blr.adapter.warehouse.AddBatchNoAdapter;
import com.zxdc.utils.library.bean.AddBatchno;
import com.zxdc.utils.library.bean.OutBoundDetails;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;

import java.util.List;

public class SendDeliveryPersenter {

    private SendDeliveryActivity activity;

    public SendDeliveryPersenter(SendDeliveryActivity activity){
        this.activity=activity;
    }


    /**
     * 显示增加批次的弹框
     */
    public void addDialog(final OutBoundDetails.GoodList goodList){
        View view= LayoutInflater.from(activity).inflate(R.layout.dialog_add_batchno,null);
        PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);

        /**
         * 显示批次列表
         */
        final List<AddBatchno> list=activity.map.get(goodList.getId());
        RecyclerView listView=view.findViewById(R.id.listView);
        final AddBatchNoAdapter addBatchNoAdapter=new AddBatchNoAdapter(activity,list);
        listView.setLayoutManager(new LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL,false));
        listView.setAdapter(addBatchNoAdapter);

        /**
         * 增加批次输入列表
         */
        TextView tvAdd=view.findViewById(R.id.tv_add);
        tvAdd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(list.size()==goodList.getNum()){
                    return;
                }
                list.add(new AddBatchno());
                addBatchNoAdapter.notifyDataSetChanged();
            }
        });


        /**
         * 提交
         */
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i=0;i<list.size();i++){
                    LogUtils.e(list.get(i).getBatchNo()+"+++++++++++++++"+list.get(i).getNum());
                }
            }
        });
    }
}
