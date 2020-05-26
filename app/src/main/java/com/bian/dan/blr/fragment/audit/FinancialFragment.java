package com.bian.dan.blr.fragment.audit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.audit.financial.AuditFinancialActivity;
import com.bian.dan.blr.activity.audit.financial.AuditFinancialDetailsActivity;
import com.bian.dan.blr.adapter.audit.AuditFinancialAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.Financial;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FinancialFragment extends BaseFragment implements MyRefreshLayoutListener {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    Unbinder unbinder;
    //fragment是否可见
    private boolean isVisibleToUser = false;
    //页码
    private int page = 1;
    private List<Financial.ListBean> listAll=new ArrayList<>();
    private AuditFinancialAdapter auditFinancialAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview, container, false);
        unbinder = ButterKnife.bind(this, view);
        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        auditFinancialAdapter=new AuditFinancialAdapter(mActivity,listAll);
        listView.setAdapter(auditFinancialAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(mActivity, AuditFinancialDetailsActivity.class);
                intent.putExtra("detailsId",listAll.get(position).getId());
                startActivityForResult(intent,1000);
            }
        });
        //获取报销列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            getAuditFinancialList();
        }
        return view;
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
                getAuditFinancialList();
            }
        },200);
    }

    /**
     * 上拉加载更多
     * @param view
     */
    public void onLoadMore(View view) {
        page++;
        getAuditFinancialList();
    }


    /**
     * 获取报销列表
     */
    private void getAuditFinancialList(){
        HttpMethod.getAuditFinancialList(((AuditFinancialActivity)mActivity).pageIndex==0 ? "0" : "1,2", page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Financial financial= (Financial) object;
                if(financial.isSussess()){
                    List<Financial.ListBean> list=financial.getData().getRows();
                    listAll.addAll(list);
                    auditFinancialAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(financial.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            reList.startRefresh();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        //获取报销列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            getAuditFinancialList();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
