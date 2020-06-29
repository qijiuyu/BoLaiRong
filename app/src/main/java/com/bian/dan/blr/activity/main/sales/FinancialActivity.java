package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.financial.FinancialDetailsActivity2;
import com.bian.dan.blr.adapter.sales.DialogSelectAdapter;
import com.bian.dan.blr.adapter.sales.FinancialAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Financial;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 财务报销
 */
public class FinancialActivity extends BaseActivity  implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private FinancialAdapter financialAdapter;
    //页码
    private int page=1;
    private List<Financial.ListBean> listAll=new ArrayList<>();
    /**
     *0：未审批
     *1：审批通过
     * 2：审批未通过
     * 3：全部状态
     */
    private int state=3;
    private String[] str=new String[]{"未审批","审批通过","审批未通过","全部状态"};
    /**
     * 0：其他模块进入
     * 1：财务模块进入
     */
    private int type;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("财务报销");
        imgRight.setImageResource(R.mipmap.add);
        type=getIntent().getIntExtra("type",0);
        reList.setMyRefreshLayoutListener(this);
        financialAdapter=new FinancialAdapter(this,listAll);
        listView.setAdapter(financialAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Financial.ListBean listBean=listAll.get(position);
                Intent intent=new Intent();
                if(type==0){
                    intent.setClass(activity,FinancialDetailsActivity.class);
                }else{
                    intent.setClass(activity, FinancialDetailsActivity2.class);
                }
                intent.putExtra("listBean",listBean);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_status,R.id.img_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
           //切换状态
            case R.id.tv_status:
                showSelectType();
                break;
            //添加财务报销
            case R.id.img_right:
                setClass(AddFinancialActivity.class,1000);
                 break;
            default:
                break;
        }
    }


    /**
     * 切换状态
     */
    private void showSelectType(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_select, null);
        final PopupWindow popupWindow = DialogUtil.showPopWindow(view);
        popupWindow.showAsDropDown(tvStatus);
        ListView listView = view.findViewById(R.id.listView);
        final DialogSelectAdapter dialogSelectAdapter=new DialogSelectAdapter(this,str,state);
        listView.setAdapter(dialogSelectAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                state=position;
                tvStatus.setText(str[state]);
                dialogSelectAdapter.onclick(state);
                //加载数据
                reList.startRefresh();
            }
        });
    }


    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getFinancialList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getFinancialList();
    }


    /**
     * 获取财务列表
     */
    private void getFinancialList(){
        HttpMethod.getFinancialList(state==3 ? null : String.valueOf(state), page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Financial financial= (Financial) object;
                if(financial.isSussess()){
                    List<Financial.ListBean> list=financial.getData().getRows();
                    listAll.addAll(list);
                    financialAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(financial.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            //重新刷新列表
            reList.startRefresh();
        }
    }
}
