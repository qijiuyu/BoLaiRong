package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.procurement.AddProductAdapter4;
import com.bian.dan.blr.persenter.warehouse.AddSdEnterPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Goods;
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
    private AddSdEnterPersenter addSdEnterPersenter;
    //产品列表
    private List<Goods> goodList=new ArrayList<>();
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
        addSdEnterPersenter=new AddSdEnterPersenter(this);
        addProductAdapter4=new AddProductAdapter4(this,goodList);
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
                break;
            //选择采购时间
            case R.id.tv_time:
                addSdEnterPersenter.selectTime(tvTime);
                break;
            //选择采购列表
            case R.id.tv_product:
                setClass(AddProductActivity4.class,200);
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
        switch (resultCode){
            //回执产品列表
            case 200:
                if(data!=null){
                    Goods goods= (Goods) data.getSerializableExtra("goods");
                    if(goods!=null){
                        goodList.add(goods);
                        addProductAdapter4.notifyDataSetChanged();
                    }
                }
                break;
            default:
                break;
        }
    }
}
