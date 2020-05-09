package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.SelectCustomerAdapter;
import com.bian.dan.blr.application.MyApplication;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SelectCustomer;
import com.zxdc.utils.library.bean.UserInfo;
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
 * 选择客户
 */
public class SelectCustomerActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.et_key)
    EditText etKey;
    //要搜索的关键字
    private String keys;
    private List<Customer> listAll = new ArrayList<>();
    private SelectCustomerAdapter selectCustomerAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_customer);
        ButterKnife.bind(this);
        initView();
        //获取设备列表
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("选择客户");
        reList.setMyRefreshLayoutListener(this);
        selectCustomerAdapter = new SelectCustomerAdapter(this, listAll);
        listView.setAdapter(selectCustomerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer customer=listAll.get(position);
                Intent intent = new Intent();
                intent.putExtra("customer", customer);
                setResult(100, intent);
                SelectCustomerActivity.this.finish();
            }
        });

        /**
         * 监听关键字搜索
         */
        etKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                keys=s.toString();
                //获取设备列表
                reList.startRefresh();
            }
        });
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onRefresh(View view) {
        listAll.clear();
        getCustomerList();
    }

    @Override
    public void onLoadMore(View view) {
        getCustomerList();
    }


    /**
     * 获取客户列表
     */
    private void getCustomerList() {
        UserInfo userInfo= MyApplication.getUser();
        HttpMethod.getCustomerList(keys,userInfo.getUser().getUserId(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                SelectCustomer customer = (SelectCustomer) object;
                if (customer == null) {
                    return;
                }
                if (customer.isSussess()) {
                    List<Customer> list =customer.getCustomer();
                    listAll.addAll(list);
                    selectCustomerAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                } else {
                    ToastUtil.showLong(customer.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
