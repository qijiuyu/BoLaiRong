package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.warehouse.Procurement_Entry_Goods_Adapter;
import com.bian.dan.blr.persenter.warehouse.Confirm_Procurement_Entry_Persenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.ProcurementDetails;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确认采购单入库
 */
public class Confirm_Procurement_EntryActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.et_remark)
    EditText etRemark;
    //适配器
    private Procurement_Entry_Goods_Adapter procurement_entry_goods_adapter;
    //详情对象
    private ProcurementDetails.DetailsBean detailsBean;
    public Confirm_Procurement_Entry_Persenter confirm_procurement_entry_persenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_procurement_entry);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("确认收货");
        confirm_procurement_entry_persenter=new Confirm_Procurement_Entry_Persenter(this);
        detailsBean= (ProcurementDetails.DetailsBean) getIntent().getSerializableExtra("detailsBean");
        if(detailsBean!=null){
            procurement_entry_goods_adapter=new Procurement_Entry_Goods_Adapter(this,detailsBean.getPurchaseDetailList());
            listView.setAdapter(procurement_entry_goods_adapter);
        }
    }

    @OnClick({R.id.lin_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_submit:
                break;
            default:
                break;
        }
    }
}
