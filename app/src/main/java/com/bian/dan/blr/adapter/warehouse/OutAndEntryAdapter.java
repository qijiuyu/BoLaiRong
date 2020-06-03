package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.OutBoundProduct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OutAndEntryAdapter extends BaseAdapter {

    private Activity activity;
    private List<OutBoundProduct.ListBean> list;

    public OutAndEntryAdapter(Activity activity, List<OutBoundProduct.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_out_and_entry, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        OutBoundProduct.ListBean listBean = list.get(position);
        holder.tvCode.setText(Html.fromHtml("生产计划：<font color=\"#000000\">" + listBean.getPlanCode() + "</font>"));
        holder.tvName.setText(Html.fromHtml("申请人：<font color=\"#000000\">" + listBean.getCreateName() + "</font>"));
        switch (listBean.getOutStatus()) {
            case 0:
                holder.tvOutStatus.setText(Html.fromHtml("出库状态：<font color=\"#FE8E2C\">"+listBean.getOutStatusStr()+"</font>"));
                break;
            case 1:
                holder.tvOutStatus.setText(Html.fromHtml("出库状态：<font color=\"#70DF5D\">"+listBean.getOutStatusStr()+"</font>"));
                break;
            case 2:
                holder.tvOutStatus.setText(Html.fromHtml("出库状态：<font color=\"#FF4B4C\">"+listBean.getOutStatusStr()+"</font>"));
                break;
            default:
                break;
        }
        switch (listBean.getEntryStatus()){
            case 0:
                holder.tvPutStatus.setText(Html.fromHtml("入库状态：<font color=\"#FE8E2C\">"+listBean.getEntryStatusStr()+"</font>"));
                break;
            case 1:
                holder.tvPutStatus.setText(Html.fromHtml("入库状态：<font color=\"#FE8E2C\">"+listBean.getEntryStatusStr()+"</font>"));
                break;
            case 2:
                holder.tvPutStatus.setText(Html.fromHtml("入库状态：<font color=\"#70DF5D\">"+listBean.getEntryStatusStr()+"</font>"));
                break;
            default:
                break;
        }
        holder.tvTime.setText(listBean.getCreateDate());
        holder.tvDepartment.setText(Html.fromHtml("部门：<font color=\"#000000\">" + listBean.getDeptName() + "</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_department)
        TextView tvDepartment;
        @BindView(R.id.tv_out_status)
        TextView tvOutStatus;
        @BindView(R.id.tv_put_status)
        TextView tvPutStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

