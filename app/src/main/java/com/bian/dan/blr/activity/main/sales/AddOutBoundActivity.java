package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.AddProductAdapter;
import com.bian.dan.blr.persenter.sales.AddOutBoundPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.ContractCode;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.parameter.AddGoodP;
import com.zxdc.utils.library.bean.parameter.OutBoundP;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class AddOutBoundActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_remark)
    EditText etRemark;
    private AddProductAdapter addProductAdapter;
    private AddOutBoundPersenter addOutBoundPersenter;
    //合同对象
    private ContractCode.ListBean listBean;
    //产品列表
    private List<Goods> goodList = new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_outbound);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        addOutBoundPersenter = new AddOutBoundPersenter(this);
        tvHead.setText("新增出库单");
        addProductAdapter = new AddProductAdapter(this, goodList);
        listView.setAdapter(addProductAdapter);
    }


    /**
     * 按钮点击事件
     *
     * @param view
     */
    @OnClick({R.id.lin_back, R.id.tv_code, R.id.tv_pay_type, R.id.tv_product, R.id.tv_submit})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择合同编号
            case R.id.tv_code:
                intent.setClass(this, SelectContractCodeActivity.class);
                startActivityForResult(intent, 300);
                break;
            //选择付款方式
            case R.id.tv_pay_type:
                addOutBoundPersenter.selectText(tvPayType);
                break;
            //选择产品列表
            case R.id.tv_product:
                intent.setClass(this, AddProductActivity.class);
                startActivityForResult(intent, 200);
                break;
            case R.id.tv_submit:
                String code = tvCode.getText().toString().trim();
                String payType = tvPayType.getText().toString().trim();
                String memo=etRemark.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    ToastUtil.showLong("请选择合同编号");
                    return;
                }
                if (TextUtils.isEmpty(payType)) {
                    ToastUtil.showLong("请选择付款方式");
                    return;
                }
                if (goodList.size() == 0) {
                    ToastUtil.showLong("请添加产品列表");
                    return;
                }
                OutBoundP outBoundP = new OutBoundP();
                outBoundP.setCustomerId(listBean.getCustomerId());
                outBoundP.setProp2(code);
                outBoundP.setProp1(listBean.getCreateId());
                outBoundP.setMemo(memo);
                if (payType.equals("全款")) {
                    outBoundP.setPayType(1);
                } else {
                    outBoundP.setPayType(2);
                }
                List<AddGoodP> list = new ArrayList<>();
                for (int i = 0; i < goodList.size(); i++) {
                    Goods goods = goodList.get(i);
                    AddGoodP addGoodP = new AddGoodP(goods.getGoodId(), goods.getNum(), goods.getPrice(), goods.getTotalMoney(), goods.getIsInvoice(), goods.getMemo());
                    list.add(addGoodP);
                }
                outBoundP.setOutOrderDetailList(list);
                //增加出库单
                LogUtils.e("++++++" + SPUtil.gson.toJson(outBoundP));
                addOutBoundPersenter.addOutBound(outBoundP);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            //回执合同编号
            case 300:
                if (data != null) {
                    listBean = (ContractCode.ListBean) data.getSerializableExtra("listBean");
                    tvCode.setText(listBean.getProp2());
                    tvName.setText(listBean.getCustomerName());
                    tvContact.setText(listBean.getContacts());
                    tvMobile.setText(listBean.getPhone());
                    tvAddress.setText(listBean.getPostAddress());
                }
                break;
            //回执产品列表
            case 200:
                if (data != null) {
                    Goods goods = (Goods) data.getSerializableExtra("goods");
                    if (goods != null) {
                        boolean isThe = false;
                        for (int i = 0; i < goodList.size(); i++) {
                            if (goodList.get(i).getGoodId() == goods.getGoodId()) {
                                isThe = true;
                                break;
                            }
                        }
                        if (isThe) {
                            ToastUtil.showLong("不能重复添加物料");
                        } else {
                            goodList.add(goods);
                            addProductAdapter.notifyDataSetChanged();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

}
