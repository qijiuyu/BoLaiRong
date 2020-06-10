package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.production.AddOutBoundByProductAdapter;
import com.bian.dan.blr.application.MyApplication;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.ProductPlan;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.parameter.AddOutBoundByProductP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增出库申请
 */
public class AddOutBoundByProductActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    MeasureListView listView;
    //生产计划对象
    private ProductPlan.ListBean listBean;
    private AddOutBoundByProductAdapter addOutBoundByProductAdapter;
    private List<Goods> goodList=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_outbound_by_product);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增出库申请");
        listBean= (ProductPlan.ListBean) getIntent().getSerializableExtra("listBean");
        addOutBoundByProductAdapter=new AddOutBoundByProductAdapter(this,goodList);
        listView.setAdapter(addOutBoundByProductAdapter);
    }

    @OnClick({R.id.lin_back, R.id.tv_product, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //添加产品列表
            case R.id.tv_product:
                setClass(AddGoodsByProductionActivity.class,200);
                break;
            //提交
            case R.id.tv_submit:
                if(listBean==null){
                    return;
                }
                if(goodList.size()==0){
                    ToastUtil.showLong("请添加出库产品列表");
                    return;
                }
                AddOutBoundByProductP addOutBoundByProductP=new AddOutBoundByProductP();
                addOutBoundByProductP.setPlanId(listBean.getId());
                //获取用户所在部门id
                UserInfo userInfo= MyApplication.getUser();
                addOutBoundByProductP.setDeptId(userInfo.getUser().getDeptId());
                //产品列表
                List<AddOutBoundByProductP.GoodList> list=new ArrayList<>();
                for (int i=0;i<goodList.size();i++){
                    AddOutBoundByProductP.GoodList good=new AddOutBoundByProductP.GoodList(goodList.get(i).getGoodId(),goodList.get(i).getNum(),goodList.get(i).getStockType(),goodList.get(i).getBatchNo(),goodList.get(i).getDeliveryTime(),goodList.get(i).getMemo());
                    list.add(good);
                }
                addOutBoundByProductP.setRequireDetailList(list);
                //生产-申请出库-新增
                LogUtils.e("+++++++"+ SPUtil.gson.toJson(addOutBoundByProductP));
                addOutBoundByProduct(addOutBoundByProductP);
                break;
            default:
                break;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==200){
            if(data!=null){
                Goods goods= (Goods) data.getSerializableExtra("goods");
                if(goods!=null){
                    goodList.add(goods);
                    addOutBoundByProductAdapter.notifyDataSetChanged();
                }
            }
        }
    }


    /**
     * 生产-申请出库-新增
     */
    private void addOutBoundByProduct(AddOutBoundByProductP addOutBoundByProductP){
        DialogUtil.showProgress(this,"提交中");
        HttpMethod.addOutBoundByProduct(addOutBoundByProductP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    Intent intent=new Intent();
                    setResult(1000,intent);
                    finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
