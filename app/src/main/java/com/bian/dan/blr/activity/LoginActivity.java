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
import android.widget.RelativeLayout;

import com.bian.dan.blr.R;
import com.bian.dan.blr.view.AccountPopWindow;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.parameter.LoginP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.rel_account)
    RelativeLayout relAccount;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.img_clear_account)
    ImageView imgClearAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.img_look)
    ImageView imgLook;
    private AccountPopWindow accountPopWindow;
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
        /**
         * 监听获得焦点
         */
        etAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                   //展示账号历史
                    showAccount();
                }else{
                   //关闭账号历史
                    closeAccount();
                }
            }
        });


        /**
         * 监听输入框内容
         */
        etAccount.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    imgClearAccount.setVisibility(View.VISIBLE);

                    //关闭账号历史
                    closeAccount();
                }else{
                    imgClearAccount.setVisibility(View.GONE);

                    //展示账号历史
                    showAccount();
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
                etPwd.setText(null);
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
                    //存储最新账号信息
                    SPUtil.getInstance(activity).addObject(SPUtil.ACCOUNT,loginP);

                    //存储所有账号和密码
                    String allAccount = SPUtil.getInstance(activity).getString(SPUtil.ALL_ACCOUNT);
                    Map<String, String> keyMap;
                    if (!TextUtils.isEmpty(allAccount)) {
                        keyMap = SPUtil.gson.fromJson(allAccount, Map.class);
                    } else {
                        keyMap = new HashMap<>();
                    }
                    keyMap.put(loginP.getUsername(),loginP.getPwd());
                    SPUtil.getInstance(activity).addString(SPUtil.ALL_ACCOUNT, SPUtil.gson.toJson(keyMap));

                    setClass(TabActivity.class);
                    finish();

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


    /**
     * 展示账号历史
     */
    private void showAccount(){
        if(accountPopWindow!=null && accountPopWindow.isShowing()){
            return;
        }
        final String allAccount= SPUtil.getInstance(activity).getString(SPUtil.ALL_ACCOUNT);
        if(TextUtils.isEmpty(allAccount)) {
            return;
        }
        accountPopWindow = new AccountPopWindow(activity);
        accountPopWindow.showAsDropDown(relAccount);
        accountPopWindow.setData(etAccount,etPwd,allAccount);
        accountPopWindow.openShow();
    }


    /**
     * 关闭账号历史
     */
    private void closeAccount(){
        if (accountPopWindow != null && accountPopWindow.isShowing()) {
            accountPopWindow.closeShow();
            accountPopWindow=null;
        }
    }
}
