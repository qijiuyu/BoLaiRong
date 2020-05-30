package com.bian.dan.blr.activity.main.production;

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
import com.bian.dan.blr.adapter.production.OutBoundProductListAdapter;
import com.bian.dan.blr.application.MyApplication;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.OutBoundProduct;
import com.zxdc.utils.library.bean.ProductPlan;
import com.zxdc.utils.library.bean.UserInfo;
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
 * 出库单列表
 */
public class OutBoundProductActivity extends BaseActivity  implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //页码
    private int page=1;
    private List<OutBoundProduct.ListBean> listAll=new ArrayList<>();
    private OutBoundProductListAdapter outBoundProductListAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outbound_product);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("出库单");
        reList.setMyRefreshLayoutListener(this);
        outBoundProductListAdapter=new OutBoundProductListAdapter(this,listAll);
        listView.setAdapter(outBoundProductListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity,ProductProgressDetailsActivity.class);
                intent.putExtra("requireId",listAll.get(position).getId());
                startActivity(intent);
            }
        });


        /**
         * 监听输入框
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
                    imgClear.setVisibility(View.GONE);
                }
                //加载数据
                reList.startRefresh();
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_key, R.id.img_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                break;
            //选择生成计划
            case R.id.tv_key:
                setClass(SelectPlanCodeActivity.class,600);
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
        getOutBoundProductList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getOutBoundProductList();
    }


    /**
     * 根据部门id查询出库单列表
     */
    private void getOutBoundProductList(){
        UserInfo userInfo= MyApplication.getUser();
        HttpMethod.getOutBoundProductList(tvKey.getText().toString().trim(), userInfo.getUser().getDeptId()+"", null, null, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                OutBoundProduct outBoundProduct= (OutBoundProduct) object;
                if(outBoundProduct.isSussess()){
                    List<OutBoundProduct.ListBean> list=outBoundProduct.getData().getRows();
                    listAll.addAll(list);
                    outBoundProductListAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(outBoundProduct.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==600 && data!=null){
            ProductPlan.ListBean listBean= (ProductPlan.ListBean) data.getSerializableExtra("listBean");
            if(listBean!=null){
                tvKey.setText(listBean.getPlanCode());
            }
        }
    }
}
