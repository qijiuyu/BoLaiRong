package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.OutBoundDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OutBoundGoodAdapter extends BaseAdapter {

    private Activity activity;
    private List<OutBoundDetails.GoodList> list;
    public OutBoundGoodAdapter(Activity activity, List<OutBoundDetails.GoodList> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null ? 0 : list.size();
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_add_product, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        OutBoundDetails.GoodList goodList=list.get(position);
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">"+goodList.getGoodsName()+"</font>"));
        holder.tvBrand.setText(goodList.getBrand());
        holder.tvSpec.setText(goodList.getSpec());
        holder.tvUnit.setText(goodList.getUnitStr());
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">"+goodList.getNum()+"</font>"));
        holder.tvPrice.setText(Html.fromHtml("单价：<font color=\"#000000\">"+goodList.getProp1()+"元</font>"));
        holder.tvMoney.setText(Html.fromHtml("金额：<font color=\"#FF4B4C\">"+goodList.getProp2()+"元</font>"));
        holder.tvRemark.setText("备注："+goodList.getMemo());
        if(goodList.getProp3().equals("1")){
            holder.tvInvoice.setText(Html.fromHtml("是否开票：<font color=\"#000000\">是</font>"));
        }else{
            holder.tvInvoice.setText(Html.fromHtml("是否开票：<font color=\"#000000\">否</font>"));
        }
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_brand)
        TextView tvBrand;
        @BindView(R.id.tv_spec)
        TextView tvSpec;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_monety)
        TextView tvMoney;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.tv_invoice)
        TextView tvInvoice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

