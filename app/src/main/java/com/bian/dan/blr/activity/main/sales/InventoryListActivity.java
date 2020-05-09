package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.InventoryAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Inventory;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库存明细
 */
public class InventoryListActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    //页码
    private int page = 1;
    private List<Inventory.ListBean> listAll = new ArrayList<>();
    private InventoryAdapter inventoryAdapter;
    //商品id
    private String goodId;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("库存明细");
        reList.setMyRefreshLayoutListener(this);
        inventoryAdapter = new InventoryAdapter(this, listAll);
        listView.setAdapter(inventoryAdapter);

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


    @OnClick({R.id.lin_back, R.id.tv_key,R.id.img_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_key:
                setClass(SelectMaterialActivity.class,100);
                break;
            case R.id.img_clear:
                 goodId=null;
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
        getInventoryList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getInventoryList();
    }


    /**
     * 获取库存明细列表
     */
    private void getInventoryList() {
        HttpMethod.getInventoryList(goodId, null, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Inventory inventory = (Inventory) object;
                if (inventory.isSussess()) {
                    List<Inventory.ListBean> list = inventory.getData().getRows();
                    listAll.addAll(list);
                    inventoryAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                } else {
                    ToastUtil.showLong(inventory.getMsg());
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
                goodId=String.valueOf(listBean.getId());
                tvKey.setText(listBean.getName()+"/"+listBean.getBrand()+"/"+listBean.getSpec());
            }
        }
    }
}
