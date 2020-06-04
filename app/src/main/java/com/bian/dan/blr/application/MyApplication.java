package com.bian.dan.blr.application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.zxdc.utils.library.base.BaseApplication;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.ActivitysLifecycle;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.error.CockroachUtil;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);

        //开启小强
        CockroachUtil.install();

        //初始化百度地图
        initMap();

        //初始化消息推送
        initPush();

        //管理Activity
        registerActivityLifecycleCallbacks(ActivitysLifecycle.getInstance());
    }


    /**
     * 初始化百度地图
     */
    private void initMap(){
        //初始化地图
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }


    /**
     * 初始化消息推送
     */
    private void initPush(){
        //设置开启日志,发布时请关闭日志
        JPushInterface.setDebugMode(true);
        //初始化 JPush
        JPushInterface.init(this);
    }

    /**
     * 获取用户信息
     * @return
     */
    public static UserInfo getUser(){
        return (UserInfo) SPUtil.getInstance(getContext()).getObject(SPUtil.USER_INFO,UserInfo.class);
    }


    /**
     * 获取角色id
     * @return
     */
    public static int getRoleId(){
        return getUser().getUser().getRoleId();
    }
}
