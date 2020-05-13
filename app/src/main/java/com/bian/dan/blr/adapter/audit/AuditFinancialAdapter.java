package com.bian.dan.blr.adapter.audit;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Financial;
import com.zxdc.utils.library.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuditFinancialAdapter extends BaseAdapter {

    private Activity activity;
    private List<Financial.ListBean> list;
    public AuditFinancialAdapter(Activity activity, List<Financial.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_financial, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Financial.ListBean listBean=list.get(position);
        holder.tvName.setText(Html.fromHtml("申请人：<font color=\"#000000\">"+listBean.getName()+"</font>"));
        holder.tvDes.setText(Html.fromHtml("款项用途及金额：<font color=\"#000000\">"+listBean.getMemo()+"</font>"));
        holder.tvMoney.setText(Html.fromHtml("金额：<font color=\"#000000\">"+ Util.setDouble(listBean.getAmount(),2) +"</font>"));
        holder.tvTime.setText(listBean.getCreateDate());
        switch (listBean.getState()){
            case 0:
                 holder.tvStatus.setText("未审批");
                 holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_FE8E2C));
                 break;
            case 1:
                holder.tvStatus.setText("审批通过");
                holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_70DF5D));
                break;
            case 2:
                holder.tvStatus.setText("审批未通过");
                holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_FF4B4C));
                break;
            default:
                break;
        }
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

