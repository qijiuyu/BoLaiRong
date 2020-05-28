package com.bian.dan.blr.activity.main.procurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.procurement.AddProductAdapter3;
import com.bian.dan.blr.persenter.procurement.AddProcurementPersenter;
import com.google.gson.Gson;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.parameter.AddProcurementP;
import com.zxdc.utils.library.util.BigDecimalUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增采购单
 */
public class AddProcurementActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.tv_product_money)
    TextView tvProductMoney;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    @BindView(R.id.lin_good)
    LinearLayout linGood;
    private AddProcurementPersenter addProcurementPersenter;
    private AddProductAdapter3 addProductAdapter3;
    private List<Goods> goodsList=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_procurement);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增采购单");
        addProcurementPersenter = new AddProcurementPersenter(this);
        addProductAdapter3=new AddProductAdapter3(this,goodsList);
        listView.setAdapter(addProductAdapter3);
    }

    @OnClick({R.id.lin_back, R.id.tv_time, R.id.tv_product, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //采购日期
            case R.id.tv_time:
                addProcurementPersenter.selectTime(tvTime);
                break;
            //采购列表
            case R.id.tv_product:
                setClass(AddProductActivity3.class, 200);
                break;
            //提交
            case R.id.tv_submit:
                String time=tvTime.getText().toString().trim();
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择采购日期");
                    return;
                }
                if(goodsList.size()==0){
                    ToastUtil.showLong("请添加采购列表");
                    return;
                }
                AddProcurementP addProcurementP=new AddProcurementP();
                addProcurementP.setPurcDate(time);
                List<AddProcurementP.GoodsList> list=new ArrayList<>();
                for (int i=0;i<goodsList.size();i++){
                     Goods goods=goodsList.get(i);
                     AddProcurementP.GoodsList goodsList=new AddProcurementP.GoodsList();
                     goodsList.setSupplierId(goods.getCompanyId());
                     goodsList.setGoodsId(goods.getGoodId());
                     goodsList.setNum(goods.getNum());
                     goodsList.setUnitPrice(Double.parseDouble(goods.getPrice()));
                     goodsList.setAmount(Double.parseDouble(goods.getTotalMoney()));
                     goodsList.setPayType(goods.getPayType());
                     goodsList.setPayDate(goods.getPayTime());
                     goodsList.setMemo(goods.getMemo());
                    list.add(goodsList);
                }
                addProcurementP.setPurchaseDetailList(list);
                LogUtils.e("+++++++++++"+new Gson().toJson(addProcurementP));
                //新增采购单
                addProcurementPersenter.AddProcurement(addProcurementP);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==200 && data!=null){
            Goods goods= (Goods) data.getSerializableExtra("goods");
            if(goods!=null){
                goodsList.add(goods);
                addProductAdapter3.notifyDataSetChanged();
                if(goodsList.size()>0){
                    linGood.setVisibility(View.VISIBLE);
                }
            }

            /**
             * 计算总数量，总金额
             */
            int totalNum=0;
            double totalMoney=0;
            for (int i=0;i<goodsList.size();i++){
                totalNum=totalNum+goodsList.get(i).getNum();
                totalMoney= BigDecimalUtil.add(totalMoney,Double.parseDouble(goodsList.get(i).getTotalMoney()));
            }
            tvProductNum.setText("数量："+totalNum);
            tvProductMoney.setText(Html.fromHtml("金额：<font color=\"#FF4B4C\">" + totalMoney + "</font>"));
        }
    }
}
