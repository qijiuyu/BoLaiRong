package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.production.SelectDeptActivity;
import com.bian.dan.blr.activity.main.sales.SelectUserActivity;
import com.bian.dan.blr.adapter.warehouse.LedTable_Material_Adapter;
import com.google.gson.Gson;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Dept;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.UserList;
import com.zxdc.utils.library.bean.parameter.AddLedTableP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增请领表
 */
public class AddLedTableActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.listView)
    MeasureListView listView;
    //物料列表
    private List<Goods> goodsList=new ArrayList<>();
    private LedTable_Material_Adapter ledTable_material_adapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ledtable);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增请领表");
        ledTable_material_adapter=new LedTable_Material_Adapter(this,goodsList);
        listView.setAdapter(ledTable_material_adapter);
    }

    @OnClick({R.id.lin_back, R.id.tv_department, R.id.tv_name,R.id.tv_material, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //选择领取部门
            case R.id.tv_department:
                setClass(SelectDeptActivity.class,500);
                break;
            //选择领取人
            case R.id.tv_name:
                if(TextUtils.isEmpty(tvDepartment.getText().toString().trim())){
                    ToastUtil.showLong("请先选择责任部门");
                    return;
                }
                Intent intent=new Intent(this, SelectUserActivity.class);
                intent.putExtra("deptId",tvDepartment.getTag().toString());
                startActivityForResult(intent,400);
                break;
            //添加领取列表
            case R.id.tv_material:
                setClass(AddMaterial_LedTable_Activity.class,200);
                 break;
            case R.id.tv_submit:
                 String department=tvDepartment.getText().toString().trim();
                 String people=tvName.getText().toString().trim();
                 if(TextUtils.isEmpty(department)){
                     ToastUtil.showLong("请选择领取部门");
                     return;
                 }
                if(TextUtils.isEmpty(people)){
                    ToastUtil.showLong("请选择领取人");
                    return;
                }
                if(goodsList.size()==0){
                    ToastUtil.showLong("请添加领取列表");
                    return;
                }
                AddLedTableP addLedTableP=new AddLedTableP();
                addLedTableP.setDeptId((int)tvDepartment.getTag());
                addLedTableP.setReceiveId((int)tvName.getTag());
                List<AddLedTableP.MaterialList> list=new ArrayList<>();
                for (int i=0;i<goodsList.size();i++){
                    AddLedTableP.MaterialList material=new AddLedTableP.MaterialList();
                    material.setGoodsId(goodsList.get(i).getGoodId());
                    material.setStockType(goodsList.get(i).getStockType());
                    material.setBatchNo(goodsList.get(i).getBatchNo());
                    material.setNum(goodsList.get(i).getNum());
                    material.setType(goodsList.get(i).getReceiveType());
                    list.add(material);
                }
                addLedTableP.setDetailList(list);
                LogUtils.e("++++++++++++"+new Gson().toJson(addLedTableP));
                //新增请领表
                addLedTable(addLedTableP);
                break;
            default:
                break;
        }
    }



    /**
     * 新增请领表
     */
    private void addLedTable(AddLedTableP addLedTable){
        DialogUtil.showProgress(this,"提交中");
        HttpMethod.addLedTable(addLedTable, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    setResult(1000,new Intent());
                    finish();
                }else{
                    ToastUtil.showLong(baseBean.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        switch (resultCode){
            //选择部门回执
            case 500:
                Dept.DeptBean deptBean= (Dept.DeptBean) data.getSerializableExtra("deptBean");
                if(deptBean!=null){
                    tvDepartment.setTag(deptBean.getId());
                    tvDepartment.setText(deptBean.getName());
                    tvName.setText(null);
                }
                break;
            //返回领取人信息
            case 400:
                UserList.ListBean userBean = (UserList.ListBean) data.getSerializableExtra("listBean");
                if (userBean != null) {
                    tvName.setTag(userBean.getUserId());
                    tvName.setText(userBean.getName());
                }
                break;
            //返回物料
            case 200:
                 Goods goods= (Goods) data.getSerializableExtra("goods");
                 if(goods!=null){
                     goodsList.add(goods);
                     ledTable_material_adapter.notifyDataSetChanged();
                 }
                 break;
            default:
                break;
        }
    }
}
