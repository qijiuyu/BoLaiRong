package com.bian.dan.blr.adapter.financial;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Wage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkersWageAdapter extends BaseAdapter {

    private Activity activity;
    private List<Wage.ListBean> list;
    public WorkersWageAdapter(Activity activity,List<Wage.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_workers_wage, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Wage.ListBean listBean=list.get(position);
        holder.tvName.setText(listBean.getUserName());
        holder.tvTotalNum.setText(String.valueOf(listBean.getTotalNum()));
        holder.tvWage.setText(String.valueOf(listBean.getPieceWage()));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_position)
        TextView tvPosition;
        @BindView(R.id.tv_total_num)
        TextView tvTotalNum;
        @BindView(R.id.tv_wage)
        TextView tvWage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

