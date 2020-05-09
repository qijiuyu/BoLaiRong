package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Contract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConstractManagerAdapter extends BaseAdapter {

    private Activity activity;
    private List<Contract.ListBean> list;
    public ConstractManagerAdapter(Activity activity,List<Contract.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_contract_manager, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Contract.ListBean listBean=list.get(position);
        holder.tvCode.setText(Html.fromHtml("合同编号：<font color=\"#000000\">"+listBean.getProp2()+"</font>"));
        holder.tvName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">"+listBean.getCustomerName()+"</font>"));
        holder.tvSignTime.setText(Html.fromHtml("签订时间：<font color=\"#000000\">"+listBean.getSignedTime().split(" ")[0]+"</font>"));
        holder.tvMoney.setText(Html.fromHtml("订单金额：<font color=\"#000000\">"+listBean.getAmount()+"</font>"));
        holder.tvTime.setText(listBean.getCreateDate());
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_sign_time)
        TextView tvSignTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

