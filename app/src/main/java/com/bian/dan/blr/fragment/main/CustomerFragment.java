package com.bian.dan.blr.fragment.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.CustomerDetailsActivity;
import com.bian.dan.blr.activity.main.sales.CustomerManagerActivity;
import com.bian.dan.blr.adapter.sales.CustomerAdapter;
import com.bian.dan.blr.application.MyApplication;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.bean.CustomerList;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.UserInfo;
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
import butterknife.Unbinder;

public class CustomerFragment extends BaseFragment implements MyRefreshLayoutListener {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    Unbinder unbinder;
    //fragment是否可见
    private boolean isVisibleToUser=false;
    //页码
    private int page=1;
    //客户集合
    private List<Customer> listAll=new ArrayList<>();
    private CustomerAdapter customerAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册eventBus
        EventBus.getDefault().register(this);
    }

    View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview, container, false);
        unbinder = ButterKnife.bind(this, view);
        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        customerAdapter=new CustomerAdapter(mActivity,listAll);
        listView.setAdapter(customerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(CustomerDetailsActivity.class);
            }
        });
        //获取客户列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
        return view;
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType) {
        switch (eventBusType.getStatus()) {
            //刷新列表
            case EventStatus.REFRESH_CUSTOMER_LIST:
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
    private Handler handler=new Handler();
    public void onRefresh(View view) {
        handler.postDelayed(new Runnable() {
            public void run() {
                page=1;
                listAll.clear();
                getCustomer();
            }
        },300);
    }

    /**
     * 上拉加载更多
     * @param view
     */
    public void onLoadMore(View view) {
        page++;
        getCustomer();
    }


    /**
     * 获取客户列表
     */
    private void getCustomer(){
        //1：私有    2：公有
        final int privateState=((CustomerManagerActivity)mActivity).pageIndex;
        //用户id
        String privateId=null;
        if(privateState==1){
            final UserInfo userInfo= MyApplication.getUser();
            privateId=String.valueOf(userInfo.getUser().getUserId());
        }
        //要搜索的关键词id
        String customerId=(String)((CustomerManagerActivity)mActivity).tvKey.getTag();
        HttpMethod.getCustomer(privateState, privateId, customerId, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                CustomerList customerList= (CustomerList) object;
                if(customerList.isSussess()){
                    List<Customer> list=customerList.getData().getRows();
                    listAll.addAll(list);
                    customerAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }

                }else{
                    ToastUtil.showLong(customerList.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
        //获取客户列表
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
