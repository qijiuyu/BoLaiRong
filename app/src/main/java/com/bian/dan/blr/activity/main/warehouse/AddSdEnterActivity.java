package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.persenter.warehouse.AddSdEnterPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MeasureListView;

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
                setClass(AddProductActivity4.class);
                break;
            case R.id.tv_submit:
                break;
            default:
                break;
        }
    }
}
