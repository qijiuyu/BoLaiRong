package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.production.SelectMaterialInventoryActivity;
import com.bian.dan.blr.activity.main.sales.SelectMaterialActivity;
import com.bian.dan.blr.persenter.warehouse.AddMaterial_ledTable_P;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.bean.MaterialInventory;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加请领表---添加物料
 */
public class AddMaterial_LedTable_Activity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_material_type)
    TextView tvMaterialType;
    @BindView(R.id.tv_spec)
    TextView tvSpec;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.et_batchNo)
    EditText etBatchNo;
    @BindView(R.id.tv_stock)
    TextView tvStock;
    @BindView(R.id.tv_stockType)
    TextView tvStockType;
    private AddMaterial_ledTable_P addMaterial_ledTable_p;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material_ledtable);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("添加产品");
        addMaterial_ledTable_p=new AddMaterial_ledTable_P(this);

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

    @OnClick({R.id.lin_back, R.id.tv_type, R.id.tv_name,R.id.tv_stock, R.id.tv_stockType, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择领取形式
            case R.id.tv_type:
                addMaterial_ledTable_p.selectText(tvType,1);
                break;
            //选择物料名称
            case R.id.tv_name:
                if (TextUtils.isEmpty(tvType.getText().toString().trim())) {
                    ToastUtil.showLong("请选择领取形式");
                    return;
                }
                final int type = (int) tvType.getTag();
                if (type == 1) {
                    setClass(SelectMaterialInventoryActivity.class, 100);
                } else {
                    setClass(SelectMaterialActivity.class, 200);
                }
                break;
           //选择仓库
            case R.id.tv_stock:
                addMaterial_ledTable_p.selectText(tvStock,2);
                break;
            //选择仓库类型
            case R.id.tv_stockType:
                if(TextUtils.isEmpty(tvStock.getText().toString().trim())){
                    ToastUtil.showLong("请先选择仓库");
                    return;
                }
                addMaterial_ledTable_p.getDict((int)tvStock.getTag(),tvStockType);
                break;
            case R.id.tv_submit:
                break;
            default:
                break;
        }
    }


    private Material.ListBean listBean;//物料对象
    private MaterialInventory.ListBean inventory;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode==100 && resultCode == 100) {
            inventory = (MaterialInventory.ListBean) data.getSerializableExtra("listBean");
            if(inventory==null){
                return;
            }
            tvName.setText(inventory.getGoodsName());
            tvSpec.setText(inventory.getSpec());
            tvUnit.setText(inventory.getUnitStr());
            tvMaterialType.setText(inventory.getTypeStr());
            etBatchNo.setText(inventory.getBatchNo());
            tvStockType.setText(inventory.getStockTypeStr());
            tvStockType.setTag(inventory.getStockType());
        }
        if (requestCode==200 && resultCode == 100) {
            listBean = (Material.ListBean) data.getSerializableExtra("listBean");
            if (listBean == null) {
                return;
            }
            tvName.setText(listBean.getName());
            tvSpec.setText(listBean.getSpec());
            tvUnit.setText(listBean.getUnitStr());
            tvMaterialType.setText(listBean.getTypeStr());
        }
    }

    /**
     * 根据选择的领取形式，来设置某些输入框是否可以编辑
     */
    public void editEditText(int type) {
        Drawable drawable=getResources().getDrawable(R.mipmap.next);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (type == 1) {
            etBatchNo.setEnabled(false);
            etBatchNo.setHint(null);

            tvStock.setClickable(false);
            tvStock.setHint(null);
            tvStock.setText(null);
            tvStock.setCompoundDrawables(null, null, null,null);

            tvStockType.setClickable(false);
            tvStockType.setHint(null);
            tvStockType.setText(null);
            tvStockType.setCompoundDrawables(null, null, null,null);
        }else{
            etBatchNo.setEnabled(true);
            etBatchNo.setHint("请输入");

            tvStock.setClickable(true);
            tvStock.setHint("请选择");
            tvStock.setText(null);
            tvStock.setCompoundDrawables(null, null, drawable,null);

            tvStockType.setClickable(true);
            tvStockType.setHint("请选择");
            tvStockType.setText(null);
            tvStockType.setCompoundDrawables(null, null, drawable,null);
        }
        etNum.setText(null);

    }
}
