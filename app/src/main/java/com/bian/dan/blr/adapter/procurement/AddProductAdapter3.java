package com.bian.dan.blr.adapter.procurement;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.procurement.AddProcurementActivity;
import com.zxdc.utils.library.bean.Goods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProductAdapter3 extends BaseAdapter {

    private AddProcurementActivity activity;
    private List<Goods> list;

    public AddProductAdapter3(AddProcurementActivity activity, List<Goods> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_procurement_goods, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Goods goods = list.get(position);
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + goods.getName() + "</font>"));
        holder.tvBrand.setText(goods.getSpec());
        holder.tvUnit.setText(goods.getUnitStr());
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + goods.getNum() + "</font>"));
        holder.tvPrice.setText(Html.fromHtml("单价(元)：<font color=\"#000000\">" + goods.getPrice() + "</font>"));
        holder.tvMoney.setText(Html.fromHtml("金额(元)：<font color=\"#FF4B4C\">" + goods.getTotalMoney() + "</font>"));

        holder.tvSupplierName.setText(Html.fromHtml("供应商名称：<font color=\"#000000\">" + goods.getCompany() + "</font>"));
        holder.tvContact.setText(goods.getContract() + "   " + goods.getMobile());
        holder.tvAddress.setText(Html.fromHtml("地址：<font color=\"#000000\">" + goods.getAddress() + "</font>"));
        if (goods.getPayType() == 1) {
            holder.tvPayType.setText(Html.fromHtml("付款方式：<font color=\"#000000\">全款</font>"));
        } else {
            holder.tvPayType.setText(Html.fromHtml("付款方式：<font color=\"#000000\">分期</font>"));
        }
        holder.tvPayTime.setText(Html.fromHtml("付款时间：<font color=\"#000000\">" + goods.getPayTime().split(" ")[0] + "</font>"));
        holder.tvMemo.setText(Html.fromHtml("备注：<font color=\"#000000\">" + goods.getMemo() + "</font>"));

        /**
         * 删除
         */
        holder.imgDelete.setVisibility(View.VISIBLE);
        holder.imgDelete.setTag(goods);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Goods goods= (Goods) v.getTag();
                list.remove(goods);
                notifyDataSetChanged();
            }
        });

        /**
         * 编辑
         */
        holder.imgEdit.setVisibility(View.VISIBLE);
        holder.imgEdit.setTag(goods);
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Goods goods= (Goods) v.getTag();
                activity.gotoEdit(goods);
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_brand)
        TextView tvBrand;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_supplier_name)
        TextView tvSupplierName;
        @BindView(R.id.tv_contact)
        TextView tvContact;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_pay_type)
        TextView tvPayType;
        @BindView(R.id.tv_pay_time)
        TextView tvPayTime;
        @BindView(R.id.tv_memo)
        TextView tvMemo;
        @BindView(R.id.img_edit)
        ImageView imgEdit;
        @BindView(R.id.img_delete)
        ImageView imgDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

