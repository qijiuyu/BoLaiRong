package com.bian.dan.blr.activity.main.warehouse;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.warehouse.SendDeliveryGoodsAdapter;
import com.bian.dan.blr.persenter.warehouse.SendDeliveryPersenter;
import com.bian.dan.blr.view.CycleWheelView;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.AddBatchno;
import com.zxdc.utils.library.bean.Dict;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.OutBoundDetails;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 出库发货
 */
public class SendDeliveryActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.listView)
    MeasureListView listView;
    private OutBoundDetails outBoundDetails;
    public SendDeliveryGoodsAdapter sendDelieryGoodsAdpter;
    /**
     * 存储各个商品下的批次
     */
    public Map<Integer, List<AddBatchno>> map=new HashMap<>();
    /**
     * 物流集合
     */
    private List<Dict.DictBean> logisticsList=new ArrayList<>();
    public SendDeliveryPersenter sendDeliveryPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_delivery);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("出库发货");
        sendDeliveryPersenter=new SendDeliveryPersenter(this);
        outBoundDetails= (OutBoundDetails) getIntent().getSerializableExtra("outBoundDetails");
        if(outBoundDetails!=null){
            //显示商品列表
            sendDelieryGoodsAdpter=new SendDeliveryGoodsAdapter(this,outBoundDetails.getGoodsList());
            listView.setAdapter(sendDelieryGoodsAdpter);
        }
    }

    @OnClick({R.id.lin_back, R.id.tv_name, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择物流
            case R.id.tv_name:
                selectLogistics();
                break;
            case R.id.tv_submit:
                break;
            default:
                break;
        }
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
                    selectLogistics();
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
    public void selectLogistics(){
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
                    tvName.setText(label);
                    tvName.setTag(logisticsList.get(position).getId());
                }
            });
            view.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    popupWindow.dismiss();
                    tvName.setText(null);
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

}
