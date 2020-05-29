package com.bian.dan.blr.activity.main.financial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.fragment.main.SalesFragment;
import com.bian.dan.blr.fragment.main.WorkersFragment;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.eventbus.EventStatus;
import com.zxdc.utils.library.util.DateUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工资管理
 */
public class WageManagerActivity extends BaseActivity {

    @BindView(R.id.tv_workers)
    TextView tvWorkers;
    @BindView(R.id.tv_sales)
    TextView tvSales;
    @BindView(R.id.tv_time)
    public TextView tvTime;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    //fragment页码
    public int pageIndex=1;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wage_manager);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        //显示当前时间
        tvTime.setText(DateUtils.getNewTime());

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

    @OnClick({R.id.lin_back, R.id.tv_workers, R.id.tv_sales,R.id.tv_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //工人工资
            case R.id.tv_workers:
                pageIndex = 0;
                updateViewPager();
                viewPager.setCurrentItem(0);
                break;
            //销售工资
            case R.id.tv_sales:
                pageIndex = 1;
                updateViewPager();
                viewPager.setCurrentItem(1);
                break;
            //选择时间
            case R.id.tv_time:
                 selectTime();
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
            if(position==0){
                return new WorkersFragment();
            }else{
                return new SalesFragment();
            }
        }
    }


    private void updateViewPager() {
        if (pageIndex == 1) {
            tvWorkers.setTextColor(getResources().getColor(android.R.color.white));
            tvSales.setTextColor(getResources().getColor(R.color.color_80ffffff));
        } else {
            tvWorkers.setTextColor(getResources().getColor(R.color.color_80ffffff));
            tvSales.setTextColor(getResources().getColor(android.R.color.white));
        }
    }


    /**
     * 选择时间
     */
    public void selectTime(){
        CustomDatePicker customDatePicker = new CustomDatePicker(activity, new CustomDatePicker.ResultHandler() {
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTime.setText(time.substring(0,7));
                EventBus.getDefault().post(new EventBusType(EventStatus.SELECT_WAGE_TIME));
            }
        }, "1920-01-01 00:00", "2050-12-31 23:59");
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(true); // 不允许循环滚动

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String now = sdf.format(new Date());
        customDatePicker.show(now.split(" ")[0]);
    }
}
