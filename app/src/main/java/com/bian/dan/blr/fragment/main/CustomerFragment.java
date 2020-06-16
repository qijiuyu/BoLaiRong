package com.bian.dan.blr.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.CustomerDetailsActivity;
import com.bian.dan.blr.activity.main.sales.CustomerManagerActivity;
import com.bian.dan.blr.activity.main.sales.SelectCustomerActivity;
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
import butterknife.OnClick;
import butterknife.Unbinder;

public class CustomerFragment extends BaseFragment implements MyRefreshLayoutListener {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.tv_key)
    public TextView tvKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    Unbinder unbinder;
    //fragment是否可见
    private boolean isVisibleToUser=false;
    //页码
    private int page=1;
    //客户集合
    private List<Customer> listAll=new ArrayList<>();
    private CustomerAdapter customerAdapter;
    /**
     * 1：私有    2：公有
     */
    private int privateState;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册eventBus
        EventBus.getDefault().register(this);
    }

    View view;
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customer, container, false);
        unbinder = ButterKnife.bind(this, view);
        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        customerAdapter=new CustomerAdapter(mActivity,listAll);
        listView.setAdapter(customerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer customer=listAll.get(position);
                Intent intent=new Intent(mActivity, CustomerDetailsActivity.class);
                intent.putExtra("customer",customer);
                mActivity.startActivity(intent);
            }
        });

        /**
         * 监听客户名称输入框
         */
        tvKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    imgClear.setVisibility(View.VISIBLE);
                }else{
                    tvKey.setTag("");
                    imgClear.setVisibility(View.GONE);
                }
                //加载数据
                reList.startRefresh();
            }
        });

        //获取客户列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
        return view;
    }


    @OnClick({R.id.tv_key, R.id.img_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //选择客户名称
            case R.id.tv_key:
                Intent intent=new Intent(mActivity, SelectCustomerActivity.class);
                if(privateState==2){
                    intent.putExtra("type",1);
                }
                startActivityForResult(intent,100);
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
        privateState=((CustomerManagerActivity)mActivity).pageIndex;
        //用户id
        String privateId=null;
        if(privateState==1){
            final UserInfo userInfo= MyApplication.getUser();
            privateId=String.valueOf(userInfo.getUser().getUserId());
        }
        HttpMethod.getCustomer(privateState, privateId, (String)tvKey.getTag(), page, new NetWorkCallBack() {
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


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && data!=null){
            Customer customer = (Customer) data.getSerializableExtra("customer");
            if(customer!=null){
                tvKey.setTag(String.valueOf(customer.getId()));
                tvKey.setText(customer.getCustomerName());
            }
        }
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
