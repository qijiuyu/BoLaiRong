package com.bian.dan.blr.persenter.warehouse;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.warehouse.SendDeliveryActivity;
import com.bian.dan.blr.view.CycleWheelView;
import com.zxdc.utils.library.bean.AddBatchno;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Dict;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.OutBoundDetails;
import com.zxdc.utils.library.bean.parameter.SalesOutBoundP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class SendDeliveryPersenter {

    private SendDeliveryActivity activity;
    /**
     * 物流集合
     */
    private List<Dict.DictBean> logisticsList=new ArrayList<>();

    public SendDeliveryPersenter(SendDeliveryActivity activity){
        this.activity=activity;
    }


    /**
     * 显示增加批次的弹框
     */
    public void addDialog(final OutBoundDetails.GoodList goodList){
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
                AddBatchno addBatchno=new AddBatchno(goodList.getGoodsId(),1,batchNo,num);
                if(activity.map.get(goodList.getId())==null){
                    List<AddBatchno> list=new ArrayList<>();
                    list.add(addBatchno);
                    activity.map.put(goodList.getId(),list);
                }else{
                    activity.map.get(goodList.getId()).add(addBatchno);
                }
                activity.sendDelieryGoodsAdpter.notifyDataSetChanged();

                popupWindow.dismiss();
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
            }
        });
    }



    /**
     * 获取物流数据
     */
    public void getDict(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getDict(10, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                Dict dict= (Dict) object;
                if(dict.isSussess()){
                    logisticsList.addAll(dict.getList());
                    //选择物流
                    selectLogistics(textView);
                }else{
                    ToastUtil.showLong(dict.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 选择物流
     */
    private TextView textView;
    public void selectLogistics(final TextView textView){
        this.textView=textView;
        if(logisticsList.size()==0){
            //获取物流数据
            getDict();
            return;
        }
        View view= LayoutInflater.from(activity).inflate(R.layout.wheel_select,null);
        final PopupWindow popupWindow= DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            for (int i=0;i<logisticsList.size();i++){
                list.add(logisticsList.get(i).getName());
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
                    textView.setText(label);
                    textView.setTag(logisticsList.get(position).getId());
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
     * 出库单-修改
     */
    public void updateOutOrder(SalesOutBoundP salesOutBoundP){
        DialogUtil.showProgress(activity,"提交中");
        HttpMethod.updateOutOrder(salesOutBoundP, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    activity.setResult(1000,new Intent());
                    activity.finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
