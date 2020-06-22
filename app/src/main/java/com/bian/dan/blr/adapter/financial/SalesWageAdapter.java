package com.bian.dan.blr.adapter.financial;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.SalesWage;
import com.zxdc.utils.library.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesWageAdapter extends BaseAdapter {

    private Activity activity;
    private List<SalesWage.ListBean> list;

    public SalesWageAdapter(Activity activity, List<SalesWage.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_sales_wage, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SalesWage.ListBean listBean = list.get(position);
        holder.tvName.setText(listBean.getSalesName());
        holder.tvAmount.setText(String.valueOf(listBean.getAmount()));
        holder.tvIncome.setText(String.valueOf(listBean.getIncome()));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.tv_income)
        TextView tvIncome;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

