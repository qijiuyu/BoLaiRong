package com.bian.dan.blr.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.financial.WageManagerActivity;
import com.bian.dan.blr.activity.main.production.SelectDeptActivity;
import com.bian.dan.blr.activity.main.sales.SelectUserActivity;
import com.bian.dan.blr.adapter.financial.WorkersWageAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.Dept;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.UserList;
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
 * 工人工资
 */
public class WorkersFragment extends BaseFragment implements MyRefreshLayoutListener {

    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    Unbinder unbinder;
    //fragment是否可见
    private boolean isVisibleToUser = false;
    private WorkersWageAdapter workersWageAdapter;
    //页码
    private int page=1;
    private List<Wage.ListBean> listAll=new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册eventBus
        EventBus.getDefault().register(this);
    }

    View view;
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workers, container, false);
        unbinder = ButterKnife.bind(this, view);

        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        workersWageAdapter=new WorkersWageAdapter(mActivity,listAll);
        listView.setAdapter(workersWageAdapter);

        //工资列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
        return view;
    }


    @OnClick({R.id.tv_department,R.id.tv_username})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //选择部门
            case R.id.tv_department:
                setClass(SelectDeptActivity.class,500);
                break;
            //选择负责人
            case R.id.tv_username:
                if(TextUtils.isEmpty(tvDepartment.getText())){
                    ToastUtil.showLong("请先选择部门");
                    return;
                }
                Intent intent=new Intent(mActivity, SelectUserActivity.class);
                intent.putExtra("deptId",(String)tvDepartment.getTag());
                startActivityForResult(intent,400);
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
        HttpMethod.getWageList((String)tvUsername.getTag(), (String)tvDepartment.getTag(), ((WageManagerActivity) mActivity).tvTime.getText().toString().trim(), page, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Wage wage= (Wage) object;
                if(wage.isSussess()){
                    List<Wage.ListBean> list=wage.getData().getRows();
                    listAll.addAll(list);
                    workersWageAdapter.notifyDataSetChanged();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        if(resultCode==500){
            Dept.DeptBean deptBean= (Dept.DeptBean) data.getSerializableExtra("deptBean");
            if(deptBean!=null){
                tvDepartment.setTag(String.valueOf(deptBean.getId()));
                tvDepartment.setText(deptBean.getName());
                tvUsername.setTag(null);
                tvUsername.setText(null);
                //加载数据
                reList.startRefresh();
            }
        }
        if(resultCode==400){
            UserList.ListBean listBean= (UserList.ListBean) data.getSerializableExtra("listBean");
            if(listBean!=null){
                tvUsername.setTag(String.valueOf(listBean.getUserId()));
                tvUsername.setText(listBean.getName());
                //加载数据
                reList.startRefresh();
            }
        }
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
