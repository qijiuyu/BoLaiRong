package com.bian.dan.blr.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.parameter.LoginP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.img_clear_account)
    ImageView imgClearAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.img_look)
    ImageView imgLook;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!TextUtils.isEmpty(SPUtil.getInstance(activity).getString(SPUtil.TOKEN))){
            setClass(TabActivity.class);
            return;
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        etAccount.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    imgClearAccount.setVisibility(View.VISIBLE);
                }else{
                    imgClearAccount.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.img_clear_account, R.id.img_look, R.id.img_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //清空账号
            case R.id.img_clear_account:
                etAccount.setText(null);
                break;
            case R.id.img_look:
                 if(view.getTag().equals("0")){
                     view.setTag("1");
                     imgLook.setImageResource(R.mipmap.yes_look);
                     etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                 }else{
                     view.setTag("0");
                     imgLook.setImageResource(R.mipmap.no_look);
                     etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                 }
                break;
            //登录
            case R.id.img_login:
                String userName=etAccount.getText().toString().trim();
                String pwd=etPwd.getText().toString().trim();
                if(TextUtils.isEmpty(userName)){
                    ToastUtil.showLong("请输入账号");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showLong("请输入密码");
                    return;
                }
                LoginP loginP=new LoginP(userName,pwd);
                login(loginP);
                break;
            default:
                break;
        }
    }


    /**
     * 登录
     */
    private void login(final LoginP loginP){
        DialogUtil.showProgress(this,"登录中");
        HttpMethod.login(loginP, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                DialogUtil.closeProgress();
                UserInfo userInfo= (UserInfo) object;
                if(userInfo==null){
                    return;
                }
                if(userInfo.isSussess()){
                    //存储token
                    SPUtil.getInstance(activity).addString(SPUtil.TOKEN,userInfo.getToken());
                    //存储用户信息
                    SPUtil.getInstance(activity).addObject(SPUtil.USER_INFO,userInfo);
                    //存储账号和密码
                    SPUtil.getInstance(activity).addObject(SPUtil.ACCOUNT,loginP);
                    setClass(TabActivity.class);

                }else{
                    ToastUtil.showLong(userInfo.getMsg());
                }
            }
            public void onFail(Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }
}
