package com.bian.dan.blr.adapter.procurement;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.procurement.AddProductActivity5;
import com.bian.dan.blr.persenter.procurement.AddSupplierPersenter;
import com.zxdc.utils.library.bean.Goods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProductAdapter5 extends BaseAdapter {

    private Activity activity;
    private List<Goods> list;
    private AddSupplierPersenter addSupplierPersenter;

    public AddProductAdapter5(Activity activity, List<Goods> list,AddSupplierPersenter addSupplierPersenter) {
        super();
        this.activity = activity;
        this.list = list;
        this.addSupplierPersenter=addSupplierPersenter;
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
        Goods goods = list.get(position);
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + goods.getName() + "</font>"));
        holder.tvBrand.setText(goods.getBrand());
        holder.tvSpec.setText(goods.getSpec());
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + goods.getUnitStr() + "</font>"));
        holder.tvPrice.setText(Html.fromHtml("单价：<font color=\"#000000\">" + goods.getPrice() + "</font>"));
        holder.tvRemark.setText(Html.fromHtml("备注：<font color=\"#000000\">" + goods.getMemo() + "</font>"));

        if(goods.getId()==0){
            holder.imgEdit.setVisibility(View.GONE);
            holder.imgDelete.setVisibility(View.GONE);
        }else{
            holder.imgEdit.setVisibility(View.VISIBLE);
            holder.imgDelete.setVisibility(View.VISIBLE);
        }

        /**
         * 编辑
         */
        holder.imgEdit.setTag(goods);
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Goods goods= (Goods) v.getTag();
                Intent intent=new Intent(activity, AddProductActivity5.class);
                intent.putExtra("goods",goods);
                activity.startActivityForResult(intent,200);
            }
        });


        /**
         * 删除
         */
        holder.imgDelete.setTag(goods);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Goods goods= (Goods) v.getTag();
                addSupplierPersenter.deleteSupplierGoods(goods);
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
        @BindView(R.id.tv_spec)
        TextView tvSpec;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
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

