package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.warehouse.SendDeliveryGoodsAdapter;
import com.bian.dan.blr.persenter.warehouse.SendDeliveryPersenter;
import com.google.gson.Gson;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.AddBatchno;
import com.zxdc.utils.library.bean.OutBoundDetails;
import com.zxdc.utils.library.bean.parameter.SalesOutBoundP;
import com.zxdc.utils.library.util.LogUtils;
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
                sendDeliveryPersenter.selectLogistics(tvName);
                break;
            case R.id.tv_submit:
                 String name=tvName.getText().toString().trim();
                 String code=etCode.getText().toString().trim();
                 if(TextUtils.isEmpty(name)){
                     ToastUtil.showLong("请选择物流");
                     return;
                 }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showLong("请输入物流单号");
                    return;
                }
                SalesOutBoundP salesOutBoundP=new SalesOutBoundP();
                salesOutBoundP.setId(outBoundDetails.getOutOrder().getId());
                salesOutBoundP.setExpressType((int)tvName.getTag());
                salesOutBoundP.setExpressNo(code);
                salesOutBoundP.setStatus(1);

                List<SalesOutBoundP.GoodList> goodList=new ArrayList<>();
                for(Integer id:map.keySet()){
                     List<AddBatchno> list=map.get(id);
                     for (int i=0;i<list.size();i++){
                         SalesOutBoundP.GoodList good=new SalesOutBoundP.GoodList();
                         good.setBatchNo(list.get(i).getBatchNo());
                         good.setGoodsId(list.get(i).getGoodsId());
                         good.setNum(Integer.parseInt(list.get(i).getNum()));
                         good.setStockType(list.get(i).getStockType());
                         goodList.add(good);
                     }
                }
                salesOutBoundP.setSaleDetailList(goodList);
                LogUtils.e("+++++++++++++"+new Gson().toJson(salesOutBoundP));
                sendDeliveryPersenter.updateOutOrder(salesOutBoundP);
                break;
            default:
                break;
        }
    }




}
