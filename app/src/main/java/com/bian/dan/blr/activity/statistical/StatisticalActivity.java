package com.bian.dan.blr.activity.statistical;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.persenter.statistical.StatisticalPersenter;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.CustomerState;
import com.zxdc.utils.library.bean.Income;
import com.zxdc.utils.library.bean.StatisticalGoods;
import com.zxdc.utils.library.bean.StatisticalMaterial;
import com.zxdc.utils.library.bean.StatisticalSales;
import com.zxdc.utils.library.util.BigDecimalUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * 统计
 */
public class StatisticalActivity extends BaseActivity implements TextWatcher{

    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_income)
    TextView tvIncome;
    @BindView(R.id.tv_spending)
    TextView tvSpending;
    @BindView(R.id.view_fiscal)
    PieChartView viewFiscal;
    @BindView(R.id.view_customer)
    AnimatedPieView viewCustomer;
    @BindView(R.id.view_order)
    LineChartView viewOrder;
    @BindView(R.id.view_sales_money)
    LineChartView viewSalesMoney;
    @BindView(R.id.view_material)
    LineChartView viewMaterial;
    @BindView(R.id.view_product)
    ColumnChartView viewProduct;
    @BindView(R.id.tv_start_fiscal)
    TextView tvStartFiscal;
    @BindView(R.id.tv_end_fiscal)
    TextView tvEndFiscal;
    @BindView(R.id.tv_start_sales_order)
    TextView tvStartSalesOrder;
    @BindView(R.id.tv_end_sales_order)
    TextView tvEndSalesOrder;
    @BindView(R.id.tv_start_sales_money)
    TextView tvStartSalesMoney;
    @BindView(R.id.tv_end_sales_money)
    TextView tvEndSalesMoney;
    @BindView(R.id.tv_start_material)
    TextView tvStartMaterial;
    @BindView(R.id.tv_end_material)
    TextView tvEndMaterial;
    @BindView(R.id.tv_customer_num)
    TextView tvCustomerNum;
    private StatisticalPersenter statisticalPersenter;
    /**
     * 1：财务收支对比
     * 2：销售订单增长趋势
     * 3：销售金额增长趋势
     * 4：物料使用趋势
     */
    private int selectTime;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_statistical);
