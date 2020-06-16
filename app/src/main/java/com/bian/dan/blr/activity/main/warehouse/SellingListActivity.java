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
import com.bian.dan.blr.adapter.warehouse.SellingAdapter;
import com.bian.dan.blr.persenter.warehouse.SellingPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SellingOutBound;
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
 * 售卖申请表
 */
public class SellingListActivity extends BaseActivity implements MyRefreshLayoutListener {

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
    //页码
    private int page = 1;
    //数据集合
    private List<SellingOutBound.ListBean> listAll=new ArrayList<>();
    private SellingAdapter sellingAdapter;
    private SellingPersenter sellingPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling_list);
        ButterKnife.bind(this);
        initView();
        //获取售卖出库表
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("售卖申请表");
        sellingPersenter=new SellingPersenter(this);
        reList.setMyRefreshLayoutListener(this);
        listView.setAdapter(sellingAdapter = new SellingAdapter(this,listAll));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity,SellingDetailsActivity.class);
                intent.putExtra("listBean",listAll.get(position));
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
                    //获取售卖出库表
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
                    //获取售卖出库表
                    reList.startRefresh();
                }
            }
        });
    }


    @OnClick({R.id.lin_back,R.id.tv_start_time, R.id.tv_end_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择采购开始日期
            case R.id.tv_start_time:
                sellingPersenter.selectStartTime(tvStartTime,tvEndTime);
                break;
            //选择采购结束日期
            case R.id.tv_end_time:
                sellingPersenter.selectEndTime(tvStartTime,tvEndTime);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getSellingList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getSellingList();
    }


    /**
     * 售卖出库表
     */
    private void getSellingList(){
        HttpMethod.getSellingList(tvStartTime.getText().toString().trim(), tvEndTime.getText().toString().trim(), page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                SellingOutBound sellingOutBound= (SellingOutBound) object;
                if(sellingOutBound.isSussess()){
                    List<SellingOutBound.ListBean> list=sellingOutBound.getData().getRows();
                    listAll.addAll(list);
                    sellingAdapter.notifyDataSetChanged();
                    if(list.size()<HttpMethod.limit){
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(sellingOutBound.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
