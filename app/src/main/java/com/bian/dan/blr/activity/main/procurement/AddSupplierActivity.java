package com.bian.dan.blr.activity.main.procurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.procurement.AddProductAdapter5;
import com.bian.dan.blr.persenter.procurement.AddSupplierPersenter;
import com.google.gson.Gson;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.SupplierDetails;
import com.zxdc.utils.library.bean.parameter.AddSupplierP;
import com.zxdc.utils.library.bean.parameter.EditSupplierGoodsP;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSupplierActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_industry)
    TextView tvIndustry;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_material)
    TextView tvMaterial;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.et_memo)
    EditText etMemo;
    private AddSupplierPersenter addSupplierPersenter;
    private AddProductAdapter5 addProductAdapter5;
    //产品集合
    private List<Goods> goodsList=new ArrayList<>();
    //详情对象
    private SupplierDetails.DetailsBean detailsBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        ButterKnife.bind(this);
        initView();
        //进入编辑页面，显示数据
        showEditData();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增供应商");
        addSupplierPersenter=new AddSupplierPersenter(this);
        detailsBean= (SupplierDetails.DetailsBean) getIntent().getSerializableExtra("detailsBean");

        //产品列表
        addProductAdapter5=new AddProductAdapter5(this,goodsList,addSupplierPersenter);
        listView.setAdapter(addProductAdapter5);
    }


    @OnClick({R.id.lin_back, R.id.tv_industry, R.id.tv_material, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //选择所属行业
            case R.id.tv_industry:
                addSupplierPersenter.selectIndustry(tvIndustry);
                break;
            //选择供应物料
            case R.id.tv_material:
                setClass(AddProductActivity5.class,200);
                break;
            case R.id.tv_submit:
                String supplierName=etName.getText().toString().trim();
                String industry=tvIndustry.getText().toString().trim();
                String contact=etContact.getText().toString().trim();
                String phone=etMobile.getText().toString().trim();
                String address=etAddress.getText().toString().trim();
                String memo=etMemo.getText().toString().trim();
                if(TextUtils.isEmpty(supplierName)){
                    ToastUtil.showLong("请输入供应商名称");
                    return;
                }
                if(TextUtils.isEmpty(industry)){
                    ToastUtil.showLong("请选择所属行业");
                    return;
                }
                if(TextUtils.isEmpty(contact)){
                    ToastUtil.showLong("请输入联系人");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    ToastUtil.showLong("请输入电话");
                    return;
                }
                if(TextUtils.isEmpty(address)){
                    ToastUtil.showLong("请输入地址");
                    return;
                }
                AddSupplierP addSupplierP=new AddSupplierP();
                addSupplierP.setSupplierName(supplierName);
                addSupplierP.setIndustry((int)tvIndustry.getTag());
                addSupplierP.setContacts(contact);
                addSupplierP.setPhone(phone);
                addSupplierP.setSupplierAddress(address);
                addSupplierP.setMemo(memo);
                List<AddSupplierP.GoodList> list=new ArrayList<>();
                for (int i=0;i<goodsList.size();i++){
                     AddSupplierP.GoodList good=new AddSupplierP.GoodList(goodsList.get(i).getGoodId(),goodsList.get(i).getMemo(),goodsList.get(i).getPrice());
                     list.add(good);
                }
                addSupplierP.setSupplierDetailList(list);
                LogUtils.e("+++++++++"+new Gson().toJson(addSupplierP));
                addSupplierPersenter.checkSupplierName(addSupplierP);
                break;
            default:
                break;
        }
    }


    /**
     * 进入编辑页面，显示数据
     */
    private void showEditData(){
        if(detailsBean==null){
            return;
        }
        etName.setText(detailsBean.getSupplierName());
        tvIndustry.setText(detailsBean.getIndustryStr());
        tvIndustry.setTag(detailsBean.getIndustry());
        etContact.setText(detailsBean.getContacts());
        etMobile.setText(detailsBean.getPhone());
        etAddress.setText(detailsBean.getSupplierAddress());
        etMemo.setText(detailsBean.getMemo());

        //遍历物料列表
        for (int i=0;i<detailsBean.getSupplierDetailList().size();i++){
              Goods goods=new Goods();
              goods.setId(detailsBean.getSupplierDetailList().get(i).getId());
              goods.setGoodId(detailsBean.getSupplierDetailList().get(i).getGoodsId());
              goods.setName(detailsBean.getSupplierDetailList().get(i).getGoodsName());
              goods.setBrand(detailsBean.getSupplierDetailList().get(i).getBrand());
              goods.setSpec(detailsBean.getSupplierDetailList().get(i).getSpec());
              goods.setUnitStr(detailsBean.getSupplierDetailList().get(i).getUnitStr());
              goods.setPrice(detailsBean.getSupplierDetailList().get(i).getProp1());
              goods.setMemo(detailsBean.getSupplierDetailList().get(i).getMemo());
              goodsList.add(goods);
        }
        addProductAdapter5.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==200 && data!=null){
            Goods goods= (Goods) data.getSerializableExtra("goods");
            if(goods!=null){
                if(goods.getId()==0){
                    goodsList.add(goods);
                }else{
                    for (int i=0;i<goodsList.size();i++){
                          if(goodsList.get(i).getId()==goods.getId()){
                              goodsList.get(i).setPrice(goods.getPrice());
                              break;
                          }
                    }

                    //修改明细物料单价
                    EditSupplierGoodsP editSupplierGoodsP=new EditSupplierGoodsP();
                    editSupplierGoodsP.setId(goods.getId());
                    editSupplierGoodsP.setProp1(goods.getPrice());
                    LogUtils.e("+++++++++"+new Gson().toJson(editSupplierGoodsP));
                    addSupplierPersenter.editSupplierPrice(editSupplierGoodsP);
                }
                addProductAdapter5.notifyDataSetChanged();
            }
        }
    }

    /**
     * 删除供应物料成功
     */
    public void deleteSuccess(Goods goods){
        goodsList.remove(goods);
        addProductAdapter5.notifyDataSetChanged();
    }
}
