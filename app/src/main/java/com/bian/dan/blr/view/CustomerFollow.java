package com.bian.dan.blr.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.CustomerFollowAdapter;
import com.zxdc.utils.library.bean.Log;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerFollow extends Dialog implements MyRefreshLayoutListener {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.tv_no)
    TextView tvNo;
    private View mContentView;
    private Activity context;
    //客户id
    private int customerId;
    //页码
    private int page = 1;
    private List<Log.ListBean> listAll = new ArrayList<>();
    private CustomerFollowAdapter customerFollowAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(context);
        mContentView = inflater.inflate(R.layout.dialog_customer_follow, null);
        setContentView(mContentView);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
        //加载数据
        reList.startRefresh();
    }

    public CustomerFollow(Activity context, int customerId) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.customerId = customerId;
    }


    /**
     * 初始化
     */
    private void initView() {
        reList.setMyRefreshLayoutListener(this);
        customerFollowAdapter = new CustomerFollowAdapter(context, listAll);
        listView.setAdapter(customerFollowAdapter);
    }

    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getFollow();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getFollow();
    }


    /**
     * 获取客户跟进列表
     */
    private void getFollow() {
        HttpMethod.getFollow(customerId, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Log log = (Log) object;
                if (log.isSussess()) {
                    List<Log.ListBean> list = log.getData().getRows();
                    listAll.addAll(list);
                    customerFollowAdapter.notifyDataSetChanged();
                    if(listAll.size()==0){
                        tvNo.setVisibility(View.VISIBLE);
                    }
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                } else {
                    ToastUtil.showLong(log.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }

}
