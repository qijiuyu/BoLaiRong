package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.production.SelectDeptActivity;
import com.bian.dan.blr.activity.main.sales.SelectUserActivity;
import com.bian.dan.blr.adapter.warehouse.ProductOutAdapter;
import com.bian.dan.blr.persenter.warehouse.ProductOutPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Dept;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.OutAndEntry;
import com.zxdc.utils.library.bean.UserList;
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
 * 生产出库列表
 */
public class ProductOutActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    //页码
    private int page = 1;
    private List<OutAndEntry.ListBean> listAll = new ArrayList<>();
    private ProductOutAdapter outAndEntryAdapter;
    private ProductOutPersenter persenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_and_entry);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("生产出库");
        persenter=new ProductOutPersenter(this);
        reList.setMyRefreshLayoutListener(this);
        outAndEntryAdapter = new ProductOutAdapter(this, listAll);
        listView.setAdapter(outAndEntryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, OutAndEntry_DetailsActivity.class);
                intent.putExtra("requireId", listAll.get(position).getId());
                startActivity(intent);
            }
        });


        /**
         * 监听开始日期
         */
        tvStartTime.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0 && !TextUtils.isEmpty(tvEndTime.getText().toString())){
                    //加载数据
                    reList.startRefresh();
                }
            }
        });

        /**
         * 监听结束日期
         */
        tvEndTime.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0 && !TextUtils.isEmpty(tvStartTime.getText().toString())){
                    //加载数据
                    reList.startRefresh();
                }
            }
        });

    }


    @OnClick({R.id.lin_back,R.id.tv_start_time, R.id.tv_end_time,R.id.tv_department,R.id.tv_username})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择采购开始日期
            case R.id.tv_start_time:
                persenter.selectStartTime(tvStartTime,tvEndTime);
                break;
            //选择采购结束日期
            case R.id.tv_end_time:
                persenter.selectEndTime(tvStartTime,tvEndTime);
                break;
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
                 Intent intent=new Intent(this, SelectUserActivity.class);
                 intent.putExtra("deptId",(String)tvDepartment.getTag());
                 startActivityForResult(intent,400);
                 break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getProductOutList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getProductOutList();
    }


    /**
     * 生产出库记录查询
     */
    private void getProductOutList() {
        HttpMethod.getProductOutList((String)tvDepartment.getTag(),(String)tvUsername.getTag(), tvStartTime.getText().length()==0 ? null : tvStartTime.getText().toString(), tvEndTime.getText().length()==0 ? null : tvEndTime.getText().toString(), page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                OutAndEntry outAndEntry = (OutAndEntry) object;
                if (outAndEntry.isSussess()) {
                    List<OutAndEntry.ListBean> list = outAndEntry.getData().getRows();
                    listAll.addAll(list);
                    outAndEntryAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                } else {
                    ToastUtil.showLong(outAndEntry.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
}
