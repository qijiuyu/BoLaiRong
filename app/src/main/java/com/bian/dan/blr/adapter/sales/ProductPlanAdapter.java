package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.ProductPlan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductPlanAdapter extends BaseAdapter {

    private Activity activity;
    private List<ProductPlan.ListBean> list;
    public ProductPlanAdapter(Activity activity,List<ProductPlan.ListBean> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null ? 0 : list.size();
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_product_plan, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ProductPlan.ListBean listBean=list.get(position);
        holder.tvPlan.setText(Html.fromHtml("生产计划：<font color=\"#000000\">"+listBean.getPlanCode()+"</font>"));
        holder.tvDeliveryTime.setText(Html.fromHtml("交付日期：<font color=\"#000000\">"+listBean.getDeliveryDate().split(" ")[0]+"</font>"));
        holder.tvRemark.setText(Html.fromHtml("备注：<font color=\"#000000\">"+listBean.getMemo()+"</font>"));
        holder.tvTime.setText(listBean.getCreateDate());
        holder.tvStatus.setText(listBean.getStatusStr());
        switch (listBean.getStatus()){
            case 0:
                 holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_FE8E2C));
                 break;
            case 1:
                holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_70DF5D));
                break;
            case 2:
                holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_FF4B4C));
                break;
            default:
                break;
        }
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_plan)
        TextView tvPlan;
        @BindView(R.id.tv_delivery_time)
        TextView tvDeliveryTime;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

