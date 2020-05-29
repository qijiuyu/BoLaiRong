package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.AddProductAdapter2;
import com.bian.dan.blr.persenter.sales.AddProductPlanPersenter;
import com.google.gson.Gson;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.ContractCode;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.parameter.AddGoodP;
import com.zxdc.utils.library.bean.parameter.AddProductPlanP;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加生产计划
 */
public class AddProductPlanActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_remark)
    EditText etRemark;
    private AddProductAdapter2 addProductAdapter2;
    //产品列表集合
    private List<Goods> goodsList=new ArrayList<>();
    private AddProductPlanPersenter addProductPlanPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_plan);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增生产计划");
        addProductPlanPersenter=new AddProductPlanPersenter(this);
        addProductAdapter2=new AddProductAdapter2(this,goodsList);
        listView.setAdapter(addProductAdapter2);
    }

    @OnClick({R.id.lin_back,R.id.tv_code, R.id.tv_name, R.id.tv_time, R.id.tv_submit})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //选择合同编号
            case R.id.tv_code:
                 intent.setClass(this,SelectContractCodeActivity.class);
                 startActivityForResult(intent,300);
                 break;
            //选择生产列表
            case R.id.tv_name:
                intent.setClass(this,AddProductActivity2.class);
                startActivityForResult(intent,200);
                break;
            //选择交付日期
            case R.id.tv_time:
                addProductPlanPersenter.selectTime(tvTime);
                break;
            case R.id.tv_submit:
                 String code=tvCode.getText().toString().trim();
                 String time=tvTime.getText().toString().trim();
                 String remark=etRemark.getText().toString().trim();
                 if(TextUtils.isEmpty(code)){
                     ToastUtil.showLong("请选择合同编号");
                     return;
                 }
                 if(goodsList.size()==0){
                     ToastUtil.showLong("请添加产品列表");
                     return;
                 }
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择交付日期");
                    return;
                }
                AddProductPlanP addProductPlanP=new AddProductPlanP();
                addProductPlanP.setProp2(code);
                addProductPlanP.setDeliveryDate(time);
                addProductPlanP.setMemo(remark);
                List<AddGoodP> list=new ArrayList<>();
                for (int i=0;i<goodsList.size();i++){
                     AddGoodP addGoodP=new AddGoodP();
                     addGoodP.setGoodsId(goodsList.get(i).getGoodId());
                     addGoodP.setNum(goodsList.get(i).getNum());
                     addGoodP.setMemo(goodsList.get(i).getMemo());
                     list.add(addGoodP);
                }
                addProductPlanP.setDetailList(list);
                //添加生产计划
                LogUtils.e("+++++++++++++"+new Gson().toJson(addProductPlanP));
                addProductPlanPersenter.addPlan(addProductPlanP);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            //返回商品列表
            case 200:
                 if(data!=null){
                     Goods goods= (Goods) data.getSerializableExtra("goods");
                     if(goods!=null){
                         goodsList.add(goods);
                         addProductAdapter2.notifyDataSetChanged();
                     }
                 }
                 break;
            //回执合同编号
            case 300:
                if(data!=null){
                    ContractCode.ListBean listBean= (ContractCode.ListBean) data.getSerializableExtra("listBean");
                    tvCode.setText(listBean.getProp2());
                }
                break;
            default:
                break;
        }
    }
}
