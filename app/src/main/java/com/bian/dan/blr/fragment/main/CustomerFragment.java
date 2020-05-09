package com.bian.dan.blr.fragment.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.CustomerDetailsActivity;
import com.bian.dan.blr.adapter.sales.CustomerAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
        customerAdapter=new CustomerAdapter(mActivity);
        listView.setAdapter(customerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(CustomerDetailsActivity.class);
            }
        });
        return view;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(msg.obj.toString());
                    break;
                default:
                    break;
            }
            return false;
        }
    });






    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType) {
        if(!isVisibleToUser){
            return;
        }
        switch (eventBusType.getStatus()) {
            default:
                break;
        }
    }


    /**
     * 下刷
     * @param view
     */
    public void onRefresh(View view) {
    }

    /**
     * 上拉加载更多
     * @param view
     */
    public void onLoadMore(View view) {
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
