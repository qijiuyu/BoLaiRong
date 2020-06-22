package com.bian.dan.blr.activity.main.financial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.financial.EntryBonusDetailsAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.EntryBonus;
import com.zxdc.utils.library.bean.EntryBonusDetails;
import com.zxdc.utils.library.bean.NetWorkCallBack;
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
 * 录入奖金详情
 */
public class EntryBonusDetailsActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //录入奖金列表对象
    private EntryBonus.ListBean listBean;
    //页码
    private int page=1;
    private List<EntryBonusDetails.ListBean> listAll=new ArrayList<>();
    private EntryBonusDetailsAdapter adapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worder_details);
        ButterKnife.bind(this);
        initView();
        //录入奖金明细
        reList.startRefresh();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("录入奖金明细");
        listBean= (EntryBonus.ListBean) getIntent().getSerializableExtra("listBean");

        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        adapter=new EntryBonusDetailsAdapter(this,listAll);
        listView.setAdapter(adapter);
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
        getEntryBonusDetails();
    }
    @Override
    public void onLoadMore(View view) {
        page++;
        getEntryBonusDetails();
    }


    /**
     * 录入客户奖金明细
     */
    private void getEntryBonusDetails(){
        HttpMethod.getEntryBonusDetails(listBean.getCreateId(),listBean.getMonth(), page,new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                EntryBonusDetails entryBonusDetails= (EntryBonusDetails) object;
                if(entryBonusDetails.isSussess()){
                    List<EntryBonusDetails.ListBean> list=entryBonusDetails.getData().getRows();
                    listAll.addAll(list);
                    adapter.notifyDataSetChanged();
                    if(list.size()<HttpMethod.limit){
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(entryBonusDetails.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
