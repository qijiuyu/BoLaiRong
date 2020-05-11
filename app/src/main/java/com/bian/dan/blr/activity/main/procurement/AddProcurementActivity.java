package com.bian.dan.blr.activity.main.procurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.persenter.procurement.AddProcurementPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MeasureListView;

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
    private AddProcurementPersenter addProcurementPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_procurement);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增采购单");
        addProcurementPersenter=new AddProcurementPersenter(this);
    }

    @OnClick({R.id.lin_back, R.id.tv_time, R.id.tv_product,R.id.tv_submit})
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
                 setClass(AddProductActivity3.class,200);
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
    }
}
