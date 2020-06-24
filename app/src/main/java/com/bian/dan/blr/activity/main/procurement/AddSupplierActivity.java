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
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_bank)
    EditText etBank;
    @BindView(R.id.et_account_name)
    EditText etAccountName;
    @BindView(R.id.et_pri_account)
    EditText etPriAccount;
    @BindView(R.id.et_pri_bank)
    EditText etPriBank;
    @BindView(R.id.et_pri_account_name)
    EditText etPriAccountName;
    private AddSupplierPersenter addSupplierPersenter;
    private AddProductAdapter5 addProductAdapter5;
    //产品集合
    private List<Goods> goodsList = new ArrayList<>();
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
        addSupplierPersenter = new AddSupplierPersenter(this);
        detailsBean = (SupplierDetails.DetailsBean) getIntent().getSerializableExtra("detailsBean");

        //产品列表
        addProductAdapter5 = new AddProductAdapter5(this, goodsList, addSupplierPersenter);
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
                setClass(AddProductActivity5.class, 200);
                break;
            case R.id.tv_submit:
                String supplierName = etName.getText().toString().trim();
                String industry = tvIndustry.getText().toString().trim();
                String contact = etContact.getText().toString().trim();
                String phone = etMobile.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String memo = etMemo.getText().toString().trim();
                String account = etAccount.getText().toString().trim();
                String bank = etBank.getText().toString().trim();
                String accountName = etAccountName.getText().toString().trim();
                String priAccount=etPriAccount.getText().toString().trim();
                String priBank=etPriBank.getText().toString().trim();
                String priAccountName=etPriAccountName.getText().toString().trim();
                if (TextUtils.isEmpty(supplierName)) {
                    ToastUtil.showLong("请输入供应商名称");
                    return;
                }
                if (TextUtils.isEmpty(industry)) {
                    ToastUtil.showLong("请选择所属行业");
                    return;
                }
                if (TextUtils.isEmpty(contact)) {
                    ToastUtil.showLong("请输入联系人");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ToastUtil.showLong("请输入电话");
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    ToastUtil.showLong("请输入地址");
                    return;
                }
                AddSupplierP addSupplierP = new AddSupplierP();
                addSupplierP.setSupplierName(supplierName);
                addSupplierP.setIndustry((int) tvIndustry.getTag());
                addSupplierP.setContacts(contact);
                addSupplierP.setPhone(phone);
                addSupplierP.setSupplierAddress(address);
                addSupplierP.setMemo(memo);
                addSupplierP.setOpenAccount(account);
                addSupplierP.setOpenBank(bank);
                addSupplierP.setOpenAccName(accountName);
                addSupplierP.setPrivateAccount(priAccount);
                addSupplierP.setPrivateBank(priBank);
                addSupplierP.setPrivateAccName(priAccountName);
                List<AddSupplierP.GoodList> list = new ArrayList<>();
                for (int i = 0; i < goodsList.size(); i++) {
                    if (goodsList.get(i).getId() == 0) {
                        AddSupplierP.GoodList good = new AddSupplierP.GoodList(goodsList.get(i).getGoodId(), goodsList.get(i).getMemo(), goodsList.get(i).getPrice());
                        list.add(good);
                    }
                }
                addSupplierP.setSupplierDetailList(list);

                //新增
                if (detailsBean == null) {
                    addSupplierPersenter.checkSupplierName(addSupplierP);
                }
                //编辑
                if (detailsBean != null) {
                    addSupplierP.setId(detailsBean.getId());
                    if (detailsBean.getSupplierName().equals(supplierName)) {
                        addSupplierPersenter.UpdateSupplier(addSupplierP);
                    } else {
                        addSupplierPersenter.checkSupplierName(addSupplierP);
                    }
                }
                LogUtils.e("+++++++++" + new Gson().toJson(addSupplierP));
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
        tvHead.setText("编辑供应商");
        etName.setText(detailsBean.getSupplierName());
        tvIndustry.setText(detailsBean.getIndustryStr());
        tvIndustry.setTag(detailsBean.getIndustry());
        etContact.setText(detailsBean.getContacts());
        etMobile.setText(detailsBean.getPhone());
        etAddress.setText(detailsBean.getSupplierAddress());
        etMemo.setText(detailsBean.getMemo());
        etAccount.setText(detailsBean.getOpenAccount());
        etBank.setText(detailsBean.getOpenBank());
        etAccountName.setText(detailsBean.getOpenAccName());
        etPriAccount.setText(detailsBean.getPrivateAccount());
        etPriBank.setText(detailsBean.getPrivateBank());
        etPriAccountName.setText(detailsBean.getPrivateAccName());

        //遍历物料列表
        for (int i = 0; i < detailsBean.getSupplierDetailList().size(); i++) {
            Goods goods = new Goods();
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


    /**
     * 进入编辑页面
     *
     * @param goods:要编辑的对象
     */
    private Goods editGoods;

    public void gotoEdit(Goods goods) {
        this.editGoods = goods;
        Intent intent = new Intent(this, AddProductActivity5.class);
        intent.putExtra("goods", editGoods);
        if (editGoods.getId() != 0) {
            startActivityForResult(intent, 300);
        } else {
            startActivityForResult(intent, 400);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        Goods goods = (Goods) data.getSerializableExtra("goods");
        if (goods == null) {
            return;
        }
        switch (resultCode) {
            //新增数据
            case 200:
                boolean isThe = false;
                for (int i = 0; i < goodsList.size(); i++) {
                    if (goodsList.get(i).getGoodId() == goods.getGoodId()) {
                        isThe = true;
                        break;
                    }
                }
                if (isThe) {
                    ToastUtil.showLong("不能重复添加物料");
                } else {
                    goodsList.add(goods);
                    addProductAdapter5.notifyDataSetChanged();
                }
                break;
            //编辑老数据
            case 300:
                addSupplierPersenter.editSupplierPrice(goods);
                break;
            //编辑新数据
            case 400:
                for (int i = 0; i < goodsList.size(); i++) {
                    if (editGoods.equals(goodsList.get(i))) {
                        goodsList.set(i, goods);
                        break;
                    }
                }
                addProductAdapter5.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    /**
     * 删除供应物料成功
     */
    public void deleteSuccess(Goods goods) {
        goodsList.remove(goods);
        addProductAdapter5.notifyDataSetChanged();
    }

    /**
     * 编辑供应物料成功
     */
    public void editSuccess(Goods goods) {
        for (int i = 0; i < goodsList.size(); i++) {
            if (editGoods.equals(goodsList.get(i))) {
                goodsList.set(i, goods);
                break;
            }
        }
        addProductAdapter5.notifyDataSetChanged();
    }
}
