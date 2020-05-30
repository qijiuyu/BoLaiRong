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
import com.bian.dan.blr.adapter.sales.SelectUserAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
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
 * 选择用户
 */
public class SelectUserActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.et_key)
    EditText etKey;
    //要搜索的关键字
    private String keys;
    //页码
    private int page=1;
    /**
     * 部门id
     */
    private String deptId;
    private List<UserList.ListBean> listAll=new ArrayList<>();
    private SelectUserAdapter selectUserAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("选择用户");
        deptId=getIntent().getStringExtra("deptId");
        reList.setMyRefreshLayoutListener(this);
        selectUserAdapter=new SelectUserAdapter(this,listAll);
        listView.setAdapter(selectUserAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserList.ListBean listBean=listAll.get(position);
                Intent intent=new Intent();
                intent.putExtra("listBean",listBean);
                setResult(400,intent);
                finish();
            }
        });

        /**
         * 监听关键字搜索
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
        getUserList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getUserList();
    }


    /**
     * 获取用户列表
     */
    private void getUserList(){
        HttpMethod.getUserList(deptId, keys, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                UserList userList= (UserList) object;
                if(userList.isSussess()){
                    List<UserList.ListBean> list=userList.getPage().getRows();
                    listAll.addAll(list);
                    selectUserAdapter.notifyDataSetChanged();
                    if(list.size()<HttpMethod.limit){
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(userList.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
