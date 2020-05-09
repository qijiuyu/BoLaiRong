package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.SelectCustomer;
import com.zxdc.utils.library.bean.parameter.AddLogP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增日志
 */
public class AddLogActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lin_back, R.id.tv_name, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择客户名称
            case R.id.tv_name:
                setClass(SelectCustomerActivity.class, 100);
                break;
            case R.id.tv_submit:
                String name = tvName.getText().toString().trim();
                String remark = etRemark.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.showLong("请选择客户名称");
                    return;
                }
                if (TextUtils.isEmpty(remark)) {
                    ToastUtil.showLong("请输入跟进结果");
                    return;
                }
                AddLogP addLogP = new AddLogP((int) tvName.getTag(), remark);
                //添加日志
                addLog(addLogP);
                break;
            default:
                break;
        }
    }


    /**
     * 添加日志
     */
    private void addLog(AddLogP addLogP) {
        DialogUtil.showProgress(this, "添加中");
        HttpMethod.addLog(addLogP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                BaseBean baseBean = (BaseBean) object;
                if (baseBean.isSussess()) {
                    Intent intent = new Intent();
                    setResult(1000, intent);
                    finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100 && data != null) {
            SelectCustomer.ListBean listBean = (SelectCustomer.ListBean) data.getSerializableExtra("listBean");
            if(listBean==null){
                return;
            }
            tvName.setText(listBean.getCustomerName());
            tvName.setTag(listBean.getId());
            tvPeople.setText(listBean.getContacts());
            tvMobile.setText(listBean.getPhone());
            tvPosition.setText(listBean.getPosition());
            tvWx.setText(listBean.getWechat());
            tvQq.setText(listBean.getQq());
            tvEmail.setText(listBean.getEmail());
            tvAddress.setText(listBean.getPostAddress());
        }
    }
}
