package com.bian.dan.blr.persenter.warehouse;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.warehouse.SendDeliveryActivity;
import com.zxdc.utils.library.bean.AddBatchno;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.Util;

import java.util.ArrayList;
import java.util.List;

public class SendDeliveryPersenter {

    private SendDeliveryActivity activity;

    public SendDeliveryPersenter(SendDeliveryActivity activity){
        this.activity=activity;
    }


    /**
     * 显示增加批次的弹框
     */
    public void addDialog(final int id){
        View view= LayoutInflater.from(activity).inflate(R.layout.dialog_add_batchno,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        final EditText etBatchNo=view.findViewById(R.id.et_batchNo);
        final EditText etNum=view.findViewById(R.id.et_num);

//        /**
//         * 显示批次列表
//         */
//        final List<AddBatchno> list=activity.map.get(goodList.getId());
//        RecyclerView listView=view.findViewById(R.id.listView);
//        final AddBatchNoAdapter addBatchNoAdapter=new AddBatchNoAdapter(activity,list);
//        listView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false));
//        listView.setAdapter(addBatchNoAdapter);
//
//        /**
//         * 增加批次输入列表
//         */
//        TextView tvAdd=view.findViewById(R.id.tv_add);
//        tvAdd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
//        tvAdd.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if(list.size()==goodList.getNum()){
//                    return;
//                }
//                list.add(new AddBatchno());
//                addBatchNoAdapter.notifyDataSetChanged();
//            }
//        });


        /**
         * 提交
         */
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String batchNo=etBatchNo.getText().toString().trim();
                String num=etNum.getText().toString().trim();
                if(TextUtils.isEmpty(batchNo)){
                    ToastUtil.showLong("请输入批次");
                    return;
                }
                if(TextUtils.isEmpty(num)){
                    ToastUtil.showLong("请输入数量");
                    return;
                }
                AddBatchno addBatchno=new AddBatchno(batchNo,num);
                if(activity.map.get(id)==null){
                    List<AddBatchno> list=new ArrayList<>();
                    list.add(addBatchno);
                    activity.map.put(id,list);
                }else{
                    activity.map.get(id).add(addBatchno);
                }
                activity.sendDelieryGoodsAdpter.notifyDataSetChanged();

                popupWindow.dismiss();
                //隐藏键盘
                Util.lockKey(activity,etNum);
            }
        });
    }


    /**
     * 显示编辑批次的弹框
     */
    public void editDialog(final AddBatchno addBatchno) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_add_batchno, null);
        final PopupWindow popupWindow = DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        final EditText etBatchNo = view.findViewById(R.id.et_batchNo);
        final EditText etNum = view.findViewById(R.id.et_num);
        etBatchNo.setText(addBatchno.getBatchNo());
        etNum.setText(addBatchno.getNum());

        /**
         * 提交
         */
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String batchNo=etBatchNo.getText().toString().trim();
                String num=etNum.getText().toString().trim();
                if(TextUtils.isEmpty(batchNo)){
                    ToastUtil.showLong("请输入批次");
                    return;
                }
                if(TextUtils.isEmpty(num)){
                    ToastUtil.showLong("请输入数量");
                    return;
                }
                addBatchno.setBatchNo(batchNo);
                addBatchno.setNum(num);
                activity.sendDelieryGoodsAdpter.notifyDataSetChanged();

                popupWindow.dismiss();
                //隐藏键盘
                Util.lockKey(activity,etNum);
            }
        });
    }
}
