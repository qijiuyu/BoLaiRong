package com.bian.dan.blr.activity.main.production;

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
import com.bian.dan.blr.adapter.production.SelectPlanCodeAdapter;
import com.zxdc.utils.library.base.BaseActivity;
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
import butterknife.OnClick;

public class SelectPlanCodeActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.et_key)
    EditText etKey;
    private SelectPlanCodeAdapter selectPlanCodeAdapter;
    //页码
    private int page=1;
    private List<ProductPlan.ListBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plancode);
        ButterKnife.bind(this);
        initView();
        //获取设备列表
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("选择生产计划");
        reList.setMyRefreshLayoutListener(this);
        selectPlanCodeAdapter=new SelectPlanCodeAdapter(this,listAll);
        listView.setAdapter(selectPlanCodeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("listBean",listAll.get(position));
                setResult(600,intent);
                finish();
            }
        });

        /**
         * 监听客户名称输入框
         */
        etKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                //加载数据
                reList.startRefresh();
            }
        });
    }


    /**
     * 按钮点击事件
     *
     * @param view
     */
    @OnClick({R.id.lin_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getPlanList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getPlanList();
    }


    /**
     * 获取已审核通过的生产计划列表
     */
    private void getPlanList(){
        HttpMethod.getPlanList(etKey.getText().toString().trim(),"1", page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                ProductPlan productPlan= (ProductPlan) object;
                if(productPlan.isSussess()){
                    List<ProductPlan.ListBean> list=productPlan.getData().getRows();
                    listAll.addAll(list);
                    selectPlanCodeAdapter.notifyDataSetChanged();
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
}
