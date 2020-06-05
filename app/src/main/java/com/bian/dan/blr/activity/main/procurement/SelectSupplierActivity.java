package com.bian.dan.blr.activity.main.procurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.procurement.SelectSupplierAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SupplierName;
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
 * 选择供应商
 */
public class SelectSupplierActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_key)
    EditText etKey;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //关键字
    private String keys;
    //页码
    private int page=1;
    private List<SupplierName.ListBean> listAll=new ArrayList<>();
    private SelectSupplierAdapter selectSupplierAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contract_codel);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("选择供应商");
        reList.setMyRefreshLayoutListener(this);
        reList.setIsLoadingMoreEnabled(false);
        selectSupplierAdapter=new SelectSupplierAdapter(this,listAll);
        listView.setAdapter(selectSupplierAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SupplierName.ListBean listBean=listAll.get(position);
                Intent intent=new Intent();
                intent.putExtra("listBean",listBean);
                setResult(500,intent);
                finish();
            }
        });

        /**
         * 监听输入框
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

        //设置弹出英文键盘
        etKey.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }



    @Override
    public void onRefresh(View view) {
        page=1;
        listAll.clear();
        getSupplierNameByName();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getSupplierNameByName();
    }


    /**
     * 根据首字母 获取供应商列表
     */
    public void getSupplierNameByName(){
        HttpMethod.getSupplierNameByName(keys, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                SupplierName supplierName= (SupplierName) object;
                if(supplierName.isSussess()){
                    List<SupplierName.ListBean> list=supplierName.getDataList();
                    listAll.addAll(list);
                    selectSupplierAdapter.notifyDataSetChanged();
                }else{
                    ToastUtil.showLong(supplierName.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
