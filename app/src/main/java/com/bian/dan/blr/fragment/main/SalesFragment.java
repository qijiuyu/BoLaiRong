package com.bian.dan.blr.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.financial.WageManagerActivity;
import com.bian.dan.blr.adapter.financial.SalesWageAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Wage;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.eventbus.EventStatus;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 销售工资
 */
public class SalesFragment extends BaseFragment implements MyRefreshLayoutListener {

    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    Unbinder unbinder;
    //fragment是否可见
    private boolean isVisibleToUser = false;
    //页码
    private int page=1;
    private List<Wage.ListBean> listAll=new ArrayList<>();
    private SalesWageAdapter salesWageAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册eventBus
        EventBus.getDefault().register(this);
    }

    View view;
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sales, container, false);
        unbinder = ButterKnife.bind(this, view);

        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        salesWageAdapter=new SalesWageAdapter(mActivity,listAll);
        listView.setAdapter(salesWageAdapter);

        //工资列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
        return view;
    }


    @OnClick({R.id.tv_key, R.id.img_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_key:
                break;
            case R.id.img_clear:
                tvKey.setText(null);
                break;
            default:
                break;
        }
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType) {
        switch (eventBusType.getStatus()) {
            //根据时间刷新列表
            case EventStatus.SELECT_WAGE_TIME:
                listAll.clear();
                if(isVisibleToUser && view!=null){
                    reList.startRefresh();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 下刷
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        page=1;
        listAll.clear();
        getWageList();
    }

    /**
     * 上拉加载更多
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        getWageList();
    }


    /**
     * 工资列表
     */
    private void getWageList(){
        HttpMethod.getWageList(null, "3,5", ((WageManagerActivity) mActivity).tvTime.getText().toString().trim(), page, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Wage wage= (Wage) object;
                if(wage.isSussess()){
                    List<Wage.ListBean> list=wage.getData().getRows();
                    listAll.addAll(list);
                    salesWageAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(wage.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
