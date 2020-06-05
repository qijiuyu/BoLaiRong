package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.warehouse.LedTable_Details_Material_Adapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.LedTable;
import com.zxdc.utils.library.bean.LedTableDetails;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 请领表详情页面
 */
public class LedTableDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_receive_people)
    TextView tvReceivePeople;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_create_people)
    TextView tvCreatePeople;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private LedTable.ListBean listBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledtable_details);
        ButterKnife.bind(this);
        initView();
        //获取请领表详情
        getLedTableDetails();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        listBean= (LedTable.ListBean) getIntent().getSerializableExtra("listBean");
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 获取请领表详情
     */
    private void getLedTableDetails(){
        if(listBean==null){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getLedTableDetails(listBean.getId(), new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                try {
                    LedTableDetails ledTableDetails= (LedTableDetails) object;
                    if(ledTableDetails.isSussess()){
                        LedTableDetails.DetailsBean detailsBean=ledTableDetails.getData();
                        if(detailsBean==null){
                            return;
                        }
                        tvReceivePeople.setText(Html.fromHtml("领取人：<font color=\"#000000\">" + detailsBean.getReceiveName() + "</font>"));
                        tvDepartment.setText(Html.fromHtml("领取部门：<font color=\"#000000\">" + detailsBean.getDeptName() + "</font>"));
                        tvCreatePeople.setText(Html.fromHtml("操作人：<font color=\"#000000\">" + detailsBean.getCreateName() + "</font>"));
                        tvCreateTime.setText(Html.fromHtml("操作时间：<font color=\"#000000\">" + detailsBean.getCreateDate() + "</font>"));

                        /**
                         * 领取列表
                         */
                        listView.setAdapter(new LedTable_Details_Material_Adapter(activity,detailsBean.getDetailList()));
                    }else{
                        ToastUtil.showLong(ledTableDetails.getMsg());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
