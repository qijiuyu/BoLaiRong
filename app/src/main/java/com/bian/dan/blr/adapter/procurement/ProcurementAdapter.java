package com.bian.dan.blr.adapter.procurement;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Procurement;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcurementAdapter extends BaseAdapter {

    private Activity activity;
    private List<Procurement.ListBean> list;

    public ProcurementAdapter(Activity activity,List<Procurement.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_procurement, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Procurement.ListBean listBean=list.get(position);
        holder.tvCode.setText(Html.fromHtml("采购单号：<font color=\"#000000\">"+listBean.getPurcOrder()+"</font>"));
        holder.tvName.setText(Html.fromHtml("采购员：<font color=\"#000000\">"+listBean.getPurcName()+"</font>"));
        holder.tvTime.setText(Html.fromHtml("采购日期：<font color=\"#000000\">"+listBean.getPurcDate()+"</font>"));
        holder.tvTime2.setText(listBean.getCreateDate());
        holder.tvStatus.setText(listBean.getStateStr());
        switch (listBean.getState()){
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
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_time2)
        TextView tvTime2;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

