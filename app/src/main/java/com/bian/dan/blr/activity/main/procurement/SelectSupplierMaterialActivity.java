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
import com.bian.dan.blr.adapter.procurement.SelectSupplierMaterialAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SupplierMaterial;
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
public class SelectSupplierMaterialActivity extends BaseActivity implements MyRefreshLayoutListener {

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
    private List<SupplierMaterial.MaterialBean> listAll=new ArrayList<>();
    private SelectSupplierMaterialAdapter selectSupplierMaterialAdapter;
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
        tvHead.setText("选择供应商物料");
        reList.setMyRefreshLayoutListener(this);
        reList.setIsLoadingMoreEnabled(false);
        selectSupplierMaterialAdapter=new SelectSupplierMaterialAdapter(this,listAll);
        listView.setAdapter(selectSupplierMaterialAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("materialBean",listAll.get(position));
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
        getSupplierDetails();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getSupplierDetails();
    }


    /**
     * 根据首字母查询供应商物料信息
     */
    public void getSupplierDetails(){
        HttpMethod.getSupplierDetails(keys, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                SupplierMaterial supplierMaterial= (SupplierMaterial) object;
                if(supplierMaterial.isSussess()){
                    List<SupplierMaterial.MaterialBean> list=supplierMaterial.getData();
                    listAll.addAll(list);
                    selectSupplierMaterialAdapter.notifyDataSetChanged();
                }else{
                    ToastUtil.showLong(supplierMaterial.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
