package com.bian.dan.blr.activity.statistical;

import com.zxdc.utils.library.base.BaseActivity;

/**
 * 统计
 */
public class StatisticalActivity extends BaseActivity {

//    @BindView(R.id.scrollView)
//    ScrollView scrollView;
//    @BindView(R.id.tv_income)
//    TextView tvIncome;
//    @BindView(R.id.tv_spending)
//    TextView tvSpending;
//    @BindView(R.id.view_fiscal)
//    PieChartView viewFiscal;
//    @BindView(R.id.view_customer)
//    AnimatedPieView viewCustomer;
//    @BindView(R.id.view_order)
//    LineChartView viewOrder;
//    @BindView(R.id.view_sales_money)
//    LineChartView viewSalesMoney;
//    @BindView(R.id.view_material)
//    LineChartView viewMaterial;
//    @BindView(R.id.view_product)
//    ColumnChartView viewProduct;
//    private StatisticalPersenter statisticalPersenter;
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_statistical);
//        ButterKnife.bind(this);
//        initView();
//        showViewFiscal();
//        showViewCustomer();
//        showViewOrder();
//        showViewSalesMoney();
//        showMaterial();
//        showProduct();
//
//    }
//
//    /**
//     * 初始化
//     */
//    private void initView(){
//        statisticalPersenter=new StatisticalPersenter(this);
//    }
//
//
//    /**
//     * 显示财政收支对比
//     */
//    private void showViewFiscal() {
//        List<SliceValue> values = new ArrayList<SliceValue>();
//        SliceValue sliceValue = new SliceValue(70, Color.parseColor("#FE8E2C"));
//        SliceValue sliceValue2 = new SliceValue(30, Color.parseColor("#47C9FB"));
//        values.add(sliceValue);
//        values.add(sliceValue2);
//        PieChartData data = new PieChartData(values);
//        data.setHasCenterCircle(true);
//        data.setCenterCircleScale(0.7f);//设置环形的大小级别
//
//
//        data.setCenterText1("收入");
//        data.setCenterText2("70%");
//
//        data.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
//                (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));
//        data.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
//                (int) getResources().getDimension(R.dimen.pie_chart_text2_size)));
//        data.setCenterText2Color(Color.parseColor("#FE8E2C"));
//
//        viewFiscal.startDataAnimation();
//        viewFiscal.startDataAnimation(2000);
//        viewFiscal.setPieChartData(data);
//    }
//
//
//    /**
//     * 显示客户状态统计
//     */
//    private void showViewCustomer() {
//        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
//        List<SimplePieInfo> list=new ArrayList<>();
//        list.add(new SimplePieInfo(30, Color.parseColor("#47C9FB"), "成交客户"));
//        list.add(new SimplePieInfo(20, Color.parseColor("#FE8E2C"), "培养客户"));
//        list.add(new SimplePieInfo(30, Color.parseColor("#007AFF"), "潜在客户"));
//        list.add(new SimplePieInfo(10, Color.parseColor("#FF4B4C"), "理想客户"));
//        list.add(new SimplePieInfo(10, Color.parseColor("#47C9FB"), "优质客户"));
//        for (int i=0;i<list.size();i++){
//             config.addData(list.get(i));
//        }
//        config.strokeWidth(70)// 圆弧（甜甜圈）宽度
//                .duration(2000)// 动画时间
//                .startAngle(-90f)// 开始的角度
//                .drawText(true)// 是否绘制文字描述
//                .textSize(20)// 绘制的文字大小
//                .textMargin(8)// 绘制文字与导航线的距离
//                .autoSize(true)// 自动测量甜甜圈半径
//                .pieRadius(130)// 甜甜圈半径
//                .pieRadiusRatio(0.8f)// 甜甜圈半径占比
//                .guidePointRadius(2)// 设置描述文字的开始小点的大小
//                .guideLineWidth(4)// 设置描述文字的指示线宽度
//                .guideLineMarginStart(9)// 设置描述文字的指示线开始距离外圆半径的大小
//                .textGravity(AnimatedPieViewConfig.ABOVE)// 设置描述文字方向 【-AnimatedPieViewConfig.ABOVE：文字将会在导航线上方绘制-AnimatedPieViewConfig.BELOW：文字在导航线下方绘制-AnimatedPieViewConfig.ALIGN：文字与导航线对齐-AnimatedPieViewConfig.ECTOPIC：文字在1、2象限部分绘制在线的上方，在3、4象限绘制在线的下方】
//                .canTouch(true)// 是否允许甜甜圈点击放大
//                .splitAngle(0);// 甜甜圈间隙角度
//        viewCustomer.applyConfig(config);
//        viewCustomer.start();
//    }
//
//
//    /**
//     * 显示销售订单模块
//     */
//    private void showViewOrder(){
//        String[] date = {"2020-01", "2020-02", "2020-03", "2020-04", "2020-05", "2020-06", "2020-07", "2020-059", "2020-09", "2020-10"};//X轴的数据
//        float[] score = {20, 10, 5, 12, 10, 7, 5,17, 12, 6};//x轴对应的y值
//        statisticalPersenter.setChartLine(date,score,Color.parseColor("#4AC9FB"),Color.parseColor("#DAF5FE"),viewOrder);
//        // 下面的这个api控制 滑动
//        Viewport v = new Viewport(viewOrder.getMaximumViewport());
//        v.bottom = 0;
//        v.top = 20;
//        viewOrder.setMaximumViewport(v);
//        v.left = date.length-7;
//        v.right = date.length-1;
//        viewOrder.setCurrentViewport(v);
//    }
//
//
//    /**
//     * 显示销售金额模块
//     */
//    private void showViewSalesMoney(){
//        String[] date = {"2020-01", "2020-02", "2020-03", "2020-04", "2020-05", "2020-06", "2020-07", "2020-059", "2020-09", "2020-10"};//X轴的数据
//        float[] score = {70, 30, 60, 50, 10, 40, 20,70, 30, 60};//x轴对应的y值
//        statisticalPersenter.setChartLine(date,score,Color.parseColor("#F6B467"),Color.parseColor("#FCEFDF"),viewSalesMoney);
//        // 下面的这个api控制 滑动
//        Viewport v = new Viewport(viewSalesMoney.getMaximumViewport());
//        v.bottom = 0;
//        v.top = 70;
//        viewSalesMoney.setMaximumViewport(v);
//        v.left = date.length-7;
//        v.right = date.length-1;
//        viewSalesMoney.setCurrentViewport(v);
//    }
//
//
//    /**
//     * 显示物料使用趋势模块
//     */
//    private void showMaterial(){
//        String[] date = {"2020-01", "2020-02", "2020-03", "2020-04", "2020-05", "2020-06", "2020-07", "2020-059", "2020-09", "2020-10","2020-11"};//X轴的数据
//        float[] score = {500, 300, 200, 100, 600, 800, 500,150, 320, 230,320};//x轴对应的y值
//        statisticalPersenter.setChartLine(date,score,Color.parseColor("#5FDB75"),Color.parseColor("#C9EFC8"),viewMaterial);
//        // 下面的这个api控制 滑动
//        Viewport v = new Viewport(viewMaterial.getMaximumViewport());
//        v.bottom = 0;
//        v.top = 800;
//        viewMaterial.setMaximumViewport(v);
//        v.left = date.length-7;
//        v.right = date.length-1;
//        viewMaterial.setCurrentViewport(v);
//    }
//
//
//    /**
//     * 显示成品统计
//     */
//    private void showProduct(){
//        List<Column> columns = new ArrayList<>();
//        List<SubcolumnValue> values;
//        for (int i = 0; i < 7; ++i) {
//            values=new ArrayList<>();
//            values.add(new SubcolumnValue((float) Math.random() * 50f + 5, Color.parseColor("#04BFF2")));
//            Column column = new Column(values);
//            column.setHasLabels(false);
//            column.setHasLabelsOnlyForSelected(false);
//            columns.add(column);
//        }
//        ColumnChartData data = new ColumnChartData(columns);
//
//        //X轴
//        //底部标题
//        List<String> title = new ArrayList<>();
//        title.add("星期一");
//        title.add("星期二");
//        title.add("星期三");
//        title.add("星期四");
//        title.add("星期五");
//        title.add("星期六");
//        title.add("星期日");
//        List<AxisValue> axisXValues = new ArrayList<>();
//        for (int i = 0; i < title.size(); i++) {
//            //设置X轴的柱子所对应的属性名称(底部文字)
//            axisXValues.add(new AxisValue(i).setLabel(title.get(i)));
//        }
//        Axis axisBottom = new Axis(axisXValues);
//        //字体大小
//        axisBottom.setTextSize(10);
//        //距离各标签之间的距离,包括离Y轴间距 (0-32之间)
//        axisBottom.setMaxLabelChars(0);
//        //设置x轴在底部显示
//        data.setAxisXBottom(axisBottom);
//
//
//        //Y轴
//        Axis axisLeft = new Axis();
//        //是否显示X轴的网格线
//        axisLeft.setHasLines(true);
//        axisLeft.setLineColor(Color.parseColor("#ABDFF8"));//设置网格线的颜色
//        //字体大小
//        axisBottom.setTextSize(10);
//        data.setAxisYLeft(axisLeft);
//
//        data.setFillRatio(0.5f);
//        viewProduct.setColumnChartData(data);
//        viewProduct.setInteractive(true);
//        viewProduct.setZoomType(ZoomType.HORIZONTAL);
//        viewProduct.setMaxZoom((float) 4);//最大方法比例
//        viewProduct.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
//
//        // 下面的这个api控制 滑动
//        Viewport v = new Viewport(viewProduct.getMaximumViewport());
//        v.bottom = 0;
//        v.top = 70;
//        viewProduct.setMaximumViewport(v);
//        v.left = 0;
//        v.right = 9;
//        viewProduct.setCurrentViewport(v);
//    }
}
