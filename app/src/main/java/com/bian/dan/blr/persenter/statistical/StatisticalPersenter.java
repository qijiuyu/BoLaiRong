package com.bian.dan.blr.persenter.statistical;

import android.graphics.Color;
import android.widget.TextView;

import com.bian.dan.blr.activity.statistical.StatisticalActivity;
import com.bian.dan.blr.view.time.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

public class StatisticalPersenter {

    private StatisticalActivity activity;

    public StatisticalPersenter(StatisticalActivity activity){
        this.activity=activity;
    }


    /**
     * 选择时间
     * @param tvTime
     */
    public void selectTime(final TextView tvTime){
        CustomDatePicker customDatePicker = new CustomDatePicker(activity, new CustomDatePicker.ResultHandler() {
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTime.setText(time.split(" ")[0]);
            }
        }, "1920-01-01 00:00", "2050-12-31 23:59");
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(true); // 不允许循环滚动

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String now = sdf.format(new Date());
        tvTime.setText(now.split(" ")[0]);
        customDatePicker.show(tvTime.getText().toString());
    }


    /**
     * 设置折线属性
     */
    public void setChartLine(String[] dateX, float[] score, int color, int colorLine, LineChartView lineChartView){
        List<PointValue> mPointValues = new ArrayList<>();
        List<AxisValue> mAxisXValues = new ArrayList<>();

        /**
         * 设置X轴的显示数据
         */
        for (int i = 0; i < dateX.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(dateX[i]));
        }


        /**
         * 设置图表的每个点的显示
         */
        for (int i = 0; i < score.length; i++) {
            // 构造函数传参 位置 值
            mPointValues.add(new PointValue(i,score[i]));
        }

        // 折线对象 传参数为点的集合对象   y轴值的范围自动生成 根据坐标的y值 不必自己准备数据
        Line line = new Line(mPointValues).setColor(color);  //折线的颜色
        List<Line> lines = new ArrayList<>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(true);//是否填充曲线的面积
        line.setHasLabels(false);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line.setHasGradientToTransparent(true);
        line.setStrokeWidth(1);//线条的粗细，默认是3
        line.setPointRadius(3);//设置圆点的大小
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.BLACK);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(1); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部  （顶部底部一旦设置就意味着x轴）
        axisX.setHasLines(false); //x 轴分割线  每个x轴上 面有个虚线 与x轴垂直
        axisX.setHasLines(true);//设置是否显示坐标网格
        axisX.setLineColor(colorLine);//设置网格线的颜色


        // Y轴
        Axis axisY = new Axis();  //Y轴
//        axisY.setName("这是Y轴");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        axisY.setTextColor(Color.BLACK);
        data.setAxisYLeft(axisY);  //Y轴设置在左边（左面右面一旦设定就意味着y轴）
        axisY.setHasLines(true);//设置是否显示坐标网格
        axisY.setLineColor(colorLine);//设置网格线的颜色

        //设置行为属性，支持缩放、滑动以及平移
        lineChartView.setInteractive(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL);
        lineChartView.setMaxZoom((float) 4);//最大方法比例
        lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChartView.setLineChartData(data);
    }

}
