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

public class InventoryDetailsAdapter extends BaseAdapter {

    private Activity activity;

    public InventoryDetailsAdapter(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 5;
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_inventory_details, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_brand_spce)
        TextView tvBrandSpce;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_top)
        TextView tvTop;
        @BindView(R.id.tv_bottom)
        TextView tvBottom;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

