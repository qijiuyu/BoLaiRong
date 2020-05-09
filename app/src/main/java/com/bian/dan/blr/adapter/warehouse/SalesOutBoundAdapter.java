package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesOutBoundAdapter extends BaseAdapter {

    private Activity activity;

    public SalesOutBoundAdapter(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 3;
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_sales_outbound, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_contact)
        TextView tvContact;
        @BindView(R.id.tv_mobile)
        TextView tvMobile;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

