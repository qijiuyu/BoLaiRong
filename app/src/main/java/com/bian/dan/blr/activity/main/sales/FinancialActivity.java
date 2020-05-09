package com.bian.dan.blr.activity.main.sales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.DialogSelectAdapter;
import com.bian.dan.blr.adapter.sales.FinancialAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 财务报销
 */
public class FinancialActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private FinancialAdapter financialAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("财务报销");
        imgRight.setImageResource(R.mipmap.add);
        financialAdapter=new FinancialAdapter(this);
        listView.setAdapter(financialAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(FinancialDetailsActivity.class);
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_status,R.id.img_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
           //切换状态
            case R.id.tv_status:
                showSelectType();
                break;
            //添加财务报销
            case R.id.img_right:
                setClass(AddFinancialActivity.class);
                 break;
            default:
                break;
        }
    }


    /**
     * 切换状态
     */
    private void showSelectType(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_select, null);
        final PopupWindow popupWindow = DialogUtil.showPopWindow(view);
        popupWindow.showAsDropDown(tvStatus);
        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(new DialogSelectAdapter(this));
    }
}
