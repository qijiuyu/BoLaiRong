package com.bian.dan.blr.adapter.procurement;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Supplier;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupplierAdapter extends BaseAdapter {

    private Activity activity;
    private List<Supplier.ListBean> list;
    public SupplierAdapter(Activity activity,List<Supplier.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_supplier, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Supplier.ListBean listBean=list.get(position);
        holder.tvName.setText(Html.fromHtml("供应商名称：<font color=\"#000000\">"+listBean.getSupplierName()+"</font>"));
        holder.tvContact.setText(Html.fromHtml("联系人：<font color=\"#000000\">"+listBean.getContacts()+"</font>"));
        holder.tvIndustry.setText(Html.fromHtml("所属行业：<font color=\"#000000\">"+listBean.getIndustryStr()+"</font>"));
        holder.tvMobile.setText(Html.fromHtml("电话：<font color=\"#000000\">"+listBean.getPhone()+"</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_industry)
        TextView tvIndustry;
        @BindView(R.id.tv_contact)
        TextView tvContact;
        @BindView(R.id.tv_mobile)
        TextView tvMobile;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

