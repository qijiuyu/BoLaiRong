package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.production.PutStorageByProductAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Goods;
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
    //入库的产品列表
    private List<Goods> goodsList=new ArrayList<>();
    private PutStorageByProductAdapter putStorageByProductAdapter;
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
        putStorageByProductAdapter=new PutStorageByProductAdapter(this,goodsList);
        listProduct.setAdapter(putStorageByProductAdapter);
    }

    @OnClick({R.id.lin_back, R.id.tv_product, R.id.tv_waste, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
           //添加入库产品
            case R.id.tv_product:
                setClass(AddStorageProductActivity.class,200);
                break;
            //添加入库废料
            case R.id.tv_waste:
                setClass(AddWasteActivity.class,400);
                break;
            case R.id.tv_submit:
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        switch (resultCode){
            case 200:
                Goods goods= (Goods) data.getSerializableExtra("goods");
                if(goods!=null) {
                    goodsList.add(goods);
                    putStorageByProductAdapter.notifyDataSetChanged();
                }
                 break;
        }
    }
}
