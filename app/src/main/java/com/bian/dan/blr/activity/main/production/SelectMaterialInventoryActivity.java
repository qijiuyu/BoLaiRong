package com.bian.dan.blr.activity.main.production;

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
import com.bian.dan.blr.adapter.production.SelectMaterialInventoryAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.MaterialInventory;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择物料库存数据
 */
public class SelectMaterialInventoryActivity extends BaseActivity {

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
    private SelectMaterialInventoryAdapter selectMaterialInventoryAdapter;
    private List<MaterialInventory.ListBean> listAll = new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_material);
        ButterKnife.bind(this);
        initView();
        //物料库存列表-名称模糊查询
        DialogUtil.showProgress(this, "数据加载中");
        getMaterialInventory();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("搜索物料库存");
        reList.setIsLoadingMoreEnabled(false);
        reList.setRefreshEnable(false);
        listView.setAdapter(selectMaterialInventoryAdapter = new SelectMaterialInventoryAdapter(activity, listAll));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MaterialInventory.ListBean listBean = listAll.get(position);
                Intent intent = new Intent();
                intent.putExtra("listBean", listBean);
                setResult(100, intent);
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
                keys = s.toString();
                //加载数据
                getMaterialInventory();
            }
        });

        //设置弹出英文键盘
        etKey.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 物料库存列表-名称模糊查询
     */
    private void getMaterialInventory() {
        HttpMethod.getMaterialInventory(keys, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                MaterialInventory materialInventory = (MaterialInventory) object;
                if (materialInventory.isSussess()) {
                    listAll.clear();
                    listAll.addAll(materialInventory.getData());
                    selectMaterialInventoryAdapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showLong(materialInventory.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
