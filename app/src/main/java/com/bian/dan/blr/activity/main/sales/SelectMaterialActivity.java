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
import com.bian.dan.blr.adapter.sales.SelectMaterialAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择物料
 */
public class SelectMaterialActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_key)
    EditText etKey;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //页码
    private int page;
    //关键字
    private String keys;
    private SelectMaterialAdapter selectMaterialAdapter;
    private List<Material.ListBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_material);
        ButterKnife.bind(this);
        initView();
        //加载数据
        DialogUtil.showProgress(this,"数据加载中");
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("搜索物料");
        reList.setMyRefreshLayoutListener(this);
        selectMaterialAdapter=new SelectMaterialAdapter(this,listAll);
        listView.setAdapter(selectMaterialAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Material.ListBean listBean=listAll.get(position);
                Intent intent=new Intent();
                intent.putExtra("listBean",listBean);
                setResult(100,intent);
                finish();
            }
        });

        /**
         * 监听搜索框
         */
        etKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                keys=s.toString();
                //加载数据
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
        page=1;
        listAll.clear();
        getMeterialByName();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getMeterialByName();
    }

    /**
     * 物料列表-名称模糊查询
     */
    private void getMeterialByName(){
        HttpMethod.getMeterialByName(keys, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Material material= (Material) object;
                if(material.isSussess()){
                    List<Material.ListBean> list=material.getData();
                    listAll.addAll(list);
                    selectMaterialAdapter.notifyDataSetChanged();
                    if(list.size()<HttpMethod.limit){
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(material.getMsg());
                }
            }
            public void onFail(Throwable t) {

            }
        });
    }
}
