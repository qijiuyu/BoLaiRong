package com.bian.dan.blr.activity.main.procurement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.audit.AuditProcurementGoodsAdapter;
import com.bian.dan.blr.adapter.procurement.AddProductAdapter3;
import com.bian.dan.blr.adapter.procurement.ProcuDetailsEnterAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Procurement;
import com.zxdc.utils.library.bean.ProcurementDetails;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BigDecimalUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 采购单详情
 */
public class ProcurementDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_pro_name)
    TextView tvProName;
    @BindView(R.id.tv_pro_time)
    TextView tvProTime;
    @BindView(R.id.tv_pro_code)
    TextView tvProCode;
    @BindView(R.id.tv_apply_time)
    TextView tvApplyTime;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.tv_product_money)
    TextView tvProductMoney;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    @BindView(R.id.tv_audit)
    TextView tvAudit;
    @BindView(R.id.tv_audit_time)
    TextView tvAuditTime;
    @BindView(R.id.tv_audit_result)
    TextView tvAuditResult;
    @BindView(R.id.tv_enter)
    TextView tvEnter;
    @BindView(R.id.tv_enter_time)
    TextView tvEnterTime;
    @BindView(R.id.list_outbound)
    MeasureListView listOutbound;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    private Procurement.ListBean listBean;
    private AddProductAdapter3 addProductAdapter3;
    private ProcuDetailsEnterAdapter procuDetailsEnterAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurement_details);
        ButterKnife.bind(this);
        initView();
        //获取采购单详情
        getProcurementDetails();
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        listBean= (Procurement.ListBean) getIntent().getSerializableExtra("listBean");

        procuDetailsEnterAdapter=new ProcuDetailsEnterAdapter(this);
        listOutbound.setAdapter(procuDetailsEnterAdapter);

    }


    /**
     * 获取采购单详情
     */
    public void getProcurementDetails(){
        if(listBean==null){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getProcurementDetails(listBean.getId(), new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                ProcurementDetails procurementDetails= (ProcurementDetails) object;
                if(procurementDetails.isSussess()){
                    ProcurementDetails.DetailsBean detailsBean=procurementDetails.getPurchase();
                    if(detailsBean==null){
                        return;
                    }
                    tvProName.setText(Html.fromHtml("采购员：<font color=\"#000000\">" + detailsBean.getPurcName()+ "</font>"));
                    tvProTime.setText(Html.fromHtml("采购时间：<font color=\"#000000\">" + detailsBean.getPurcDate()+ "</font>"));
                    tvProCode.setText(Html.fromHtml("采购单号：<font color=\"#000000\">" + detailsBean.getPurcOrder()+ "</font>"));
                    tvApplyTime.setText(Html.fromHtml("申请时间：<font color=\"#000000\">" + detailsBean.getCreateDate()+ "</font>"));

                    /**
                     * 产品列表
                     */
                    listView.setAdapter(new AuditProcurementGoodsAdapter(activity,procurementDetails.getPurchaseDetailList()));
                    /**
                     * 计算总数量，总金额
                     */
                    int totalNum=0;
                    double totalMoney=0;
                    for (int i=0;i<procurementDetails.getPurchaseDetailList().size();i++){
                        final ProcurementDetails.GoodList goodList=procurementDetails.getPurchaseDetailList().get(i);
                        totalNum=totalNum+goodList.getNum();
                        totalMoney= BigDecimalUtil.add(totalMoney,goodList.getAmount());
                    }
                    tvProductNum.setText("数量："+totalNum);
                    tvProductMoney.setText(Html.fromHtml("金额：<font color=\"#FF4B4C\">" + totalMoney + "</font>"));


                }else{
                    ToastUtil.showLong(procurementDetails.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
