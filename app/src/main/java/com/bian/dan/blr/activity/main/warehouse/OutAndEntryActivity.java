package com.bian.dan.blr.activity.main.warehouse;

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
import com.bian.dan.blr.activity.main.production.SelectPlanCodeActivity;
import com.bian.dan.blr.adapter.warehouse.OutAndEntryAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.OutBoundProduct;
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
 * 出入库列表
 */
public class OutAndEntryActivity extends BaseActivity  implements MyRefreshLayoutListener {

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
    private OutAndEntryAdapter outAndEntryAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_and_entry);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("生产出入库");
        reList.setMyRefreshLayoutListener(this);
        outAndEntryAdapter = new OutAndEntryAdapter(this, listAll);
        listView.setAdapter(outAndEntryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity, OutAndEntry_DetailsActivity.class);
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
                    tvKey.setTag(null);
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
        HttpMethod.getOutBoundProductList((String) tvKey.getTag(), null, null, null, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                OutBoundProduct outBoundProduct= (OutBoundProduct) object;
                if(outBoundProduct.isSussess()){
                    List<OutBoundProduct.ListBean> list=outBoundProduct.getData().getRows();
                    listAll.addAll(list);
                    outAndEntryAdapter.notifyDataSetChanged();
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
                tvKey.setTag(String.valueOf(listBean.getId()));
                tvKey.setText(listBean.getPlanCode());
            }
        }
    }
}
