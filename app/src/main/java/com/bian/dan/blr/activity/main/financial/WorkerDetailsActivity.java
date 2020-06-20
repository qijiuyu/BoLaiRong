package com.bian.dan.blr.activity.main.financial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.financial.WorkerDetailsAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Wage;
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
 * 工人工资明细
 */
public class WorkerDetailsActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //工资列表对象
    private Wage.ListBean listBean;
    //页码
    private int page=1;
    private List<WorkerDetails.ListBean> listAll=new ArrayList<>();
    private WorkerDetailsAdapter workerDetailsAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worder_details);
        ButterKnife.bind(this);
        initView();
        //工人工资明细
        getWorkerDetails();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("工人工资明细");
        listBean= (Wage.ListBean) getIntent().getSerializableExtra("listBean");

        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        workerDetailsAdapter=new WorkerDetailsAdapter(this,listAll);
        listView.setAdapter(workerDetailsAdapter);
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
        getWorkerDetails();
    }
    @Override
    public void onLoadMore(View view) {
        page++;
        getWorkerDetails();
    }


    /**
     * 工人工资明细
     */
    private void getWorkerDetails(){
        HttpMethod.getWorkerDetails(listBean.getMonth(), listBean.getCreateId(), page,new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                WorkerDetails workerDetails= (WorkerDetails) object;
                if(workerDetails.isSussess()){
                    List<WorkerDetails.ListBean> list=workerDetails.getData().getRows();
                    listAll.addAll(list);
                    workerDetailsAdapter.notifyDataSetChanged();
                    if(list.size()<HttpMethod.limit){
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(workerDetails.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
