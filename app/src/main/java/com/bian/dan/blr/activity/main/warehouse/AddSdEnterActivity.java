package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.SelectUserActivity;
import com.bian.dan.blr.adapter.procurement.AddProductAdapter4;
import com.bian.dan.blr.persenter.warehouse.AddSdEnterPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.UserList;
import com.zxdc.utils.library.bean.parameter.AddSdEnterP;
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
 * 新增手动入库单
 */
public class AddSdEnterActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_product)
    TextView tvProduct;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.et_remark)
    EditText etRemark;
    private AddSdEnterPersenter addSdEnterPersenter;
    //产品列表
    private List<Goods> goodList = new ArrayList<>();
    private AddProductAdapter4 addProductAdapter4;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sdenter);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增手动入库单");
        addSdEnterPersenter = new AddSdEnterPersenter(this);
        addProductAdapter4 = new AddProductAdapter4(this, goodList);
        listView.setAdapter(addProductAdapter4);
    }

    @OnClick({R.id.lin_back, R.id.tv_name, R.id.tv_time, R.id.tv_product, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择采购员
            case R.id.tv_name:
                setClass(SelectUserActivity.class, 400);
                break;
            //选择采购时间
            case R.id.tv_time:
                addSdEnterPersenter.selectTime(tvTime);
                break;
            //选择采购列表
            case R.id.tv_product:
                setClass(AddProductActivity4.class, 200);
                break;
            case R.id.tv_submit:
                String name = tvName.getText().toString().trim();
                String time = tvTime.getText().toString().trim();
                String remark=etRemark.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请选择采购员");
                    return;
                }
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择采购日期");
                    return;
                }
                if(goodList.size()==0){
                    ToastUtil.showLong("请选择采购列表");
                    return;
                }
                AddSdEnterP addSdEnterP=new AddSdEnterP();
                addSdEnterP.setPurcId((int)tvName.getTag());
                addSdEnterP.setPurcDate(time);
                addSdEnterP.setMemo(remark);
                List<AddSdEnterP.DetailsList> list=new ArrayList<>();
                for (int i=0;i<goodList.size();i++){
                    AddSdEnterP.DetailsList detailsList=new AddSdEnterP.DetailsList();
                    detailsList.setGoodsId(goodList.get(i).getGoodId());
                    detailsList.setStockType(goodList.get(i).getStockType());
                    detailsList.setBatchNo(goodList.get(i).getBatchNo());
                    detailsList.setPrice(goodList.get(i).getPrice());
                    detailsList.setNum(goodList.get(i).getNum());
                    detailsList.setAmount(goodList.get(i).getTotalMoney());
                    list.add(detailsList);
                }
                addSdEnterP.setDetailList(list);
                LogUtils.e("+++++++++++++"+SPUtil.gson.toJson(addSdEnterP));
                //新增数据
                addSdEnterPersenter.addSdEnter(addSdEnterP);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            //回执产品列表
            case 200:
                if (data != null) {
                    Goods goods = (Goods) data.getSerializableExtra("goods");
                    if (goods != null) {
                        goodList.add(goods);
                        addProductAdapter4.notifyDataSetChanged();
                    }
                }
                break;
            //返回用户信息
            case 400:
                if (data == null) {
                    return;
                }
                UserList.ListBean listBean = (UserList.ListBean) data.getSerializableExtra("listBean");
                if (listBean != null) {
                    tvName.setText(listBean.getName());
                    tvName.setTag(listBean.getUserId());
                }
                break;
            default:
                break;
        }
    }
}
