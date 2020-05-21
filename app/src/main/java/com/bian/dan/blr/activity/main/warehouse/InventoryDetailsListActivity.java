package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.SelectMaterialActivity;
import com.bian.dan.blr.adapter.warehouse.InventoryDetailsListAdapter;
import com.bian.dan.blr.persenter.warehouse.InventoryPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Inventory;
import com.zxdc.utils.library.bean.InventoryDetails;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库存明细详情
 */
public class InventoryDetailsListActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    public MyRefreshLayout reList;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_list)
    TextView tvList;
    private InventoryPersenter inventoryPersenter;
    //页码
    private int page = 1;
    private InventoryDetailsListAdapter inventoryDetailsListAdapter;
    private List<InventoryDetails.ListBean> listAll=new ArrayList<>();
    private Inventory.ListBean listBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details_list);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("库存明细详情");
        inventoryPersenter=new InventoryPersenter(this);
        listBean= (Inventory.ListBean) getIntent().getSerializableExtra("listBean");
        if(listBean!=null){
            tvKey.setTag(listBean.getGoodsId());
            tvKey.setText(listBean.getGoodsName()+"/"+listBean.getBrand()+"/"+listBean.getSpec());
            imgClear.setVisibility(View.VISIBLE);
        }
        reList.setMyRefreshLayoutListener(this);
        inventoryDetailsListAdapter=new InventoryDetailsListAdapter(this,listAll);
        listView.setAdapter(inventoryDetailsListAdapter);

        tvKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    imgClear.setVisibility(View.VISIBLE);
                }else{
                    imgClear.setVisibility(View.GONE);
                }
                //加载数据
                reList.startRefresh();
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_key,R.id.img_clear,R.id.tv_type, R.id.tv_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择仓库
            case R.id.tv_type:
                inventoryPersenter.selectType(tvType,tvList);
                break;
            //选择仓库类型
            case R.id.tv_list:
                if(TextUtils.isEmpty(tvType.getText().toString().trim())){
                    ToastUtil.showLong("请先选择仓库");
                    return;
                }
                inventoryPersenter.getDict((int)tvType.getTag(),tvList);
                break;
            case R.id.tv_key:
                setClass(SelectMaterialActivity.class,100);
                break;
            case R.id.img_clear:
                tvKey.setTag(null);
                tvKey.setText(null);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getInventoryDetails();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getInventoryDetails();
    }


    /**
     * 获取库存明细列表
     */
    private void getInventoryDetails() {
        String goodId=null,type=null,stockType=null;
        if(!TextUtils.isEmpty(tvKey.getText().toString())){
            goodId=tvKey.getTag().toString();
        }
        if(!TextUtils.isEmpty(tvType.getText().toString())){
            type=tvType.getTag().toString();
        }
        if(!TextUtils.isEmpty(tvList.getText().toString())){
            stockType=tvList.getTag().toString();
        }
        LogUtils.e(goodId+"++++++++++++++++++"+type+"+++++++++++++++"+stockType);
        HttpMethod.getInventoryDetails(goodId,type ,stockType, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
               InventoryDetails inventoryDetails= (InventoryDetails) object;
                if (inventoryDetails.isSussess()) {
                    List<InventoryDetails.ListBean> list = inventoryDetails.getData().getRows();
                    listAll.addAll(list);
                    inventoryDetailsListAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                } else {
                    ToastUtil.showLong(inventoryDetails.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && data!=null){
            Material.ListBean listBean= (Material.ListBean) data.getSerializableExtra("listBean");
            if(listBean!=null){
                tvKey.setTag(listBean.getId());
                tvKey.setText(listBean.getName()+"/"+listBean.getBrand()+"/"+listBean.getSpec());
            }
        }
    }

}
