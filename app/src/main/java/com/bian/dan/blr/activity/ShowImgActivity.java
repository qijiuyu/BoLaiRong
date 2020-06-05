package com.bian.dan.blr.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bumptech.glide.Glide;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.TouchImageView;

import java.util.List;

/**
 * 展示图片
 */
public class ShowImgActivity extends BaseActivity {

    private ViewPager viewPager;
    private TextView tvNum,tvTotal;
    private List<String> listImg;
    private int position;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);
        getImages();
        initView();
    }


    /**
     * 获取图片数据
     */
    private void getImages(){
        position=getIntent().getIntExtra("position",0);
        listImg= getIntent().getStringArrayListExtra("imgs");
    }


    /**
     * 初始化
     */
    private void initView() {
        tvNum=findViewById(R.id.tv_num);
        tvTotal=findViewById(R.id.tv_total);
        tvTotal.setText(listImg.size()+"");
        viewPager =findViewById(R.id.view_pager);
        //ViewPager相关
        ViewPagerAdater myAdater = new ViewPagerAdater(this);
        viewPager.setAdapter(myAdater);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            public void onPageSelected(int position) {
                tvNum.setText(++position+"");
            }
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(position);
    }


    public class ViewPagerAdater extends PagerAdapter {
        private Context context;

        public ViewPagerAdater(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return listImg.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TouchImageView imageView = new TouchImageView(context);
            Glide.with(context).load(listImg.get(position)).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
