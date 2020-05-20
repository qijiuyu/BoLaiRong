package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.ProductPlanAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.ProductPlan;
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
 * 生产计划
 *
 * 权限管理如下，根据部门id区分：
 * 3：配料部
 * 4：压块部
 * 5：合成部
 * 6：平面磨
 * 7：周边磨
 * 8：焊接
 * 9：切刀粒
 */
public class ProductionPlanActivity extends BaseActivity implements MyRefreshLayoutListener{

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_key)
    EditText evKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private ProductPlanAdapter productPlanAdapter;
    //页码
    private int page=1;
    private List<ProductPlan.ListBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_plan);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("生产计划");
        reList.setMyRefreshLayoutListener(this);
        productPlanAdapter=new ProductPlanAdapter(this,listAll);
        listView.setAdapter(productPlanAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity, ProductPlanDetailsActivity.class);
                intent.putExtra("listBean",listAll.get(position));
                startActivity(intent);
            }
        });

        /**
         * 监听客户名称输入框
         */
        evKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    imgClear.setVisibility(View.VISIBLE);
                }else{
                    evKey.setTag(null);
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
    @OnClick({R.id.lin_back,R.id.img_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.img_clear:
                 evKey.setText(null);
                 break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getPlanList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getPlanList();
    }


    /**
     * 获取已审核通过的生产计划列表
     */
    private void getPlanList(){
        HttpMethod.getPlanList(evKey.getText().toString().trim(),"1", page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                ProductPlan productPlan= (ProductPlan) object;
                if(productPlan.isSussess()){
                    List<ProductPlan.ListBean> list=productPlan.getData().getRows();
                    listAll.addAll(list);
                    productPlanAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }

                }else{
                    ToastUtil.showLong(productPlan.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


}
