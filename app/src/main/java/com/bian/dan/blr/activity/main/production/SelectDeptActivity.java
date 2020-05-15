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
import com.bian.dan.blr.adapter.production.SelectDeptAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Dept;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择部门
 */
public class SelectDeptActivity extends BaseActivity {

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
    /**
     * 父级部门id
     */
    private String parendId;
    private SelectDeptAdapter selectDeptAdapter;
    private List<Dept.DeptBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dept);
        ButterKnife.bind(this);
        initView();
        //获取部门列表
        getDeptList();
    }



    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("选择部门");
        parendId=getIntent().getStringExtra("parendId");
        reList.setIsLoadingMoreEnabled(false);
        reList.setRefreshEnable(false);
        selectDeptAdapter=new SelectDeptAdapter(this,listAll);
        listView.setAdapter(selectDeptAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("deptBean", listAll.get(position));
                setResult(500, intent);
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
                //获取部门列表
                getDeptList();
            }
        });
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 获取部门列表
     */
    private void getDeptList(){
        HttpMethod.getDeptList(keys, parendId, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                Dept dept= (Dept) object;
                if(dept.isSussess()){
                    listAll.clear();
                    listAll.addAll(dept.getPage());
                    selectDeptAdapter.notifyDataSetChanged();
                }else{
                    ToastUtil.showLong(dept.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