//        ButterKnife.bind(this);
//        initView();
//        //获取收支对比
//        statisticalPersenter.getIncome("2020-01-01","2020-05-26");
//        //获取客户状态统计
//        statisticalPersenter.getCustomerState();
//        //销售单数及销售金额统计
//        statisticalPersenter.getStatistionSales("2020-05-26");
//        //原料消耗月度统计
//        statisticalPersenter.getStatisticalMaterial("2020-05-26");
//        //成品统计
//        statisticalPersenter.getStatisticalGoods();
    }

    /**
     * 初始化
     */
    private void initView() {
        statisticalPersenter = new StatisticalPersenter(this);
        tvStartFiscal.addTextChangedListener(this);
        tvEndFiscal.addTextChangedListener(this);
        tvStartSalesOrder.addTextChangedListener(this);
        tvEndSalesOrder.addTextChangedListener(this);
        tvStartSalesMoney.addTextChangedListener(this);
        tvEndSalesMoney.addTextChangedListener(this);
        tvStartMaterial.addTextChangedListener(this);
        tvEndMaterial.addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String start,end;
        switch (selectTime){
            //财务收支对比
            case 1:
                 start=tvStartFiscal.getText().toString().trim();
                 end=tvEndFiscal.getText().toString().trim();
                 if(!TextUtils.isEmpty(start) && !TextUtils.isEmpty(end)){
                     statisticalPersenter.getIncome(start,end);
                 }
                 break;
            //销售订单增长趋势
            case 2:
                 start=tvStartSalesOrder.getText().toString().trim();
                 end=tvEndSalesOrder.getText().toString().trim();
                 if(!TextUtils.isEmpty(start) && !TextUtils.isEmpty(end)){
                     showViewOrder(salesBean);
                 }
                break;
            //销售金额增长趋势
            case 3:
                 start=tvStartSalesMoney.getText().toString().trim();
                 end=tvEndSalesMoney.getText().toString().trim();
                 if(!TextUtils.isEmpty(start) && !TextUtils.isEmpty(end)){

                 }
                break;
            //物料使用趋势
            case 4:
                 start=tvStartMaterial.getText().toString().trim();
                 end=tvEndMaterial.getText().toString().trim();
                 if(!TextUtils.isEmpty(start) && !TextUtils.isEmpty(end)){

                 }
                break;
            default:
                break;
        }
    }


    @OnClick({R.id.tv_start_fiscal, R.id.tv_end_fiscal,R.id.tv_start_sales_order, R.id.tv_end_sales_order, R.id.tv_start_sales_money, R.id.tv_end_sales_money, R.id.tv_start_material, R.id.tv_end_material})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //财务收支对比-开始日期
            case R.id.tv_start_fiscal:
                selectTime=1;
                statisticalPersenter.selectTime(tvStartFiscal,tvEndFiscal,1);
                break;
            case R.id.tv_end_fiscal:
                selectTime=1;
                statisticalPersenter.selectTime(tvStartFiscal,tvEndFiscal,2);
                break;
            //销售订单增长趋势-开始日期
            case R.id.tv_start_sales_order:
                selectTime=2;
                statisticalPersenter.selectTime(tvStartSalesOrder,tvEndSalesOrder,1);
                break;
            case R.id.tv_end_sales_order:
                selectTime=2;
                statisticalPersenter.selectTime(tvStartSalesOrder,tvEndSalesOrder,2);
                break;
            //销售金额增长趋势-开始日期
            case R.id.tv_start_sales_money:
                selectTime=3;
                statisticalPersenter.selectTime(tvStartSalesMoney,tvEndSalesMoney,1);
                break;
            case R.id.tv_end_sales_money:
                selectTime=3;
                statisticalPersenter.selectTime(tvStartSalesMoney,tvEndSalesMoney,2);
                break;
            //物料使用趋势-开始日期
            case R.id.tv_start_material:
                selectTime=4;
                statisticalPersenter.selectTime(tvStartMaterial,tvEndMaterial,1);
                break;
            case R.id.tv_end_material:
                selectTime=4;
                statisticalPersenter.selectTime(tvStartMaterial,tvEndMaterial,2);
                break;
            default:
                break;
        }
    }


    /**
     * 显示财政收支对比
     */
    public void showViewFiscal(Income.IncomeBean incomeBean) {
        if(incomeBean==null){
            return;
        }
        //显示支出与收入数据
        tvIncome.setText(String.valueOf(incomeBean.getIncome()));
        tvSpending.setText(String.valueOf(incomeBean.getSpending()));

        //计算收入与支出的百分比
        final int num1=BigDecimalUtil.percentage(incomeBean.getIncome(),incomeBean.getSpending());
        List<SliceValue> values = new ArrayList<>();
        SliceValue sliceValue = new SliceValue(num1, Color.parseColor("#FE8E2C"));
        SliceValue sliceValue2 = new SliceValue(100-num1, Color.parseColor("#47C9FB"));
        values.add(sliceValue);
        values.add(sliceValue2);
        PieChartData data = new PieChartData(values);
        data.setHasCenterCircle(true);
        data.setCenterCircleScale(0.7f);//设置环形的大小级别


        data.setCenterText1("收入");
        data.setCenterText2("70%");

        data.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));
        data.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                (int) getResources().getDimension(R.dimen.pie_chart_text2_size)));
        data.setCenterText2Color(Color.parseColor("#FE8E2C"));

        viewFiscal.startDataAnimation();
        viewFiscal.startDataAnimation(2000);
        viewFiscal.setPieChartData(data);
    }


    /**
     * 显示客户状态统计
     */
    public void showViewCustomer(CustomerState.StateBean stateBean) {
        if(stateBean==null){
            return;
        }
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        List<SimplePieInfo> list = new ArrayList<>();
        list.add(new SimplePieInfo(stateBean.getName1(), Color.parseColor("#FFFF00"), "潜在客户"));
        list.add(new SimplePieInfo(stateBean.getName2(), Color.parseColor("#F9B552"), "死单客户"));
        list.add(new SimplePieInfo(stateBean.getName3(), Color.parseColor("#F29EC2"), "渠道客户"));
        list.add(new SimplePieInfo(stateBean.getName4(), Color.parseColor("#8C98CC"), "流失客户"));
        list.add(new SimplePieInfo(stateBean.getName5(), Color.parseColor("#1CFEBC"), "意向客户"));
        list.add(new SimplePieInfo(stateBean.getName6(), Color.parseColor("#04C8FC"), "培养客户"));
        list.add(new SimplePieInfo(stateBean.getName7(), Color.parseColor("#468DFF"), "成交客户"));
        list.add(new SimplePieInfo(stateBean.getName8(), Color.parseColor("#12B5B0"), "客户线索"));
        for (int i = 0; i < list.size(); i++) {
            config.addData(list.get(i));
        }
        config.strokeWidth(70)// 圆弧（甜甜圈）宽度
                .duration(2000)// 动画时间
                .startAngle(-90f)// 开始的角度
                .drawText(true)// 是否绘制文字描述
                .textSize(20)// 绘制的文字大小
                .textMargin(8)// 绘制文字与导航线的距离
                .autoSize(true)// 自动测量甜甜圈半径
                .pieRadius(130)// 甜甜圈半径
                .pieRadiusRatio(0.8f)// 甜甜圈半径占比
                .guidePointRadius(2)// 设置描述文字的开始小点的大小
                .guideLineWidth(4)// 设置描述文字的指示线宽度
                .guideLineMarginStart(9)// 设置描述文字的指示线开始距离外圆半径的大小
                .textGravity(AnimatedPieViewConfig.ABOVE)// 设置描述文字方向 【-AnimatedPieViewConfig.ABOVE：文字将会在导航线上方绘制-AnimatedPieViewConfig.BELOW：文字在导航线下方绘制-AnimatedPieViewConfig.ALIGN：文字与导航线对齐-AnimatedPieViewConfig.ECTOPIC：文字在1、2象限部分绘制在线的上方，在3、4象限绘制在线的下方】
                .canTouch(true)// 是否允许甜甜圈点击放大
                .splitAngle(0);// 甜甜圈间隙角度
        viewCustomer.applyConfig(config);
        viewCustomer.start();

        //总数量
        tvCustomerNum.setText(String.valueOf(stateBean.getName1()+stateBean.getName2()+stateBean.getName3()+stateBean.getName4()+stateBean.getName5()+stateBean.getName6()+stateBean.getName7()+stateBean.getName8()));
    }


    /**
     * 显示销售订单模块
     */
    StatisticalSales.SalesBean salesBean;
    public void showViewOrder(StatisticalSales.SalesBean salesBean) {
        if(salesBean==null){
            return;
        }
        this.salesBean=salesBean;
        //遍历获取要显示的值
        float[] score =new float[salesBean.getDataList().size()];
        for (int i=0;i<salesBean.getDataList().size();i++){
             score[i]=salesBean.getDataList().get(i).getNum();
        }
        statisticalPersenter.setChartLine(salesBean.getTimeList(), score, Color.parseColor("#4AC9FB"), Color.parseColor("#DAF5FE"), viewOrder);
        // 下面的这个api控制 滑动
        final Viewport v = new Viewport(viewOrder.getMaximumViewport());
//        v.bottom = 0;
//        v.top = 20;
//        viewOrder.setMaximumViewport(v);
        v.left = score.length - 7;
        v.right = score.length - 1;
        viewOrder.setCurrentViewport(v);
    }


    /**
     * 显示销售金额模块
     */
    public void showViewSalesMoney(StatisticalSales.SalesBean salesBean) {
        if(salesBean==null){
            return;
        }
        //遍历获取要显示的值
        float[] score =new float[salesBean.getDataList().size()];
        for (int i=0;i<salesBean.getDataList().size();i++){
            score[i]=salesBean.getDataList().get(i).getAmmount();
        }
        statisticalPersenter.setChartLine(salesBean.getTimeList(), score, Color.parseColor("#F6B467"), Color.parseColor("#FCEFDF"), viewSalesMoney);
        // 下面的这个api控制 滑动
        Viewport v = new Viewport(viewSalesMoney.getMaximumViewport());
//        v.bottom = 0;
//        v.top = 70;
        viewSalesMoney.setMaximumViewport(v);
        v.left = score.length - 7;
        v.right = score.length - 1;
        viewSalesMoney.setCurrentViewport(v);
    }


    /**
     * 显示物料使用趋势模块
     */
    public void showMaterial(StatisticalMaterial.MaterialBean materialBean) {
        if(materialBean==null){
            return;
        }
        //遍历获取要显示的值
        float[] score =new float[materialBean.getDataList().size()];
        for (int i=0;i<materialBean.getDataList().size();i++){
            score[i]=materialBean.getDataList().get(i).getTotal();
        }
        statisticalPersenter.setChartLine(materialBean.getTimeList(), score, Color.parseColor("#5FDB75"), Color.parseColor("#C9EFC8"), viewMaterial);
        // 下面的这个api控制 滑动
        Viewport v = new Viewport(viewMaterial.getMaximumViewport());
//        v.bottom = 0;
//        v.top = 800;
        viewMaterial.setMaximumViewport(v);
        v.left = score.length - 7;
        v.right = score.length - 1;
        viewMaterial.setCurrentViewport(v);
    }


    /**
     * 显示成品统计
     */
    public void showProduct(StatisticalGoods.GoodBean goodBean) {
        if(goodBean==null){
            return;
        }
        List<Column> columns = new ArrayList<>();
        int[] score=new int[]{goodBean.getName1(),goodBean.getName2(),goodBean.getName3(),goodBean.getName4(),goodBean.getName5(),goodBean.getName6(),goodBean.getName7()};
        for (int i = 0; i < score.length; ++i) {
            List<SubcolumnValue> values = new ArrayList<>();
            values.add(new SubcolumnValue((float)score[i], Color.parseColor("#04BFF2")));
            Column column = new Column(values);
            column.setHasLabels(false);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
        }
        ColumnChartData data = new ColumnChartData(columns);

        //X轴
        //底部标题
        List<String> title = new ArrayList<>();
        title.add("棕色");
        title.add("黑色");
        title.add("焊接");
        title.add("琥珀色");
        title.add("非标");
        title.add("低端");
        title.add("整体聚晶");
        List<AxisValue> axisXValues = new ArrayList<>();
        for (int i = 0; i < title.size(); i++) {
            //设置X轴的柱子所对应的属性名称(底部文字)
            axisXValues.add(new AxisValue(i).setLabel(title.get(i)));
        }
        Axis axisBottom = new Axis(axisXValues);
        //字体大小
        axisBottom.setTextSize(10);
        //距离各标签之间的距离,包括离Y轴间距 (0-32之间)
        axisBottom.setMaxLabelChars(0);
        //设置x轴在底部显示
        data.setAxisXBottom(axisBottom);


        //Y轴
        Axis axisLeft = new Axis();
        //是否显示X轴的网格线
        axisLeft.setHasLines(true);
        axisLeft.setLineColor(Color.parseColor("#ABDFF8"));//设置网格线的颜色
        //字体大小
        axisBottom.setTextSize(10);
        data.setAxisYLeft(axisLeft);

        data.setFillRatio(0.5f);
        viewProduct.setColumnChartData(data);
        viewProduct.setInteractive(true);
        viewProduct.setZoomType(ZoomType.HORIZONTAL);
        viewProduct.setMaxZoom((float) 4);//最大方法比例
        viewProduct.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);

        // 下面的这个api控制 滑动
        Viewport v = new Viewport(viewProduct.getMaximumViewport());
//        v.bottom = 0;
//        v.top = 70;
        viewProduct.setMaximumViewport(v);
        v.left = score.length - 7;
        v.right = score.length - 1;
        viewProduct.setCurrentViewport(v);
    }
}
