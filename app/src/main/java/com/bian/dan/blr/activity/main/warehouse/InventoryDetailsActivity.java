package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.warehouse.InventoryDetailsAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MyRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库存明细
 */
public class InventoryDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_key)
    EditText etKey;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private InventoryDetailsAdapter inventoryDetailsAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("库存明细");
        listView.setAdapter(inventoryDetailsAdapter=new InventoryDetailsAdapter(this));
    }
}
