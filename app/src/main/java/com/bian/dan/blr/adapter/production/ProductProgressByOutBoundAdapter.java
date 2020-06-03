package com.bian.dan.blr.adapter.production;

import android.app.Activity;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.ProductProgress;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductProgressByOutBoundAdapter extends BaseAdapter {

    private Activity activity;
    private List<ProductProgress.OutBoundList> list;
    public ProductProgressByOutBoundAdapter(Activity activity, List<ProductProgress.OutBoundList> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_add_outbound_by_product, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ProductProgress.OutBoundList outBoundList= list.get(position);
        if(!TextUtils.isEmpty(outBoundList.getBatchNo())){
            holder.tvBatchNo.setVisibility(View.VISIBLE);
            holder.tvBatchNo.setText(Html.fromHtml("批次：<font color=\"#000000\">" + outBoundList.getBatchNo() + "</font>"));
        }else{
            holder.tvBatchNo.setVisibility(View.GONE);
        }
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + outBoundList.getGoodsName() + "</font>"));
        holder.tvSpec.setText(outBoundList.getBrand()+"/"+outBoundList.getSpec());
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + outBoundList.getUnitStr() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + outBoundList.getNum() + "</font>"));
        if(!TextUtils.isEmpty(outBoundList.getProp2())){
            holder.tvTime.setVisibility(View.VISIBLE);
            holder.tvTime.setText(Html.fromHtml("日期：<font color=\"#000000\">" + outBoundList.getProp2() + "</font>"));
        }else{
            holder.tvTime.setVisibility(View.GONE);
        }
        holder.tvRemark.setText("备注：" + outBoundList.getMemo());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_batchNo)
        TextView tvBatchNo;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_spec)
        TextView tvSpec;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_remark)
        TextView tvRemark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

