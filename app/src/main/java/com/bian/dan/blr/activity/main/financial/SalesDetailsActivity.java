package com.bian.dan.blr.activity.main.financial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.financial.SalesDetailsAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SalesWage;
import com.zxdc.utils.library.bean.SalesWageDetails;
import com.zxdc.utils.library.bean.WorkerDetails;
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
 * 销售工资明细
 */
public class SalesDetailsActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //销售工资对象
    private SalesWage.ListBean listBean;
    //页码
    private int page=1;
    private SalesDetailsAdapter salesDetailsAdapter;
    private List<SalesWageDetails.ListBean> listAll=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worder_details);
        ButterKnife.bind(this);
        initView();
        //销售工资统计
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("销售工资明细");
        listBean= (SalesWage.ListBean) getIntent().getSerializableExtra("listBean");

        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        salesDetailsAdapter=new SalesDetailsAdapter(this,listAll);
        listView.setAdapter(salesDetailsAdapter);
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 下刷
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        page=1;
        listAll.clear();
        getSalesWageDetails();
    }
    @Override
    public void onLoadMore(View view) {
        page++;
        getSalesWageDetails();
    }


    /**
     * 销售工资统计
     */
    private void getSalesWageDetails(){
        HttpMethod.getSalesWageDetails(listBean.getSalesId(),listBean.getMonth(), page,new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                SalesWageDetails salesWageDetails= (SalesWageDetails) object;
                if(salesWageDetails.isSussess()){
                    List<SalesWageDetails.ListBean> list=salesWageDetails.getData().getRows();
                    listAll.addAll(list);
                    salesDetailsAdapter.notifyDataSetChanged();
                    if(list.size()<HttpMethod.limit){
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(salesWageDetails.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
