package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.fragment.main.CustomerFragment;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.eventbus.EventStatus;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.tv_key)
    public TextView tvKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
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


        /**
         * 监听客户名称输入框
         */
        tvKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    imgClear.setVisibility(View.VISIBLE);
                }else{
                    tvKey.setTag("");
                    imgClear.setVisibility(View.GONE);
                }
                //加载数据
                EventBus.getDefault().post(new EventBusType(EventStatus.REFRESH_CUSTOMER_LIST));
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_my_customer, R.id.tv_customer, R.id.img_right,R.id.tv_key, R.id.img_clear})
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
                setClass(AddCustomerActivity.class,1000);
                break;
            //选择客户名称
            case R.id.tv_key:
                setClass(SelectCustomerActivity.class,100);
                break;
            case R.id.img_clear:
                tvKey.setText(null);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            //选择客户名称回执
            case 100:
                if(data!=null){
                    Customer customer = (Customer) data.getSerializableExtra("customer");
                    if(customer!=null){
                        tvKey.setTag(String.valueOf(customer.getId()));
                        tvKey.setText(customer.getCustomerName());
                    }
                }
                break;
            //增加成功后，重新刷新列表
            case 1000:
                //加载数据
                EventBus.getDefault().post(EventStatus.REFRESH_CUSTOMER_LIST);
                break;
            default:
                break;
        }
    }
}
