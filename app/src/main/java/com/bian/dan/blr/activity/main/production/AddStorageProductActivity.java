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
import com.zxdc.utils.library.bean.UserInfo;
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
    @BindView(R.id.tv_stock)
    TextView tvStock;
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
    //用户对象
    private UserInfo userInfo;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_put_storage_product);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("添加产品");
        addStoragePersenter=new AddStoragePersenter(this);

    }

    @OnClick({R.id.lin_back, R.id.tv_name, R.id.tv_stock,R.id.tv_stockType,R.id.tv_submit})
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
            case R.id.tv_stock:
                 addStoragePersenter.selectType(tvStock,tvStockType);
                 break;
            //选择仓库类型
            case R.id.tv_stockType:
                 if(TextUtils.isEmpty(tvStock.getText().toString().trim())){
                     ToastUtil.showLong("请先选择仓库");
                     return;
                 }
                addStoragePersenter.getDict((int)tvStock.getTag(),tvStockType);
                 break;
            //提交
            case R.id.tv_submit:
                String batchNo=etBatchNo.getText().toString().trim();
                String stock=tvStock.getText().toString().trim();
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
                if(listBean==null){
                    ToastUtil.showLong("请选择物料");
                    return;
                }
                if(TextUtils.isEmpty(stock)){
                    ToastUtil.showLong("请选择仓库");
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
//                if(TextUtils.isEmpty(rewardMoney)){
//                    ToastUtil.showLong("请输入奖励金额");
//                    return;
//                }
//                if(TextUtils.isEmpty(rewardDes)){
//                    ToastUtil.showLong("奖励说明");
//                    return;
//                }
//                if(TextUtils.isEmpty(fineMoney)){
//                    ToastUtil.showLong("罚款金额");
//                    return;
//                }
//                if(TextUtils.isEmpty(fineDes)){
//                    ToastUtil.showLong("罚款说明");
//                    return;
//                }
                Goods goods=new Goods(listBean.getId(),listBean.getName(),listBean.getSpec(),listBean.getUnitStr(),listBean.getBrand(),listBean.getTypeStr(),Integer.parseInt(num),remark,(int)tvStockType.getTag(),batchNo,rewardMoney,rewardDes,fineMoney,fineDes);
                Intent intent=new Intent();
                intent.putExtra("goods",goods);
                 setResult(200,intent);
                 finish();
                break;
            default:
                break;
        }
    }


    Material.ListBean listBean;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && data!=null){
            listBean= (Material.ListBean) data.getSerializableExtra("listBean");
            if(listBean!=null){
                tvName.setText(listBean.getName());
                tvSpec.setText(listBean.getBrand()+"/"+listBean.getSpec());
                tvUnit.setText(listBean.getUnitStr());
            }
        }
    }
}
