package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.application.MyApplication;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.MaterialInventory;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    @BindView(R.id.tv_batchNo)
    TextView tvBatchNo;
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
    @BindView(R.id.rel_time)
    RelativeLayout relTime;
    //用户对象
    private UserInfo userInfo;
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
         * 根据部门id来区分显示数据(配料部-没有日期)
         */
        userInfo = MyApplication.getUser();
        if (userInfo.getUser().getDeptId() == 3) {
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
                 selectTime();
                break;
            //提交
            case R.id.tv_submit:
                String num=etNum.getText().toString().trim();
                String time=tvTime.getText().toString().trim();
                String remark=etRemark.getText().toString().trim();
                if(listBean==null){
                    ToastUtil.showLong("请选择物料");
                    return;
                }
                if(TextUtils.isEmpty(num)){
                    ToastUtil.showLong("请输入数量");
                    return;
                }
                if(Integer.parseInt(num)>listBean.getNum()){
                    ToastUtil.showLong("数量已超过库存，请重新输入");
                    return;
                }
                if(Integer.parseInt(num)==0){
                    ToastUtil.showLong("数量不能是0");
                    return;
                }
                if(userInfo.getUser().getDeptId()!= 3 && TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择日期");
                    return;
                }
                Goods goods=new Goods(listBean.getGoodsId(),listBean.getGoodsName(),listBean.getSpec(),listBean.getUnitStr(),listBean.getBrand(),Integer.parseInt(num),time,remark,listBean.getStockType(),listBean.getStockTypeStr(),listBean.getParentStockTypeStr(),listBean.getBatchNo());
                Intent intent=new Intent();
                intent.putExtra("goods",goods);
                setResult(200,intent);
                finish();
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
            tvBatchNo.setText(listBean.getBatchNo());
            tvSpec.setText(listBean.getSpec());
            tvUnit.setText(listBean.getUnitStr());
            etNum.setText(String.valueOf(listBean.getNum()));
        }
    }


    /**
     * 选择时间
     */
    public void selectTime(){
        CustomDatePicker customDatePicker = new CustomDatePicker(activity, new CustomDatePicker.ResultHandler() {
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTime.setText(time.split(" ")[0]);
            }
        }, "1920-01-01 00:00", "2050-12-31 23:59");
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(true); // 不允许循环滚动

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String now = sdf.format(new Date());
        tvTime.setText(now.split(" ")[0]);
        customDatePicker.show(tvTime.getText().toString());
    }
}
