package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.LedTableDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LedTable_Details_Material_Adapter extends BaseAdapter {

    private Activity activity;
    private List<LedTableDetails.ListBean> list;

    public LedTable_Details_Material_Adapter(Activity activity, List<LedTableDetails.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_add_material_ladtable, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        LedTableDetails.ListBean listBean = list.get(position);
        holder.tvGetType.setText(Html.fromHtml("领取方式：<font color=\"#000000\">"+listBean.getTypeValue()+"</font>"));
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + listBean.getGoodsName() + "</font>"));
//        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + goods.getUnitStr() + "</font>"));
//        holder.tvBrand.setText(goods.getBrand());
//        holder.tvSpec.setText(goods.getSpec());
        holder.tvStockType.setText(Html.fromHtml("仓库类型：<font color=\"#000000\">" + listBean.getStockTypeStr() + "</font>"));
        holder.tvBatchNo.setText(Html.fromHtml("批次：<font color=\"#000000\">" + listBean.getBatchNo() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("领取数量/更换数量：<font color=\"#000000\">" + listBean.getNum() + "</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_get_type)
        TextView tvGetType;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_brand)
        TextView tvBrand;
        @BindView(R.id.tv_spec)
        TextView tvSpec;
        @BindView(R.id.tv_stockType)
        TextView tvStockType;
        @BindView(R.id.tv_batchNo)
        TextView tvBatchNo;
        @BindView(R.id.tv_num)
        TextView tvNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

