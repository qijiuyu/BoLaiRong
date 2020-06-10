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
import com.zxdc.utils.library.bean.ProcurementDetails;
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
    //产品列表集合
    private List<Goods> goodsList=new ArrayList<>();
    //详情对象
    private ProcurementDetails.DetailsBean detailsBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_procurement);
        ButterKnife.bind(this);
        initView();
        //进入编辑页面，显示数据
        showEditData();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增采购单");
        addProcurementPersenter = new AddProcurementPersenter(this);
        detailsBean= (ProcurementDetails.DetailsBean) getIntent().getSerializableExtra("detailsBean");

        //产品列表
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
                if(detailsBean==null){
                    //新增采购单
                    addProcurementPersenter.AddProcurement(addProcurementP);
                }else{
                    //修改采购单
                    addProcurementP.setId(detailsBean.getId());
                    addProcurementP.setFlag("1");
                    addProcurementPersenter.EditProcurement(addProcurementP);
                }
                LogUtils.e("+++++++++++"+new Gson().toJson(addProcurementP));
                break;
            default:
                break;
        }
    }


    /**
     * 进入编辑页面，显示数据
     */
    private void showEditData() {
        if (detailsBean == null) {
            return;
        }
        tvTime.setText(detailsBean.getPurcDate().split(" ")[0]);
        //遍历采购物料列表
        for (int i=0;i<detailsBean.getPurchaseDetailList().size();i++) {
             ProcurementDetails.GoodList goodList=detailsBean.getPurchaseDetailList().get(i);
             Goods goods = new Goods();
             goods.setId(goodList.getId());
             goods.setGoodId(goodList.getGoodsId());
             goods.setName(goodList.getGoodsName());
             goods.setSpec(goodList.getSpec());
             goods.setUnitStr(goodList.getUnitStr());
             goods.setTypeStr(goodList.getTypeStr());
             goods.setNum(goodList.getNum());
             goods.setPrice(goodList.getUnitPrice()+"");
             goods.setTotalMoney(goodList.getAmount()+"");
             goods.setMemo(goodList.getMemo());
             goods.setAddress(goodList.getAddress());
             goods.setPayType(goodList.getPayType());
             goods.setPayTime(goodList.getPayDate());
             goods.setCompanyId(goodList.getSupplierId());
             goods.setCompany(goodList.getSupplierName());
             goods.setContract(goodList.getContacts());
             goods.setMobile(goodList.getPhone());
             goodsList.add(goods);
        }
        addProductAdapter3.notifyDataSetChanged();
        //计算总数量，总金额
        getTotal();
    }


    /**
     * 进入编辑页面
     * @param goods:要编辑的对象
     */
    private Goods editGoods;
    public void gotoEdit(Goods goods){
        this.editGoods=goods;
        Intent intent=new Intent(this,AddProductActivity3.class);
        intent.putExtra("goods",editGoods);
        startActivityForResult(intent,200);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        Goods goods=(Goods) data.getSerializableExtra("goods");
        if(goods==null){
            return;
        }
        //新增、编辑回执
        if(resultCode==200){
            if(editGoods==null){
                boolean isThe=false;
                for (int i=0;i<goodsList.size();i++){
                    if(goodsList.get(i).getGoodId()==goods.getGoodId()){
                        isThe=true;
                        break;
                    }
                }
                if(isThe){
                    ToastUtil.showLong("不能重复添加物料");
                }else{
                    goodsList.add(goods);
                }
            }else{
                for (int i=0;i<goodsList.size();i++){
                    if(editGoods.equals(goodsList.get(i))){
                        goodsList.set(i,goods);
                        break;
                    }
                }
            }
        }
        editGoods=null;
        //刷新列表
        addProductAdapter3.notifyDataSetChanged();

        /**
         * 计算总数量，总金额
         */
        getTotal();
    }


    /**
     * 计算总数量，总金额
     */
    private void getTotal(){
        if(goodsList.size()>0){
            linGood.setVisibility(View.VISIBLE);
        }
        int totalNum=0;
        double totalMoney=0;
        for (int i=0;i<goodsList.size();i++){
            totalNum=totalNum+goodsList.get(i).getNum();
            totalMoney= BigDecimalUtil.add(totalMoney,Double.parseDouble(goodsList.get(i).getTotalMoney()));
        }
        tvProductNum.setText("数量："+totalNum);
        tvProductMoney.setText(Html.fromHtml("金额：<font color=\"#FF4B4C\">" + totalMoney + "元</font>"));
    }
}
