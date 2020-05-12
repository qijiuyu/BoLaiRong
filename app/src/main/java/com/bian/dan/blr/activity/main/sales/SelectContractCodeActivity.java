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
import com.bian.dan.blr.adapter.warehouse.SelectContractCodeAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.ContractCode;
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
 * 选择合同编号
 */
public class SelectContractCodeActivity extends BaseActivity implements MyRefreshLayoutListener {

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
    private List<ContractCode.ListBean> listAll=new ArrayList<>();
    private SelectContractCodeAdapter selectContractCodeAdapter;
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
        tvHead.setText("选择合同编号");
        reList.setMyRefreshLayoutListener(this);
        selectContractCodeAdapter=new SelectContractCodeAdapter(this,listAll);
        listView.setAdapter(selectContractCodeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContractCode.ListBean listBean=listAll.get(position);
                Intent intent=new Intent();
                intent.putExtra("listBean",listBean);
                setResult(300,intent);
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
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }



    @Override
    public void onRefresh(View view) {
        page=1;
        listAll.clear();
        getContractCode();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getContractCode();
    }


    /**
     * 根据合同编码模糊匹配合同列表
     */
    public void getContractCode(){
        HttpMethod.getContractCode(keys, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                ContractCode contractCode= (ContractCode) object;
                if(contractCode.isSussess()){
                    List<ContractCode.ListBean> list=contractCode.getData().getRows();
                    listAll.addAll(list);
                    selectContractCodeAdapter.notifyDataSetChanged();
                    if(list.size()<HttpMethod.limit){
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(contractCode.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
