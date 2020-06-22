package com.bian.dan.blr.activity.audit.selling;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.fragment.audit.AuditSellingFragment;
import com.bian.dan.blr.fragment.audit.ProductionFragment;
import com.zxdc.utils.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 审核的售卖出库列表
 */
public class AuditSellingActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_no_audit)
    TextView tvNoAudit;
    @BindView(R.id.tv_yes_audit)
    TextView tvYesAudit;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    //fragment页码
    public int pageIndex;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_financial);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("售卖出库");
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            public void onPageSelected(int position) {
                pageIndex = position;
                updateViewPager();
            }
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_no_audit, R.id.tv_yes_audit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //待审核
            case R.id.tv_no_audit:
                pageIndex = 0;
                updateViewPager();
                viewPager.setCurrentItem(0);
                break;
            //已审核
            case R.id.tv_yes_audit:
                pageIndex = 1;
                updateViewPager();
                viewPager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            return new AuditSellingFragment();
        }
    }


    private void updateViewPager() {
        if(pageIndex==0){
            tvNoAudit.setBackgroundResource(R.drawable.bg_audit_line);
            tvYesAudit.setBackgroundResource(0);
            tvNoAudit.setTextColor(getResources().getColor(R.color.colorPrimary));
            tvYesAudit.setTextColor(getResources().getColor(android.R.color.black));
        }else{
            tvNoAudit.setBackgroundResource(0);
            tvYesAudit.setBackgroundResource(R.drawable.bg_audit_line);
            tvNoAudit.setTextColor(getResources().getColor(android.R.color.black));
            tvYesAudit.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
