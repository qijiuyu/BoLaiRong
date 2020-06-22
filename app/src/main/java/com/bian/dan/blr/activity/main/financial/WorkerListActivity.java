package com.bian.dan.blr.activity.main.financial;

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
import com.bian.dan.blr.activity.main.production.SelectDeptActivity;
import com.bian.dan.blr.activity.main.sales.SelectUserActivity;
import com.bian.dan.blr.adapter.financial.WorkersWageAdapter;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Dept;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.UserList;
import com.zxdc.utils.library.bean.Wage;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.eventbus.EventStatus;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DateUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工人工资列表
 */
public class WorkerListActivity extends BaseActivity  implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private WorkersWageAdapter workersWageAdapter;
    private List<Wage.ListBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("工人工资");
        tvRight.setText("重置");
        //显示当前时间
        tvTime.setText(DateUtils.getNewTime());
        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        reList.setIsLoadingMoreEnabled(false);

        workersWageAdapter=new WorkersWageAdapter(this,listAll);
        listView.setAdapter(workersWageAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity, WorkerDetailsActivity.class);
                intent.putExtra("listBean",listAll.get(position));
                startActivity(intent);
            }
        });


        /**
         * 监听部门的选择
         */
        tvDepartment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
              if(s.length()>0){
                  tvUsername.setTag("");
                  tvUsername.setText(null);
                  //加载数据
                  reList.startRefresh();
              }else{
                  tvDepartment.setTag("");
              }
            }
        });

        /**
         * 监听人员的选择
         */
        tvUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    //加载数据
                    reList.startRefresh();
                }else{
                    tvUsername.setTag("");
                }
            }
        });

        /**
         * 监听时间的选择
         */
        tvTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    //加载数据
                    reList.startRefresh();
                }
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_right, R.id.tv_department, R.id.tv_username, R.id.tv_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_right:
                 tvDepartment.setText(null);
                 tvUsername.setText(null);
                 tvTime.setText(null);
                //加载数据
                reList.startRefresh();
                break;
            //选择部门
            case R.id.tv_department:
                setClass(SelectDeptActivity.class,500);
                break;
            //选择负责人
            case R.id.tv_username:
                if(TextUtils.isEmpty(tvDepartment.getText())){
                    ToastUtil.showLong("请先选择部门");
                    return;
                }
                Intent intent=new Intent(this, SelectUserActivity.class);
                intent.putExtra("deptId",(String)tvDepartment.getTag());
                startActivityForResult(intent,400);
                break;
            //选择时间
            case R.id.tv_time:
                 selectTime();
                break;
            default:
                break;
        }
    }


    /**
     * 下刷
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        listAll.clear();
        getWageList();
    }
    @Override
    public void onLoadMore(View view) {

    }


    /**
     * 工资列表
     */
    private void getWageList(){
        HttpMethod.getWageList((String)tvUsername.getTag(), (String)tvDepartment.getTag(),tvTime.getText().toString().trim(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Wage wage= (Wage) object;
                if(wage.isSussess()){
                    listAll.addAll(wage.getData());
                    workersWageAdapter.notifyDataSetChanged();
                }else{
                    ToastUtil.showLong(wage.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        if(resultCode==500){
            Dept.DeptBean deptBean= (Dept.DeptBean) data.getSerializableExtra("deptBean");
            if(deptBean!=null){
                tvDepartment.setTag(String.valueOf(deptBean.getId()));
                tvDepartment.setText(deptBean.getName());
            }
        }
        if(resultCode==400){
            UserList.ListBean listBean= (UserList.ListBean) data.getSerializableExtra("listBean");
            if(listBean!=null){
                tvUsername.setTag(String.valueOf(listBean.getUserId()));
                tvUsername.setText(listBean.getName());
            }
        }
    }


    /**
     * 选择时间
     */
    public void selectTime(){
        CustomDatePicker customDatePicker = new CustomDatePicker(activity, new CustomDatePicker.ResultHandler() {
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTime.setText(time.substring(0,7));
            }
        }, "1920-01-01 00:00", "2050-12-31 23:59");
        customDatePicker.showYearAndMonth(); // 只显示年月
        customDatePicker.setIsLoop(true); // 不允许循环滚动

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String now = sdf.format(new Date());
        customDatePicker.show(now.split(" ")[0]);
    }

}
