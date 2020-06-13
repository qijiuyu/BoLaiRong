package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Goods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LedTable_Material_Adapter extends BaseAdapter {

    private Activity activity;
    private List<Goods> list;

    public LedTable_Material_Adapter(Activity activity, List<Goods> list) {
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
        Goods goods = list.get(position);
        if (goods.getReceiveType() == 1) {
            holder.tvGetType.setText(Html.fromHtml("领取方式：<font color=\"#000000\">取新</font>"));
        } else {
            holder.tvGetType.setText(Html.fromHtml("领取方式：<font color=\"#000000\">还旧</font>"));
        }
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + goods.getName() + "</font>"));
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + goods.getUnitStr() + "</font>"));
        holder.tvBrand.setText(Html.fromHtml("牌号：<font color=\"#000000\">" + goods.getBrand() + "</font>"));
        holder.tvSpec.setText(Html.fromHtml("规格/型号：<font color=\"#000000\">" + goods.getSpec() + "</font>"));
        holder.tvStockType.setText(Html.fromHtml("仓库类型：<font color=\"#000000\">" + goods.getStockName() + "</font>"));
        holder.tvBatchNo.setText(Html.fromHtml("批次：<font color=\"#000000\">" + goods.getBatchNo() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("领取数量/更换数量：<font color=\"#000000\">" + goods.getNum() + "</font>"));
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

