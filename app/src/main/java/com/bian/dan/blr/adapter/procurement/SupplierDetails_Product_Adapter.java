package com.bian.dan.blr.adapter.procurement;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.SupplierDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupplierDetails_Product_Adapter extends BaseAdapter {

    private Activity activity;
    private List<SupplierDetails.GoodList> list;

    public SupplierDetails_Product_Adapter(Activity activity, List<SupplierDetails.GoodList> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_add_product5, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SupplierDetails.GoodList goodList = list.get(position);
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + goodList.getGoodsName() + "</font>"));
        holder.tvBrand.setText(Html.fromHtml("牌号：<font color=\"#000000\">" + goodList.getBrand() + "</font>"));
        holder.tvSpec.setText(Html.fromHtml("规格/型号：<font color=\"#000000\">" + goodList.getSpec() + "</font>"));
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + goodList.getUnitStr() + "</font>"));
        holder.tvPrice.setText(Html.fromHtml("单价(元)：<font color=\"#000000\">" + goodList.getProp1() + "</font>"));
        holder.tvRemark.setText(Html.fromHtml("备注：<font color=\"#000000\">" + goodList.getMemo() + "</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_brand)
        TextView tvBrand;
        @BindView(R.id.tv_spec)
        TextView tvSpec;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.img_edit)
        ImageView imgEdit;
        @BindView(R.id.img_delete)
        ImageView imgDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

