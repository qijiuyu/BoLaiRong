package com.bian.dan.blr.activity.main.procurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.audit.AuditProcurementGoodsAdapter;
import com.bian.dan.blr.adapter.procurement.Procurement_Details_EntryGood_Adapter;
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
    @BindView(R.id.tv_right)
    TextView tvRight;
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
    @BindView(R.id.tv_audit_remark)
    TextView tvAuditRemark;
    @BindView(R.id.list_entry)
    MeasureListView listEntry;
    @BindView(R.id.lin_audit)
    LinearLayout linAudit;
    @BindView(R.id.lin_entry)
    LinearLayout linEntry;
    private Procurement.ListBean listBean;
    //详情对象
    private ProcurementDetails.DetailsBean detailsBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurement_details);
        ButterKnife.bind(this);
        initView();
        //获取采购单详情
        getProcurementDetails();
    }


    @OnClick({R.id.lin_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //编辑
            case R.id.tv_right:
                if(detailsBean==null){
                    return;
                }
                Intent intent=new Intent(this,AddProcurementActivity.class);
                intent.putExtra("detailsBean",detailsBean);
                startActivityForResult(intent,1000);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("详情");
        tvRight.setText("编辑");
        listBean= (Procurement.ListBean) getIntent().getSerializableExtra("listBean");
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
                    detailsBean=procurementDetails.getData();
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
                    listView.setAdapter(new AuditProcurementGoodsAdapter(activity,detailsBean.getPurchaseDetailList()));
                    /**
                     * 计算总数量，总金额
                     */
                    int totalNum=0;
                    double totalMoney=0;
                    for (int i=0;i<detailsBean.getPurchaseDetailList().size();i++){
                        final ProcurementDetails.GoodList goodList=detailsBean.getPurchaseDetailList().get(i);
                        totalNum=totalNum+goodList.getNum();
                        totalMoney= BigDecimalUtil.add(totalMoney,goodList.getAmount());
                    }
                    tvProductNum.setText("数量："+totalNum);
                    tvProductMoney.setText(Html.fromHtml("金额：<font color=\"#FF4B4C\">" + totalMoney + "元</font>"));


                    /**
                     * 审核信息
                     */
                    if(detailsBean.getState()>0){
                        linAudit.setVisibility(View.VISIBLE);
                        tvRight.setVisibility(View.GONE); //审核后就不能再编辑了
                        tvAudit.setText(Html.fromHtml("审核：<font color=\"#000000\">" + detailsBean.getApproveName()+ "</font>"));
                        tvAuditTime.setText(Html.fromHtml("审核时间：<font color=\"#000000\">" + detailsBean.getProp5()+ "</font>"));
                        tvAuditResult.setText(Html.fromHtml("审核结果：<font color=\"#000000\">" + detailsBean.getStateStr()+ "</font>"));
                        tvAuditRemark.setText(Html.fromHtml("审核意见：<font color=\"#000000\">" + detailsBean.getProp4()+ "</font>"));
                    }


                    /**
                     * 入库信息
                     */
                    ProcurementDetails.GoodList goodList=detailsBean.getPurchaseDetailList().get(0);
                    if(goodList.getEntryDetailList().size()>0){
                        linEntry.setVisibility(View.VISIBLE);
                        listEntry.setAdapter(new Procurement_Details_EntryGood_Adapter(activity,detailsBean.getPurchaseDetailList()));
                    }
                }else{
                    ToastUtil.showLong(procurementDetails.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            //编辑成功后，重新刷新界面
            case 1000:
                getProcurementDetails();
                break;
            default:
                break;
        }
    }
}
