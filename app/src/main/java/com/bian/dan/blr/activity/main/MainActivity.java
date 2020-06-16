package com.bian.dan.blr.activity.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.LoginActivity;
import com.bian.dan.blr.activity.UpdatePwdActivity;
import com.bian.dan.blr.activity.main.financial.WageManagerActivity;
import com.bian.dan.blr.activity.main.procurement.ProcurementActivity;
import com.bian.dan.blr.activity.main.procurement.SupplierListActivity;
import com.bian.dan.blr.activity.main.production.OutBoundProductActivity;
import com.bian.dan.blr.activity.main.sales.ContractManagerActivity;
import com.bian.dan.blr.activity.main.sales.CustomerManagerActivity;
import com.bian.dan.blr.activity.main.sales.FinancialActivity;
import com.bian.dan.blr.activity.main.sales.InventoryListActivity;
import com.bian.dan.blr.activity.main.sales.LogListActivity;
import com.bian.dan.blr.activity.main.sales.OutBoundActivity;
import com.bian.dan.blr.activity.main.sales.ProductionPlanActivity;
import com.bian.dan.blr.activity.main.warehouse.DeviceListActivity;
import com.bian.dan.blr.activity.main.warehouse.InventoryDetailsActivity;
import com.bian.dan.blr.activity.main.warehouse.LedTableActivity;
import com.bian.dan.blr.activity.main.warehouse.OutAndEntryActivity;
import com.bian.dan.blr.activity.main.warehouse.SalesOutBoundActivity;
import com.bian.dan.blr.activity.main.warehouse.SdEnterActivity;
import com.bian.dan.blr.activity.main.warehouse.SellingListActivity;
import com.bian.dan.blr.application.MyApplication;
import com.bumptech.glide.Glide;
import com.paradoxie.autoscrolltextview.VerticalTextview;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Notice;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ActivitysLifecycle;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.error.CockroachUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 首页
 * 用户根据role_id区分角色
 * 1：管理员
 * 2：销售外勤
 * 3：生产组长
 * 4：财务
 * 5：仓库
 * 6：销售内勤
 * 7：生产工人
 *
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_notice)
    VerticalTextview tvNotice;
    @BindView(R.id.lin_sales)
    LinearLayout linSales;
    @BindView(R.id.lin_warehouse)
    LinearLayout linWareHouse;
    @BindView(R.id.lin_financial)
    LinearLayout linFinancial;
    @BindView(R.id.lin_product)
    LinearLayout linProduct;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        showBanner();
        //公告列表查询
        getNoticeList();
        //设置推送
        setPush();
    }


    /**
     * 初始化
     */
    private void initView(){
        /**
         * 通过角色id显示不同的功能
         */
        switch (MyApplication.getRoleId()){
            case 2://销售外勤
            case 6://销售内勤
                linWareHouse.setVisibility(View.GONE);
                linFinancial.setVisibility(View.GONE);
                linProduct.setVisibility(View.GONE);
                break;
            case 4://财务
                linSales.setVisibility(View.GONE);
                linWareHouse.setVisibility(View.GONE);
                linProduct.setVisibility(View.GONE);
                 break;
            case 5://仓库
                linSales.setVisibility(View.GONE);
                linFinancial.setVisibility(View.GONE);
                linProduct.setVisibility(View.GONE);
                break;
            case 3://生产组长
            case 7://生产工人
                linWareHouse.setVisibility(View.GONE);
                linFinancial.setVisibility(View.GONE);
                linSales.setVisibility(View.GONE);
            default:
                break;
        }
    }


    private Intent intent=new Intent();
    @OnClick({R.id.tv_login_out,R.id.tv_pwd,R.id.tv_sales_htgl, R.id.tv_sales_kcmx, R.id.tv_sales_ckd, R.id.tv_sales_scjh, R.id.tv_sales_rz, R.id.tv_sales_khgl, R.id.tv_sales_cwbx, R.id.tv_collect_cgd, R.id.tv_collect_gysgl, R.id.tv_collect_cwbx, R.id.tv_house_ckgl, R.id.tv_house_sdrkd, R.id.tv_house_ckd, R.id.tv_house_cgrkd, R.id.tv_house_scck, R.id.tv_house_scrk,R.id.tv_house_qlb, R.id.tv_house_smsqb, R.id.tv_house_sbgl, R.id.tv_house_cwbx, R.id.tv_financial_gzgl, R.id.tv_financial_cwbx, R.id.tv_production_scjh, R.id.tv_production_ckd, R.id.tv_production_cwbx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //退出登录
            case R.id.tv_login_out:
                SPUtil.getInstance(this).removeMessage(SPUtil.TOKEN);
                intent.setClass(this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                //停止推送
                JPushInterface.stopPush(this);
                finish();
                 break;
            //修改密码
            case R.id.tv_pwd:
                 setClass(UpdatePwdActivity.class);
                 break;
            //销售
            //合同管理
            case R.id.tv_sales_htgl:
                setClass(ContractManagerActivity.class);
                break;
            //库存明细
            case R.id.tv_sales_kcmx:
                setClass(InventoryListActivity.class);
                break;
            //出库单
            case R.id.tv_sales_ckd:
                setClass(OutBoundActivity.class);
                break;
            //生产计划
            case R.id.tv_sales_scjh:
                setClass(ProductionPlanActivity.class);
                break;
          //日志
            case R.id.tv_sales_rz:
                setClass(LogListActivity.class);
                break;
           //客户管理
            case R.id.tv_sales_khgl:
                setClass(CustomerManagerActivity.class);
                break;
           //财务报销
            case R.id.tv_sales_cwbx:
                setClass(FinancialActivity.class);
                break;
            //采购单
            case R.id.tv_collect_cgd:
                intent.setClass(this,ProcurementActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            //供应商管理
            case R.id.tv_collect_gysgl:
                setClass(SupplierListActivity.class);
                break;
            //财务报销
            case R.id.tv_collect_cwbx:
                setClass(FinancialActivity.class);
                break;




            //仓库管理
            case R.id.tv_house_ckgl:
                setClass(InventoryDetailsActivity.class);
                break;
            //手动入库单
            case R.id.tv_house_sdrkd:
                setClass(SdEnterActivity.class);
                break;
            //销售出库单
            case R.id.tv_house_ckd:
                setClass(SalesOutBoundActivity.class);
                break;
           //采购入库单
            case R.id.tv_house_cgrkd:
                intent.setClass(this,ProcurementActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            //生产出库
            case R.id.tv_house_scck:
                setClass(OutAndEntryActivity.class);
                break;
            //生产入库
            case R.id.tv_house_scrk:
                break;
            //请领表
            case R.id.tv_house_qlb:
                setClass(LedTableActivity.class);
                break;
            //售卖申请表
            case R.id.tv_house_smsqb:
                setClass(SellingListActivity.class);
                break;
            //设备管理
            case R.id.tv_house_sbgl:
                setClass(DeviceListActivity.class);
                break;
            case R.id.tv_house_cwbx:
                setClass(FinancialActivity.class);
                break;



            //工资管理
            case R.id.tv_financial_gzgl:
                setClass(WageManagerActivity.class);
                break;
           //财务报销
            case R.id.tv_financial_cwbx:
                intent.setClass(this,FinancialActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;


                //生产
            //生产计划列表
            case R.id.tv_production_scjh:
                 setClass(com.bian.dan.blr.activity.main.production.ProductionPlanActivity.class);
                break;
            //生产--出库单
            case R.id.tv_production_ckd:
                setClass(OutBoundProductActivity.class);
                break;
            //生产--财务
            case R.id.tv_production_cwbx:
                setClass(FinancialActivity.class);
                break;
            default:
                break;
        }
    }


    /**
     * 显示banner数据
     */
    private void showBanner() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.banner_one);
        list.add(R.mipmap.banner_two);
        list.add(R.mipmap.banner_three);
        banner.setVisibility(View.VISIBLE);
        //设置样式，里面有很多种样式可以自己都看看效果
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.Default);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new ABImageLoader());
        //设置图片集合
        banner.setImages(list);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public class ABImageLoader extends ImageLoader {
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(activity).load((int)path).into(imageView);
        }
    }


    /**
     * 显示公告
     */
    private void showNotice(final List<Notice.ListBean> noticeList) {
        if(noticeList==null || noticeList.size()==0){
            return;
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i<noticeList.size();i++){
             list.add(noticeList.get(i).getTitle());
        }
        tvNotice.setTextList(list);//加入显示内容,集合类型
        tvNotice.setText(17, 5, Color.BLACK);//设置属性,具体跟踪源码
        tvNotice.setTextStillTime(5000);//设置停留时长间隔
        tvNotice.setAnimTime(800);//设置进入和退出的时间间隔
        tvNotice.startAutoScroll();
        //对单条文字的点击监听
        tvNotice.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            public void onItemClick(int position) {
                Notice.ListBean listBean=noticeList.get(position);
                Intent intent=new Intent(activity,NoticeDetailsActivity.class);
                intent.putExtra("listBean",listBean);
                startActivity(intent);
            }
        });

    }


    /**
     * 公告列表查询
     */
    private void getNoticeList(){
        HttpMethod.getNoticeList(new NetWorkCallBack() {
            public void onSuccess(Object object) {
                Notice notice= (Notice) object;
                if(notice.isSussess()){
                    //显示公告
                    showNotice(notice.getData().getRows());
                }else{
                    ToastUtil.showLong(notice.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 设置推送
     */
    private void setPush() {
        //恢复推送
        JPushInterface.resumePush(this);

        final UserInfo userInfo=MyApplication.getUser();
        if (userInfo == null) {
            return;
        }
        //设置极光推送的别名
        JPushInterface.setAliasAndTags(getApplicationContext(), String.valueOf(userInfo.getUser().getUserId()), null, mAliasCallback);
    }


    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        public void gotResult(int code, String alias, Set<String> tags) {
            switch (code) {
                //设置别名成功
                case 0:
                    LogUtils.e("推送设置成功");
                    break;
                //设置别名失败
                case 6002:
                    LogUtils.e("推送设置失败");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            setPush();
                        }
                    }, 30000);
                    break;
                default:
            }
        }
    };


    // 按两次退出
    protected long exitTime = 0;
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
