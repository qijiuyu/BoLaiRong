package com.bian.dan.blr.adapter.financial;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.SalesWageDetails;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesDetailsAdapter extends BaseAdapter {

    private Activity activity;
    private List<SalesWageDetails.ListBean> list;
    public SalesDetailsAdapter(Activity activity, List<SalesWageDetails.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_sales_wage_details, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SalesWageDetails.ListBean listBean = list.get(position);
        holder.tvName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">" + listBean.getCustomerName() + "</font>"));
        holder.tvCode.setText(Html.fromHtml("合同编码：<font color=\"#000000\">" + listBean.getContractCode() + "</font>"));
        holder.tvTime.setText(Html.fromHtml("回款日期：<font color=\"#000000\">" + listBean.getPayDate() + "</font>"));
        holder.tvMoney.setText(Html.fromHtml("回款金额(元)：<font color=\"#000000\">" + listBean.getAmount() + "</font>"));
        holder.tvMemo.setText(Html.fromHtml("回款备注信息：<font color=\"#000000\">" + listBean.getMemo() + "</font>"));
        holder.tvCreatePeople.setText(Html.fromHtml("录入人：<font color=\"#000000\">" + listBean.getCreateName() + "</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_memo)
        TextView tvMemo;
        @BindView(R.id.tv_create_people)
        TextView tvCreatePeople;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

