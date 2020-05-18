package com.bian.dan.blr.activity.statistical;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;
import com.zxdc.utils.library.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * 统计
 */
public class StatisticalActivity extends BaseActivity {

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
    PieChartData data;
    private LineChartData linData;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical);
        ButterKnife.bind(this);
        showViewFiscal();
        showViewCustomer();
        showViewOrder();

    }


    /**
     * 显示财政收支对比
     */
    private void showViewFiscal() {
        List<SliceValue> values = new ArrayList<SliceValue>();
        SliceValue sliceValue = new SliceValue(70, Color.parseColor("#FE8E2C"));
        SliceValue sliceValue2 = new SliceValue(30, Color.parseColor("#47C9FB"));
        values.add(sliceValue);
        values.add(sliceValue2);
        data = new PieChartData(values);
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
    private void showViewCustomer() {
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.addData(new SimplePieInfo(50, Color.parseColor("#47C9FB"), "成交客户"))
                .addData(new SimplePieInfo(30, Color.parseColor("#FE8E2C"), "培养客户"))
                .addData(new SimplePieInfo(20, Color.parseColor("#007AFF"), "潜在客户"))
                .strokeWidth(70)// 圆弧（甜甜圈）宽度
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
    }


    /**
     * 显示销售订单模块
     */
    private void showViewOrder(){
        String[] date = {"2020-01", "2020-02", "2020-03", "2020-04", "2020-05", "2020-06", "2020-07"};//X轴的数据
        int[] score = {500, 400, 900, 350, 100, 700, 200};//x轴对应的y值
        List<PointValue> mPointValues = new ArrayList<>();
        List<AxisValue> mAxisXValues = new ArrayList<>();

        /**
         * 设置X 轴的显示
         */
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }


        /**
         * 图表的每个点的显示
         */
        for (int i = 0; i < score.length; i++) {
            // 构造函数传参 位置 值
            mPointValues.add(new PointValue(i, score[i]));
        }


        // 折线对象 传参数为点的集合对象   y轴值的范围自动生成 根据坐标的y值 不必自己准备数据
        Line line = new Line(mPointValues).setColor(Color.parseColor("#47C9FB"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(true);//是否填充曲线的面积
        line.setHasLabels(false);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(false);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
//        line.setHasGradientToTransparent(true);
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.BLACK);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部  （顶部底部一旦设置就意味着x轴）
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(false); //x 轴分割线  每个x轴上 面有个虚线 与x轴垂直

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
//        axisY.setName("这是Y轴");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        axisY.setTextColor(Color.BLACK);
        data.setAxisYLeft(axisY);  //Y轴设置在左边（左面右面一旦设定就意味着y轴）
        //data.setAxisYRight(axisY);  //y轴设置在右边



        //设置行为属性，支持缩放、滑动以及平移
        viewOrder.setZoomEnabled(true);
        viewOrder.setInteractive(true);
        viewOrder.setZoomType(ZoomType.HORIZONTAL);
//        viewOrder.setMaxZoom((float) 2);//最大方法比例
        viewOrder.setContainerScrollEnabled(false, ContainerScrollType.HORIZONTAL);
        viewOrder.setScrollEnabled(true);
        viewOrder.setLineChartData(data);
    }
}
