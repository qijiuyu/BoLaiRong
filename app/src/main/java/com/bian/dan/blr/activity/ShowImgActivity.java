package com.bian.dan.blr.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import com.zxdc.utils.library.bean.DownLoad;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.TouchImageView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 展示图片
 */
public class ShowImgActivity extends BaseActivity {

    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    private List<String> listImg;
    private int position;
    /**
     * 1：需要显示保存按钮
     */
    private int type;
    /**
     * 图片保存地址
     */
    private String savePath;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);
        ButterKnife.bind(this);
        getImages();
        initView();
    }


    /**
     * 获取图片数据
     */
    private void getImages() {
        position = getIntent().getIntExtra("position", 0);
        listImg = getIntent().getStringArrayListExtra("imgs");
        type=getIntent().getIntExtra("type",0);
    }


    /**
     * 初始化
     */
    private void initView() {
        if (type == 1) {
            tvSave.setVisibility(View.VISIBLE);
        }
        tvTotal.setText(listImg.size() + "");
        //ViewPager相关
        ViewPagerAdater myAdater = new ViewPagerAdater(this);
        viewPager.setAdapter(myAdater);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                ShowImgActivity.this.position=position;
                tvNum.setText(++position + "");
            }

            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(position);
    }

    @OnClick({R.id.lin_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //下载图片
            case R.id.tv_save:
                download();
                break;
            default:
                break;
        }
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

    /**
     * 下载图片
     */
    private void download(){
        DialogUtil.showProgress(this,"图片下载中");
        savePath= Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+System.currentTimeMillis()+".jpg";
        DownLoad d = new DownLoad();
        d.setDownPath(listImg.get(position));
        d.setSavePath(savePath);
        HttpMethod.download(d, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                ToastUtil.showLong("图片已保存");
                //更新相册
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                intent.setData(Uri.fromFile(new File(savePath)));
                sendBroadcast(intent);
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
