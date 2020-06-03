package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.production.SelectDeptActivity;
import com.bian.dan.blr.adapter.warehouse.LedTableAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Dept;
import com.zxdc.utils.library.bean.LedTable;
import com.zxdc.utils.library.bean.NetWorkCallBack;
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
 * 请领表
 */
public class LedTableActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private List<LedTable.ListBean> listAll=new ArrayList<>();
    private LedTableAdapter ledTableAdapter;
    //页码
    private int page=1;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledtable);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("请领表");
        imgRight.setImageResource(R.mipmap.add);
        reList.setMyRefreshLayoutListener(this);

        ledTableAdapter=new LedTableAdapter(this,listAll);
        listView.setAdapter(ledTableAdapter);

        /**
         * 监听关键字搜索
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

    @OnClick({R.id.lin_back, R.id.img_right,R.id.tv_key, R.id.img_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.img_right:
                setClass(AddLedTableActivity.class,1000);
                 break;
            //选择部门
            case R.id.tv_key:
                setClass(SelectDeptActivity.class,500);
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
        getLedTable();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getLedTable();
    }


    /**
     * 查询请领表
     */
    private void getLedTable(){
        HttpMethod.getLedTable((String) tvKey.getTag(), page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                LedTable ledTable= (LedTable) object;
                if(ledTable.isSussess()){
                    List<LedTable.ListBean> list=ledTable.getData().getRows();
                    listAll.addAll(list);
                    ledTableAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(ledTable.getMsg());
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
            //选择选择部门回执
            case 500:
                if(data!=null){
                    Dept.DeptBean deptBean= (Dept.DeptBean) data.getSerializableExtra("deptBean");
                    if(deptBean!=null){
                        tvKey.setTag(String.valueOf(deptBean.getId()));
                        tvKey.setText(deptBean.getName());
                    }
                }
                break;
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
