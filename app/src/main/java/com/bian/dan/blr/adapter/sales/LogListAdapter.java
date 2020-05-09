package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Log.ListBean> list;
    public LogListAdapter(Activity activity,List<Log.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_log, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Log.ListBean listBean=list.get(position);
        holder.tvName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">"+listBean.getCustomerName()+"</font>"));
        holder.tvPeople.setText(Html.fromHtml("联系人：<font color=\"#000000\">"+listBean.getContacts()+"</font>"));
        holder.tvMobile.setText(Html.fromHtml("手机号：<font color=\"#000000\">"+listBean.getPhone()+"</font>"));
        holder.tvResult.setText(Html.fromHtml("跟进结果：<font color=\"#000000\">"+listBean.getFollowResult()+"</font>"));
        holder.tvTime.setText(listBean.getCreateDate());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_people)
        TextView tvPeople;
        @BindView(R.id.tv_mobile)
        TextView tvMobile;
        @BindView(R.id.tv_result)
        TextView tvResult;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

