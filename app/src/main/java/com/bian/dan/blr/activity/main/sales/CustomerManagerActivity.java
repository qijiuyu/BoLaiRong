package com.bian.dan.blr.activity.main.sales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.fragment.main.CustomerFragment;
import com.zxdc.utils.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 客户管理
 */
public class CustomerManagerActivity extends BaseActivity {

    @BindView(R.id.tv_my_customer)
    TextView tvMyCustomer;
    @BindView(R.id.tv_customer)
    TextView tvCustomer;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.img_right)
    ImageView imgRight;
    //fragment页码
    public int pageIndex=1;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_manager);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            public void onPageSelected(int position) {
                pageIndex = position+1;
                updateViewPager();
            }
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_my_customer, R.id.tv_customer, R.id.img_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                break;
            //我的客户
            case R.id.tv_my_customer:
                pageIndex = 0;
                updateViewPager();
                viewPager.setCurrentItem(0);
                break;
            //客户公海
            case R.id.tv_customer:
                pageIndex = 1;
                updateViewPager();
                viewPager.setCurrentItem(1);
                break;
            //新增客户
            case R.id.img_right:
                setClass(AddCustomerActivity.class);
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
            return new CustomerFragment();
        }
    }


    private void updateViewPager() {
        if (pageIndex == 1) {
            tvMyCustomer.setTextColor(getResources().getColor(android.R.color.white));
            tvCustomer.setTextColor(getResources().getColor(R.color.color_80ffffff));
            imgRight.setVisibility(View.VISIBLE);
        } else {
            tvMyCustomer.setTextColor(getResources().getColor(R.color.color_80ffffff));
            tvCustomer.setTextColor(getResources().getColor(android.R.color.white));
            imgRight.setVisibility(View.GONE);
        }
    }
}
