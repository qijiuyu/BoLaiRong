package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.SelectMaterialActivity;
import com.bian.dan.blr.activity.main.sales.SelectUserActivity;
import com.bian.dan.blr.persenter.product.AddWastePersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Dept;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.bean.UserList;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 增加多余废料
 */
public class AddWasteActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_depart)
    TextView tvDepart;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.et_batchNo)
    EditText etBatchNo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_spec)
    TextView tvSpec;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_stock)
    TextView tvStock;
    @BindView(R.id.tv_stockType)
    TextView tvStockType;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.et_remark)
    EditText etRemark;
    private AddWastePersenter addWastePersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_waste);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("余废料添加产品");
        addWastePersenter=new AddWastePersenter(this);

        /**
         * 监听部门输入
         */
        tvDepart.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                tvPeople.setText(null);
            }
        });

        /**
         * 监听仓库输入
         */
        tvStock.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
              tvStockType.setText(null);
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_type, R.id.tv_depart, R.id.tv_people, R.id.tv_name, R.id.tv_stock, R.id.tv_stockType, R.id.tv_submit})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
           //选择类别
            case R.id.tv_type:
                addWastePersenter.selectType(tvType,1);
                break;
            //选择部门
            case R.id.tv_depart:
                intent.setClass(this,SelectDeptActivity.class);
                intent.putExtra("parendId","2");
                startActivityForResult(intent,500);
                break;
            //根据部门id选择责任人
            case R.id.tv_people:
                if(TextUtils.isEmpty(tvDepart.getText().toString().trim())){
                    ToastUtil.showLong("请先选择责任部门");
                    return;
                }
                intent.setClass(this, SelectUserActivity.class);
                intent.putExtra("deptId",(int)tvDepart.getTag());
                startActivityForResult(intent,400);
                break;
            //选择物料
            case R.id.tv_name:
                setClass(SelectMaterialActivity.class,100);
                break;
            //选择仓库
            case R.id.tv_stock:
                addWastePersenter.selectType(tvStock,2);
                break;
            //选择仓库类型
            case R.id.tv_stockType:
                if(TextUtils.isEmpty(tvStock.getText().toString().trim())){
                    ToastUtil.showLong("请先选择仓库");
                    return;
                }
                addWastePersenter.getDict((int)tvStock.getTag(),tvStockType);
                break;
            //提交
            case R.id.tv_submit:
                String type=tvType.getText().toString().trim();
                String dept=tvDepart.getText().toString().trim();
                String peple=tvPeople.getText().toString().trim();
                String batchNo=etBatchNo.getText().toString().trim();
                String name=tvName.getText().toString().trim();
                String stock=tvStock.getText().toString().trim();
                String stockType=tvStockType.getText().toString().trim();
                String num=etNum.getText().toString().trim();
                String remark=etRemark.getText().toString().trim();
                if(TextUtils.isEmpty(type)){
                    ToastUtil.showLong("请选择类别");
                    return;
                }
                if(TextUtils.isEmpty(dept)){
                    ToastUtil.showLong("请选择责任部门");
                    return;
                }
                if(TextUtils.isEmpty(batchNo)){
                    ToastUtil.showLong("请输入批次");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请选择物料名称");
                    return;
                }
                if(TextUtils.isEmpty(stock)){
                    ToastUtil.showLong("请选择仓库");
                    return;
                }
                if(TextUtils.isEmpty(stockType)){
                    ToastUtil.showLong("请选择仓库类别");
                    return;
                }
                if(TextUtils.isEmpty(num)){
                    ToastUtil.showLong("请输入数量");
                    return;
                }
                Goods goods=new Goods(listBean.getId(),listBean.getName(),listBean.getSpec(),listBean.getUnitStr(),listBean.getBrand(),(int)tvType.getTag(),type,Integer.parseInt(num),(int)tvStockType.getTag(),batchNo,(int)tvDepart.getTag(),dept,(int)tvPeople.getTag());
                intent.putExtra("goods",goods);
                setResult(400,intent);
                finish();
                break;
            default:
                break;
        }
    }



    Material.ListBean listBean;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        switch (resultCode){
            //返回部门信息
            case 500:
                 Dept.DeptBean deptBean= (Dept.DeptBean) data.getSerializableExtra("deptBean");
                 if(deptBean!=null){
                     tvDepart.setText(deptBean.getName());
                     tvDepart.setTag(deptBean.getId());
                 }
                 break;
            //返回责任人信息
            case 400:
                UserList.ListBean userBean = (UserList.ListBean) data.getSerializableExtra("listBean");
                if (userBean != null) {
                    tvPeople.setText(userBean.getName());
                    tvPeople.setTag(userBean.getUserId());
                }
                 break;
            //返回物料数据
            case 100:
                listBean= (Material.ListBean) data.getSerializableExtra("listBean");
                if(listBean!=null){
                    tvName.setText(listBean.getName());
                    tvSpec.setText(listBean.getBrand()+"/"+listBean.getSpec());
                    tvUnit.setText(listBean.getUnitStr());
                }
                 break;
            default:
                break;
        }
    }
}