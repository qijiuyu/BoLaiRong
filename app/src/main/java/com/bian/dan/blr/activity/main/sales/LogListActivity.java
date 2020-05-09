package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.LogListAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.bean.Log;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 日志
 */
public class LogListActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    //页码
    private int page = 1;
    private LogListAdapter logListAdapter;
    private List<Log.ListBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_list);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("日志");
        imgRight.setImageResource(R.mipmap.add);
        reList.setMyRefreshLayoutListener(this);
        logListAdapter=new LogListAdapter(this,listAll);
        listView.setAdapter(logListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.ListBean listBean=listAll.get(position);
                Intent intent=new Intent(activity,LogDetailsActivity.class);
                intent.putExtra("listBean",listBean);
                startActivity(intent);
            }
        });

        /**
         * 监听客户名称输入框
         */
        tvKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    imgClear.setVisibility(View.VISIBLE);
                }else{
                    tvKey.setTag("");
                    imgClear.setVisibility(View.GONE);
                }
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
    @OnClick({R.id.lin_back,R.id.img_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                 break;
            //添加
            case R.id.img_right:
                  setClass(AddLogActivity.class,1000);
                  break;
            //选择客户名称
            case R.id.tv_key:
                setClass(SelectCustomerActivity.class,100);
                break;
            case R.id.img_clear:
                tvKey.setText(null);
                break;
             default:
                 break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getLogList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getLogList();
    }


    /**
     * 获取日志列表
     */
    private void getLogList(){
        HttpMethod.getLogList((String) tvKey.getTag(), page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Log log= (Log) object;
                if(log.isSussess()){
                    List<Log.ListBean> list=log.getData().getRows();
                    listAll.addAll(list);
                    logListAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(log.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            //选择客户名称回执
            case 100:
                if(data!=null){
                    Customer customer = (Customer) data.getSerializableExtra("customer");
                    if(customer!=null){
                        tvKey.setTag(String.valueOf(customer.getId()));
                        tvKey.setText(customer.getCustomerName());
                    }
                }
                break;
            //增加出库单成功后，重新刷新列表
            case 1000:
                //加载数据
                reList.startRefresh();
                break;
            default:
                break;
        }
    }
}
