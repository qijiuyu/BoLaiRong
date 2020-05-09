package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Device;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Device.ListBean> list;
    public DeviceListAdapter(Activity activity,List<Device.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_device, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Device.ListBean listBean=list.get(position);
        holder.tvName.setText(Html.fromHtml("设备名称：<font color=\"#000000\">"+listBean.getEquipName()+"</font>"));
        holder.tvType.setText(Html.fromHtml("设备类型：<font color=\"#000000\">"+listBean.getTypeName()+"</font>"));
        holder.tvSpec.setText(Html.fromHtml("规格型号：<font color=\"#000000\">"+listBean.getSpec()+"</font>"));
        holder.tvDepartment.setText(Html.fromHtml("归属部门：<font color=\"#000000\">"+listBean.getDeptName()+"</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_spec)
        TextView tvSpec;
        @BindView(R.id.tv_department)
        TextView tvDepartment;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

