package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.warehouse.SdEnterAdapter;
import com.bian.dan.blr.persenter.sales.SdEnterPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SdEnter;
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
 * 手动入库单
 */
public class SdEnterActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    //页码
    private int page = 1;
    private SdEnterAdapter sdEnterAdapter;
    private SdEnterPersenter sdEnterPersenter;
    private List<SdEnter.ListBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd_enter);
        ButterKnife.bind(this);
        initView();
        //获取手动入库列表
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("手动入库单");
        sdEnterPersenter=new SdEnterPersenter(this);
        imgRight.setImageResource(R.mipmap.add);
        reList.setMyRefreshLayoutListener(this);
        listView.setAdapter(sdEnterAdapter = new SdEnterAdapter(this,listAll));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(SdEnterDetailsActivity.class);
            }
        });

        /**
         * 监听开始日期
         */
        tvStartTime.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0 && !TextUtils.isEmpty(tvEndTime.getText().toString())){
                    //获取手动入库列表
                    reList.startRefresh();
                }
            }
        });

        /**
         * 监听结束日期
         */
        tvEndTime.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0 && !TextUtils.isEmpty(tvStartTime.getText().toString())){
                    //获取手动入库列表
                    reList.startRefresh();
                }
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.img_right,R.id.tv_start_time, R.id.tv_end_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.img_right:
                setClass(AddSdEnterActivity.class,1000);
                break;
            //选择采购开始日期
            case R.id.tv_start_time:
                sdEnterPersenter.selectStartTime(tvStartTime,tvEndTime);
                break;
            //选择采购结束日期
            case R.id.tv_end_time:
                sdEnterPersenter.selectEndTime(tvStartTime,tvEndTime);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getSdEnterList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getSdEnterList();
    }


    /**
     * 获取手动入库列表
     */
    private void getSdEnterList(){
        HttpMethod.getSdEnterList(tvStartTime.getText().toString().trim(), tvEndTime.getText().toString().trim(), page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                SdEnter sdEnter= (SdEnter) object;
                if(sdEnter.isSussess()){
                    List<SdEnter.ListBean> list=sdEnter.getData().getRows();
                    listAll.addAll(list);
                    sdEnterAdapter.notifyDataSetChanged();
                    if(list.size()<HttpMethod.limit){
                        reList.setIsLoadingMoreEnabled(false);
                    }

                }else{
                    ToastUtil.showLong(sdEnter.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
