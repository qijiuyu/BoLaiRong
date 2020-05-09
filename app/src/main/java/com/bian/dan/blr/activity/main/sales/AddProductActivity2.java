package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.persenter.sales.AddProductPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加产品
 */
public class AddProductActivity2 extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_brand)
    TextView tvBrand;
    @BindView(R.id.tv_spec)
    TextView tvSpace;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.et_remark)
    EditText etRemark;
    private AddProductPersenter addProductPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product2);
        ButterKnife.bind(this);
        initView();
    }


    private void initView(){
        addProductPersenter=new AddProductPersenter(this);
        tvHead.setText("添加产品");
    }


    /**
     * 按钮点击事件
     * @param view
     */
    @OnClick({R.id.lin_back,R.id.tv_name,R.id.tv_brand,R.id.tv_spec,R.id.tv_submit})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择物料名称
            case R.id.tv_name:
                intent.setClass(this,SelectMaterialActivity.class);
                startActivityForResult(intent,100);
                break;
            case R.id.tv_submit:
                  String name=tvName.getText().toString().trim();
                  String num=etNum.getText().toString().trim();
                  String remark=etRemark.getText().toString().trim();
                  if(TextUtils.isEmpty(name)){
                      ToastUtil.showLong("请选择物料名称");
                      return;
                  }
                 if(TextUtils.isEmpty(num)){
                     ToastUtil.showLong("请输入数量");
                     return;
                 }
                 if(TextUtils.isEmpty(remark)){
                     ToastUtil.showLong("请输入备注");
                     return;
                 }
                 Goods goods=new Goods(listBean.getId(),listBean.getName(),listBean.getSpec(),listBean.getBrand(),Integer.parseInt(num),remark);
                 intent.putExtra("goods",goods);
                 setResult(200,intent);
                 finish();
                  break;
            default:
                break;
        }
    }


    private Material.ListBean listBean;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && data!=null){
            listBean= (Material.ListBean) data.getSerializableExtra("listBean");
            if(listBean==null){
                return;
            }
            tvName.setText(listBean.getName());
            tvBrand.setText(listBean.getBrand());
            tvSpace.setText(listBean.getSpec());
            tvUnit.setText(listBean.getUnitStr());
        }
    }
}
