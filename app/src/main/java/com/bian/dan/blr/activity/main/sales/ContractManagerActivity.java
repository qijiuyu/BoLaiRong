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
import com.bian.dan.blr.adapter.sales.ConstractManagerAdapter;
import com.bian.dan.blr.application.MyApplication;
import com.bian.dan.blr.persenter.sales.ContractManagerPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Contract;
import com.zxdc.utils.library.bean.ContractCode;
import com.zxdc.utils.library.bean.Customer;
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
 * 合同管理
 */
public class ContractManagerActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    public MyRefreshLayout reList;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.img_close_code)
    ImageView imgCloseCode;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.img_close_name)
    ImageView imgCloseName;
    //页码
    private int page = 1;
    private List<Contract.ListBean> listAll = new ArrayList<>();
    private ConstractManagerAdapter constractManagerAdapter;
    private ContractManagerPersenter contractManagerPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_manager);
        ButterKnife.bind(this);
        initView();
        //设置权限
        setPermissions();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        contractManagerPersenter = new ContractManagerPersenter(this);
        tvHead.setText("合同管理");
        imgRight.setImageResource(R.mipmap.add);
        reList.setMyRefreshLayoutListener(this);
        constractManagerAdapter = new ConstractManagerAdapter(this, listAll);
        listView.setAdapter(constractManagerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, ContractDetailsActivity.class);
                intent.putExtra("listBean", listAll.get(position));
                startActivity(intent);
            }
        });

        /**
         * 监听合同编号输入框
         */
        tvCode.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    imgCloseCode.setVisibility(View.VISIBLE);
                }else{
                    imgCloseCode.setVisibility(View.GONE);
                }
                //加载数据
                reList.startRefresh();
            }
        });

        /**
         * 监听客户名称输入框
         */
        tvName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    imgCloseName.setVisibility(View.VISIBLE);
                }else{
                    tvName.setTag("");
                    imgCloseName.setVisibility(View.GONE);
                }
                //加载数据
                reList.startRefresh();
            }
        });
    }


    /**
     * 设置权限
     */
    private void setPermissions(){
        final UserInfo userInfo= MyApplication.getUser();
        //销售内勤不能录入合同
        if(userInfo.getUser().getRoleId()==6){
            imgRight.setVisibility(View.GONE);
        }
    }


    /**
     * 按钮点击事件
     *
     * @param view
     */
    @OnClick({R.id.lin_back, R.id.img_right,R.id.tv_code,R.id.tv_name,R.id.img_close_code, R.id.img_close_name})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //添加
            case R.id.img_right:
                setClass(AddContractActivity.class, 1000);
                break;
            //选择合同编号
            case R.id.tv_code:
                 intent.setClass(this,SelectContractCodeActivity.class);
                 startActivityForResult(intent,300);
                 break;
           //选择客户名称
            case R.id.tv_name:
                intent.setClass(this,SelectCustomerActivity.class);
                startActivityForResult(intent,100);
                 break;
            case R.id.img_close_code:
                tvCode.setText(null);
                break;
            case R.id.img_close_name:
                tvName.setText(null);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        contractManagerPersenter.getContractList(tvCode, tvName, page);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        contractManagerPersenter.getContractList(tvCode, tvName, page);
    }


    /**
     * 刷新数据
     */
    public void refresh(Object object) {
        reList.refreshComplete();
        reList.loadMoreComplete();
        Contract contract = (Contract) object;
        if (contract.isSussess()) {
            List<Contract.ListBean> list = contract.getData().getRows();
            listAll.addAll(list);
            constractManagerAdapter.notifyDataSetChanged();
            if (list.size() < HttpMethod.limit) {
                reList.setIsLoadingMoreEnabled(false);
            }
        } else {
            ToastUtil.showLong(contract.getMsg());
        }
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
                         tvName.setTag(String.valueOf(customer.getId()));
                         tvName.setText(customer.getCustomerName());
                     }
                 }
                 break;
            //选择合同编号回执
            case 300:
                 if(data!=null){
                    ContractCode.ListBean listBean= (ContractCode.ListBean) data.getSerializableExtra("listBean");
                    if(listBean!=null){
                        tvCode.setText(listBean.getProp2());
                    }
                 }
                 break;
            //新合同增加成功后，重新刷新列表
            case 1000:
                 //加载数据
                 reList.startRefresh();
                 break;
             default:
                 break;
        }
    }
}
