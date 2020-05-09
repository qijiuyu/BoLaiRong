package com.bian.dan.blr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.audit.AuditActivity;
import com.bian.dan.blr.activity.main.MainActivity;
import com.bian.dan.blr.activity.statistical.StatisticalActivity;
import com.zxdc.utils.library.util.ActivitysLifecycle;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.error.CockroachUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabActivity extends android.app.TabActivity {

    @BindView(R.id.img_main)
    ImageView imgMain;
    @BindView(R.id.tv_main)
    TextView tvMain;
    @BindView(R.id.img_audit)
    ImageView imgAudit;
    @BindView(R.id.tv_audit)
    TextView tvAudit;
    @BindView(R.id.img_statistical)
    ImageView imgStatistical;
    @BindView(R.id.tv_statistical)
    TextView tvStatistical;
    @BindView(android.R.id.tabhost)
    TabHost tabhost;
    private int[] notClick = new int[]{R.mipmap.tab_1_false, R.mipmap.tab_2_false, R.mipmap.tab_3_false};
    private int[] yesClick = new int[]{R.mipmap.tab_1_true, R.mipmap.tab_2_true, R.mipmap.tab_3_true};
    // 按两次退出
    protected long exitTime = 0;
    private List<TextView> tvList = new ArrayList<>();
    private List<ImageView> imgList = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        imgList.add(imgMain);
        imgList.add(imgAudit);
        imgList.add(imgStatistical);
        tvList.add(tvMain);
        tvList.add(tvAudit);
        tvList.add(tvStatistical);
        tabhost = this.getTabHost();
        TabHost.TabSpec spec;
        spec = tabhost.newTabSpec("首页").setIndicator("首页").setContent(new Intent(this, MainActivity.class));
        tabhost.addTab(spec);
        spec = tabhost.newTabSpec("审核").setIndicator("审核").setContent(new Intent(this, AuditActivity.class));
        tabhost.addTab(spec);
        spec = tabhost.newTabSpec("统计").setIndicator("统计").setContent(new Intent(this, StatisticalActivity.class));
        tabhost.addTab(spec);
        tabhost.setCurrentTab(0);
    }


    /**
     * 按钮点击事件
     * @param view
     */
    @OnClick({R.id.lin_main, R.id.lin_audit, R.id.lin_statistical})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_main:
                updateTag(0);
                break;
            case R.id.lin_audit:
                updateTag(1);
                break;
            case R.id.lin_statistical:
                updateTag(2);
                break;
            default:
                break;
        }
    }


    /**
     * 切换tab时，更新图片和文字颜色
     */
    private void updateTag(int type) {
        for (int i = 0; i < 3; i++) {
            if (i == type) {
                imgList.get(i).setImageDrawable(getResources().getDrawable(yesClick[i]));
                tvList.get(i).setTextColor(getResources().getColor(R.color.color_333333));
            } else {
                imgList.get(i).setImageDrawable(getResources().getDrawable(notClick[i]));
                tvList.get(i).setTextColor(getResources().getColor(R.color.color_999999));
            }
        }
        tabhost.setCurrentTab(type);
    }


    /**
     * 连续点击两次返回退出
     * @param event
     * @return
     */
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showLong("再按一次退出程序!");
                exitTime = System.currentTimeMillis();
            } else {
                //关闭小强
                CockroachUtil.clear();
                ActivitysLifecycle.getInstance().exit();
            }
            return false;
        }
        return super.dispatchKeyEvent(event);
    }
}
