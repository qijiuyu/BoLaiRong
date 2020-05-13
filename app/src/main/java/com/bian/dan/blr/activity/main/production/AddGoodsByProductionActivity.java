package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.application.MyApplication;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.MaterialInventory;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加产品
 * <p>
 * * 权限管理如下，根据部门id区分：
 * * 3：配料部
 * * 4：压块部
 * * 5：合成部
 * * 6：平面磨
 * * 7：周边磨
 * * 8：焊接
 * * 9：切刀粒
 */
public class AddGoodsByProductionActivity extends BaseActivity {

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
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.rel_batchNo)
    RelativeLayout relBatchNo;
    @BindView(R.id.rel_time)
    RelativeLayout relTime;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_good_by_production);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("添加产品");
        /**
         * 根据部门id来区分显示数据(配料部-没有批次和日期)
         */
        final UserInfo userInfo = MyApplication.getUser();
        if (userInfo.getUser().getDeptId() == 3) {
            relBatchNo.setVisibility(View.GONE);
            relTime.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.lin_back, R.id.tv_name, R.id.tv_time, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择物料库存
            case R.id.tv_name:
                setClass(SelectMaterialInventoryActivity.class,100);
                break;
            //选择时间
            case R.id.tv_time:
                break;
            //提交
            case R.id.tv_submit:
                if(listBean==null){
                    ToastUtil.showLong("请选择物料");
                    return;
                }
                break;
            default:
                break;
        }
    }


    private MaterialInventory.ListBean listBean;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && data!=null){
            listBean= (MaterialInventory.ListBean) data.getSerializableExtra("listBean");
            if(listBean==null){
                return;
            }
            tvName.setText(listBean.getGoodsName());
            tvSpec.setText(listBean.getBrand()+"/"+listBean.getSpec());
            tvUnit.setText(listBean.getUnitStr());
            etNum.setText(String.valueOf(listBean.getNum()));
        }
    }
}
