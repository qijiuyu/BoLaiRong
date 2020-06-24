package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.SelectMaterialActivity;
import com.bian.dan.blr.persenter.product.AddStoragePersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.bean.ProductProgress;
import com.zxdc.utils.library.bean.parameter.UpdateEntryGoodP;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择入库的产品
 */
public class AddStorageProductActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_batchNo)
    EditText etBatchNo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_spec)
    TextView tvSpec;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_stockType)
    TextView tvStockType;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.et_reward_money)
    EditText etRewardMoney;
    @BindView(R.id.et_reward_des)
    EditText etRewardDes;
    @BindView(R.id.et_fine_money)
    EditText etFineMoney;
    @BindView(R.id.et_fine_des)
    EditText etFineDes;
    @BindView(R.id.et_remark)
    EditText etRemark;
    private AddStoragePersenter addStoragePersenter;
    //要编辑的对象
    private ProductProgress.EntryList entryList;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_put_storage_product);
        ButterKnife.bind(this);
        initView();
        //显示要编辑的内容
        showEidtData();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("添加产品");
        addStoragePersenter=new AddStoragePersenter(this);
        entryList= (ProductProgress.EntryList) getIntent().getSerializableExtra("entryList");
    }

    @OnClick({R.id.lin_back, R.id.tv_name,R.id.tv_stockType,R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //选择物料
            case R.id.tv_name:
                setClass(SelectMaterialActivity.class,100);
                break;
            //选择仓库
            case R.id.tv_stockType:
                 addStoragePersenter.getStockList(tvStockType);
                 break;
            //提交
            case R.id.tv_submit:
                String batchNo=etBatchNo.getText().toString().trim();
                String name=tvName.getText().toString().trim();
                String stockType=tvStockType.getText().toString().trim();
                String num=etNum.getText().toString().trim();
                String rewardMoney=etRewardMoney.getText().toString().trim();
                String rewardDes=etRewardDes.getText().toString().trim();
                String fineMoney=etFineMoney.getText().toString().trim();
                String fineDes=etFineDes.getText().toString().trim();
                String remark=etRemark.getText().toString().trim();
                if(TextUtils.isEmpty(batchNo)){
                    ToastUtil.showLong("请输入批次");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请选择物料名称");
                    return;
                }
                if(TextUtils.isEmpty(stockType)){
                    ToastUtil.showLong("请选择仓库类型");
                    return;
                }
                if(TextUtils.isEmpty(num)){
                    ToastUtil.showLong("请输入数量");
                    return;
                }
                if(Integer.parseInt(num)==0){
                    ToastUtil.showLong("数量不能为0");
                    return;
                }
                Intent intent=new Intent();
                if(entryList==null){
                    Goods goods=new Goods(listBean.getId(),listBean.getName(),listBean.getSpec(),listBean.getUnitStr(),listBean.getBrand(),listBean.getTypeStr(),Integer.parseInt(num),remark,(int)tvStockType.getTag(),stockType,batchNo,rewardMoney,rewardDes,fineMoney,fineDes);
                    intent.putExtra("goods",goods);
                }else{
                    UpdateEntryGoodP updateEntryGoodP=new UpdateEntryGoodP(entryList.getId(),Integer.parseInt(num),(int)tvStockType.getTag(),batchNo,remark);
                    intent.putExtra("goods",updateEntryGoodP);
                }
                 setResult(200,intent);
                 finish();
                break;
            default:
                break;
        }
    }


    /**
     * 显示要编辑的内容
     */
    private void showEidtData(){
        if(entryList==null){
            return;
        }
        tvHead.setText("编辑产品");
        etBatchNo.setText(entryList.getBatchNo());
        tvName.setText(entryList.getGoodsName());
        tvName.setClickable(false);
        tvSpec.setText(entryList.getSpec());
        tvUnit.setText(entryList.getUnitStr());
        tvStockType.setTag(entryList.getStockType());
        tvStockType.setText(entryList.getParentStockTypeStr()+"-"+entryList.getStockTypeStr());
        etNum.setText(String.valueOf(entryList.getNum()));
        etRemark.setText(entryList.getMemo());
    }


    Material.ListBean listBean;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && data!=null){
            listBean= (Material.ListBean) data.getSerializableExtra("listBean");
            if(listBean!=null){
                tvName.setText(listBean.getName());
                tvSpec.setText(listBean.getSpec());
                tvUnit.setText(listBean.getUnitStr());
            }
        }
    }
}
