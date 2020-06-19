package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.ConstractDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayMentAdapter extends BaseAdapter {

    private Activity activity;
    private List<ConstractDetails.PayMent> list;

    public PayMentAdapter(Activity activity, List<ConstractDetails.PayMent> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_payment, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ConstractDetails.PayMent payMent = list.get(position);
        holder.tvCreatePeople.setText(Html.fromHtml("操作人：<font color=\"#000000\">" + payMent.getCreateName() + "</font>"));
        holder.tvCreateTime.setText(Html.fromHtml("操作时间：<font color=\"#000000\">" + payMent.getCreateDate() + "</font>"));
        holder.tvMoney.setText(Html.fromHtml("付款金额(元)：<font color=\"#000000\">" + payMent.getAmount() + "</font>"));
        holder.tvTime.setText(Html.fromHtml("付款时间：<font color=\"#000000\">" + payMent.getPayDate() + "</font>"));
        holder.tvRemark.setText(Html.fromHtml("备注：<font color=\"#000000\">" + payMent.getMemo() + "</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_create_people)
        TextView tvCreatePeople;
        @BindView(R.id.tv_create_time)
        TextView tvCreateTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_remark)
        TextView tvRemark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

