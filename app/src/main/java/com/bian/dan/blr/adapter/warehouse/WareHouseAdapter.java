package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.warehouse.InventoryDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WareHouseAdapter extends BaseAdapter {

    private Activity activity;

    public WareHouseAdapter(Activity activity) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_warehouse, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(activity, InventoryDetailsActivity.class);
                activity.startActivity(intent);
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_warehouse_type)
        TextView tvWarehouseType;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_play)
        TextView tvPlay;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

