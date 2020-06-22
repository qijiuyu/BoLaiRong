package com.bian.dan.blr.adapter.financial;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.EntryBonus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryBonusAdapter extends BaseAdapter {

    private Activity activity;
    private List<EntryBonus.ListBean> list;

    public EntryBonusAdapter(Activity activity, List<EntryBonus.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_entry_bonus, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        EntryBonus.ListBean listBean = list.get(position);
        holder.tvName.setText(listBean.getCreateName());
        holder.tvDepartment.setText(String.valueOf(listBean.getDeptName()));
        holder.tvMoney.setText(listBean.getIncome());
        holder.tvMonth.setText(listBean.getMonth());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_department)
        TextView tvDepartment;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_month)
        TextView tvMonth;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

