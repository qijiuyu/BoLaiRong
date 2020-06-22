package com.bian.dan.blr.adapter.financial;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.EntryBonusDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryBonusDetailsAdapter extends BaseAdapter {

    private Activity activity;
    private List<EntryBonusDetails.ListBean> list;

    public EntryBonusDetailsAdapter(Activity activity, List<EntryBonusDetails.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_entry_bonus_details, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        EntryBonusDetails.ListBean listBean = list.get(position);
        holder.tvCustomerName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">" + listBean.getCustomerName() + "</font>"));
        holder.tvAuditName.setText(Html.fromHtml("审批人员名称：<font color=\"#000000\">" + listBean.getApprovalName() + "</font>"));
        holder.tvPrivateName.setText(Html.fromHtml("客户归属人：<font color=\"#000000\">" + listBean.getPrivateName() + "</font>"));
        holder.tvMoney.setText(Html.fromHtml("录入奖金：<font color=\"#000000\">" + listBean.getEntryFee() + "</font>"));
        holder.tvTime.setText(Html.fromHtml("审批时间：<font color=\"#000000\">" + listBean.getApprovalDate() + "</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_customer_name)
        TextView tvCustomerName;
        @BindView(R.id.tv_audit_name)
        TextView tvAuditName;
        @BindView(R.id.tv_privateName)
        TextView tvPrivateName;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

