package com.bian.dan.blr.adapter.financial;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.WorkerDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkerDetailsAdapter extends BaseAdapter {

    private Activity activity;
    private List<WorkerDetails.ListBean> list;

    public WorkerDetailsAdapter(Activity activity, List<WorkerDetails.ListBean> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder = null;

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_worker_details, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        WorkerDetails.ListBean listBean = list.get(position);
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + listBean.getGoodsName() + "</font>"));
        holder.tvType.setText(Html.fromHtml("物料类型：<font color=\"#000000\">" + listBean.getGoodsTypeStr() + "</font>"));
        holder.tvSpec.setText(Html.fromHtml("规格/型号：<font color=\"#000000\">" + listBean.getSpec() + "</font>"));
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + listBean.getUnitStr() + "</font>"));
        holder.tvStockType.setText(Html.fromHtml("仓库类型：<font color=\"#000000\">" + listBean.getParentStockTypeStr()+"-"+listBean.getStockTypeStr() + "</font>"));
        holder.tvBatchNo.setText(Html.fromHtml("批号：<font color=\"#000000\">" + listBean.getBatchNo() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + listBean.getNum() + "</font>"));
        holder.tvWage.setText(Html.fromHtml("计件工资：<font color=\"#000000\">" + listBean.getWage() + "</font>"));
        holder.tvWaste.setText(Html.fromHtml("废品率(%)：<font color=\"#000000\">" + listBean.getRejectRate() + "</font>"));
        holder.tvTotal.setText(Html.fromHtml("总收入：<font color=\"#000000\">" + listBean.getIncome() + "</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_spec)
        TextView tvSpec;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_stockType)
        TextView tvStockType;
        @BindView(R.id.tv_batchNo)
        TextView tvBatchNo;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_wage)
        TextView tvWage;
        @BindView(R.id.tv_waste)
        TextView tvWaste;
        @BindView(R.id.tv_total)
        TextView tvTotal;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

