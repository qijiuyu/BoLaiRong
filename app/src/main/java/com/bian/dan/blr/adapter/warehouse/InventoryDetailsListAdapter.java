package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.InventoryDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryDetailsListAdapter extends BaseAdapter {

    private Activity activity;
    private List<InventoryDetails.ListBean> list;

    public InventoryDetailsListAdapter(Activity activity, List<InventoryDetails.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_inventory_details, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        InventoryDetails.ListBean listBean=list.get(position);
        holder.tvName.setText(listBean.getGoodsName());
        holder.tvDes.setText(listBean.getBrand() + " / " + listBean.getSpec());
        holder.tvInventory.setText(listBean.getTypeStr());
        holder.tvUnit.setText(listBean.getUnitStr());
        holder.tvNum.setText(String.valueOf(listBean.getNum()));
        holder.tvType.setText(listBean.getStockTypeStr());
        holder.tvCode.setText(listBean.getBatchNo());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_inventory)
        TextView tvInventory;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_code)
        TextView tvCode;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

