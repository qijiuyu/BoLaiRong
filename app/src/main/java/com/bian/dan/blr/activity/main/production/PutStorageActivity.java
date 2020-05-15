package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.production.AddWasteAdapter;
import com.bian.dan.blr.adapter.production.PutStorageByProductAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.AddPutStorageP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
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
 * 申请入库
 */
public class PutStorageActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.list_product)
    MeasureListView listProduct;
    @BindView(R.id.list_waste)
    MeasureListView listWaste;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    @BindView(R.id.lin_good)
    LinearLayout linGood;
    @BindView(R.id.tv_waste_num)
    TextView tvWasteNum;
    @BindView(R.id.lin_waste)
    LinearLayout linWaste;
    //出入库id
    private int requireId;
    //入库的产品列表
    private List<Goods> goodsList = new ArrayList<>();
    //入库的余废料列表
    private List<Goods> wasteList = new ArrayList<>();
    private PutStorageByProductAdapter putStorageByProductAdapter;
    private AddWasteAdapter addWasteAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_storage);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增入库申请");
        requireId=getIntent().getIntExtra("requireId",0);
        putStorageByProductAdapter = new PutStorageByProductAdapter(this, goodsList);
        listProduct.setAdapter(putStorageByProductAdapter);

        addWasteAdapter = new AddWasteAdapter(this, wasteList);
        listWaste.setAdapter(addWasteAdapter);
    }

    @OnClick({R.id.lin_back, R.id.tv_product, R.id.tv_waste, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //添加入库产品
            case R.id.tv_product:
                setClass(AddStorageProductActivity.class, 200);
                break;
            //添加入库废料
            case R.id.tv_waste:
                setClass(AddWasteActivity.class, 400);
                break;
            //提交
            case R.id.tv_submit:
                if(goodsList.size()==0){
                    ToastUtil.showLong("请选择要入库的产品列表");
                    return;
                }
                AddPutStorageP addPutStorageP=new AddPutStorageP();
                addPutStorageP.setRequireId(requireId);
                //遍历添加入库产品
                List<AddPutStorageP.EntryList> entryList=new ArrayList<>();
                for (int i=0;i<goodsList.size();i++){
                    AddPutStorageP.EntryList entry=new AddPutStorageP.EntryList();
                    entry.setGoodsId(goodsList.get(i).getId());
                    entry.setNum(goodsList.get(i).getNum());
                    entry.setStockType(goodsList.get(i).getStockType());
                    entry.setBatchNo(goodsList.get(i).getBatchNo());
                    entry.setMemo(goodsList.get(i).getMemo());
                    entry.setProp1(goodsList.get(i).getRewardMoney());
                    entry.setProp2(goodsList.get(i).getRewardDes());
                    entry.setProp3(goodsList.get(i).getFineMoney());
                    entry.setProp4(goodsList.get(i).getFineDes());
                    entryList.add(entry);
                }
                addPutStorageP.setEntryDetailList(entryList);
                //遍历添加入库的余废料
                List<AddPutStorageP.WasteList> oddList=new ArrayList<>();
                for (int i=0;i<wasteList.size();i++){
                    AddPutStorageP.WasteList waste=new AddPutStorageP.WasteList();
                    waste.setGoodsId(wasteList.get(i).getId());
                    waste.setNum(wasteList.get(i).getNum());
                    waste.setStockType(wasteList.get(i).getStockType());
                    waste.setBatchNo(wasteList.get(i).getBatchNo());
                    waste.setType(wasteList.get(i).getType());
                    waste.setDeptId(wasteList.get(i).getDeptId());
                    waste.setChargeId(wasteList.get(i).getChargeId());
                    oddList.add(waste);
                }
                addPutStorageP.setOddsDetailList(oddList);
                LogUtils.e("+++++++++++++"+SPUtil.gson.toJson(addPutStorageP));
                //申请入库
                addPutStorage(addPutStorageP);
                break;
            default:
                break;
        }
    }


    private Goods goods;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (resultCode) {
            //返回入库产品
            case 200:
                goods = (Goods) data.getSerializableExtra("goods");
                if (goods != null) {
                    goodsList.add(goods);
                    putStorageByProductAdapter.notifyDataSetChanged();
                    linGood.setVisibility(View.VISIBLE);
                    int totalNum=0;
                    for (int i=0;i<goodsList.size();i++){
                        totalNum+=goodsList.get(i).getNum();
                    }
                    tvProductNum.setText("数量："+totalNum);
                }
                break;
            //返回入库废料
            case 400:
                goods = (Goods) data.getSerializableExtra("goods");
                if (goods != null) {
                    wasteList.add(goods);
                    addWasteAdapter.notifyDataSetChanged();
                    linWaste.setVisibility(View.VISIBLE);
                    int totalNum=0;
                    for (int i=0;i<wasteList.size();i++){
                        totalNum+=wasteList.get(i).getNum();
                    }
                    tvWasteNum.setText("数量："+totalNum);
                }
                break;
            default:
                break;
        }
    }


    /**
     * 申请入库
     */
    private void addPutStorage(AddPutStorageP addPutStorageP){
        DialogUtil.showProgress(this,"入库中");
        HttpMethod.addPutStorage(addPutStorageP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    Intent intent=new Intent();
                    setResult(100,intent);
                    finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            public void onFail(Throwable t) {

            }
        });
    }

}
