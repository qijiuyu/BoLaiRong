package com.bian.dan.blr.activity.main.financial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.SelectUserActivity;
import com.bian.dan.blr.adapter.financial.EntryBonusAdapter;
import com.bian.dan.blr.adapter.financial.SalesWageAdapter;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.EntryBonus;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SalesWage;
import com.zxdc.utils.library.bean.UserList;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DateUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 录入奖金
 */
public class EntryBonusActivity extends BaseActivity   implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private EntryBonusAdapter adapter;
    private List<EntryBonus.ListBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_bonus);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("录入奖金");
        tvRight.setText("重置");
        //显示当前时间
        tvTime.setText(DateUtils.getNewTime());
        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        reList.setIsLoadingMoreEnabled(false);

        adapter=new EntryBonusAdapter(this,listAll);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity,EntryBonusDetailsActivity.class);
                intent.putExtra("listBean",listAll.get(position));
                startActivity(intent);
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


    @OnClick({R.id.lin_back, R.id.tv_right, R.id.tv_time, R.id.tv_username})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_right:
                tvUsername.setText(null);
                tvTime.setText(null);
                //加载数据
                reList.startRefresh();
                break;
            //选择负责人
            case R.id.tv_username:
                setClass(SelectUserActivity.class,400);
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
        getEntryBonus();
    }
    @Override
    public void onLoadMore(View view) {

    }


    /**
     * 录入客户奖金统计
     */
    private void getEntryBonus(){
        HttpMethod.getEntryBonus((String)tvUsername.getTag(),tvTime.getText().toString().trim(), new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                EntryBonus entryBonus= (EntryBonus) object;
                if(entryBonus.isSussess()){
                    listAll.addAll(entryBonus.getData());
                    adapter.notifyDataSetChanged();
                }else{
                    ToastUtil.showLong(entryBonus.getMsg());
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
