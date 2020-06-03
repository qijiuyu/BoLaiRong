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
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Dept;
import com.zxdc.utils.library.bean.UserList;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

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
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            //选择部门回执
            case 500:
                if(data!=null){
                    Dept.DeptBean deptBean= (Dept.DeptBean) data.getSerializableExtra("deptBean");
                    if(deptBean!=null){
                        tvDepartment.setTag(String.valueOf(deptBean.getId()));
                        tvDepartment.setText(deptBean.getName());
                        tvName.setText(null);
                    }
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
            default:
                break;
        }
    }
}
