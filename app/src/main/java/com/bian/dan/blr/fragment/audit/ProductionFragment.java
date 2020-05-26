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
import com.bian.dan.blr.activity.audit.production.AuditProductPlanDetailsActivity;
import com.bian.dan.blr.activity.audit.production.AuditProductionActivity;
import com.bian.dan.blr.adapter.sales.ProductPlanAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.ProductPlan;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductionFragment extends BaseFragment implements MyRefreshLayoutListener {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    Unbinder unbinder;
    //fragment是否可见
    private boolean isVisibleToUser = false;
    private ProductPlanAdapter productPlanAdapter;
    //页码
    private int page=1;
    private List<ProductPlan.ListBean> listAll=new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview, container, false);
        unbinder = ButterKnife.bind(this, view);
        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        productPlanAdapter=new ProductPlanAdapter(mActivity,listAll);
        listView.setAdapter(productPlanAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(mActivity, AuditProductPlanDetailsActivity.class);
                intent.putExtra("detailsId",listAll.get(position).getId());
                startActivityForResult(intent,1000);
            }
        });
        //获取生产计划列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            getPlanList();
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
                getPlanList();
            }
        },200);
    }

    /**
     * 上拉加载更多
     * @param view
     */
    public void onLoadMore(View view) {
        page++;
        getPlanList();
    }


    /**
     * 获取生产计划列表
     */
    private void getPlanList(){
        HttpMethod.getAuditPlan(((AuditProductionActivity)mActivity).pageIndex==0 ? "0,2" : "1", page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                ProductPlan productPlan= (ProductPlan) object;
                if(productPlan.isSussess()){
                    List<ProductPlan.ListBean> list=productPlan.getData().getRows();
                    listAll.addAll(list);
                    productPlanAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }

                }else{
                    ToastUtil.showLong(productPlan.getMsg());
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
        //获取生产计划列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            getPlanList();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
