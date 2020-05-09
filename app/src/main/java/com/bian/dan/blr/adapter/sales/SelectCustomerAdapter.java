package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Customer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCustomerAdapter extends BaseAdapter {

    private Activity activity;
    private List<Customer> list;
    public SelectCustomerAdapter(Activity activity, List<Customer> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_select_customer, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Customer customer=list.get(position);
        holder.tvName.setText(customer.getCustomerName());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

