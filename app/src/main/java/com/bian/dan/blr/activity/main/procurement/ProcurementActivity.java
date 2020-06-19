package com.bian.dan.blr.activity.main.procurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.warehouse.ProcurementDetailsActivity2;
import com.bian.dan.blr.adapter.procurement.ProcurementAdapter;
import com.bian.dan.blr.persenter.procurement.ProcurementPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Procurement;
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
 * 采购单
 */
public class ProcurementActivity extends BaseActivity  implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_right)
    TextView tvRight;
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
    @BindView(R.id.et_key)
    EditText etKey;
    //页码
    private int page = 1;
    /**
     * 1：采购模块进入
     * 2：仓库模块进入
     */
    private int type;
    private ProcurementAdapter procurementAdapter;
    private List<Procurement.ListBean> listAll=new ArrayList<>();
    private ProcurementPersenter procurementPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurement);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("采购单");
        procurementPersenter=new ProcurementPersenter(this);
        type=getIntent().getIntExtra("type",0);
        if(type==1){
            imgRight.setImageResource(R.mipmap.add);
        }else{
            tvRight.setText("重置");
        }
        reList.setMyRefreshLayoutListener(this);
        procurementAdapter=new ProcurementAdapter(this,listAll);
        listView.setAdapter(procurementAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                if(type==1){
                    intent.setClass(activity,ProcurementDetailsActivity.class);
                }else{
                    intent.setClass(activity, ProcurementDetailsActivity2.class);
                }
                intent.putExtra("listBean",listAll.get(position));
                startActivity(intent);
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

        /**
         * 监听客户名称输入框
         */
        etKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                //加载数据
                reList.startRefresh();
            }
        });
    }

    @OnClick({R.id.lin_back,R.id.tv_right, R.id.img_right,R.id.tv_start_time, R.id.tv_end_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //重置
            case R.id.tv_right:
                tvStartTime.setText(null);
                tvEndTime.setText(null);
                etKey.setText(null);
                //加载数据
                reList.startRefresh();
                break;
            //添加
            case R.id.img_right:
                setClass(AddProcurementActivity.class,1000);
                break;
            //选择开始日期
            case R.id.tv_start_time:
                procurementPersenter.selectStartTime(tvStartTime,tvEndTime);
                break;
            //选择结束日期
            case R.id.tv_end_time:
                procurementPersenter.selectEndTime(tvStartTime,tvEndTime);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getProcurementList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getProcurementList();
    }


    /**
     * 获取采购单列表
     */
    private void getProcurementList(){
        HttpMethod.getProcurementList(tvStartTime.getText().toString(),tvEndTime.getText().toString(),etKey.getText().toString().trim(),page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Procurement procurement= (Procurement) object;
                if(procurement.isSussess()){
                    List<Procurement.ListBean> list=procurement.getData().getRows();
                    listAll.addAll(list);
                    procurementAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(procurement.getMsg());
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
            //增加成功后，重新刷新列表
            case 1000:
                //加载数据
                reList.startRefresh();
                break;
            default:
                break;
        }
    }
}
