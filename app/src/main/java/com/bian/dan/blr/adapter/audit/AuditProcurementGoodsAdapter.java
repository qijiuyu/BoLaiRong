package com.bian.dan.blr.adapter.audit;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.ProcurementDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuditProcurementGoodsAdapter extends BaseAdapter {

    private Activity activity;
    private List<ProcurementDetails.GoodList> list;

    public AuditProcurementGoodsAdapter(Activity activity, List<ProcurementDetails.GoodList> list) {
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
        ProcurementDetails.GoodList goodList = list.get(position);
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + goodList.getGoodsName() + "</font>"));
        holder.tvSpec.setText(Html.fromHtml("规格/型号：<font color=\"#000000\">" + goodList.getSpec() + "</font>"));
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + goodList.getUnitStr() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + goodList.getNum() + "</font>"));
        holder.tvPrice.setText(Html.fromHtml("单价(元)：<font color=\"#000000\">" + goodList.getUnitPrice() + "</font>"));
        holder.tvMoney.setText(Html.fromHtml("金额(元)：<font color=\"#FF4B4C\">" + goodList.getAmount() + "</font>"));

        holder.tvSupplierName.setText(Html.fromHtml("供应商名称：<font color=\"#000000\">" + goodList.getSupplierName() + "</font>"));
        holder.tvContact.setText(Html.fromHtml("联系人：<font color=\"#000000\">" + goodList.getContacts() + "</font>"));
        holder.tvMobile.setText(Html.fromHtml("电话：<font color=\"#000000\">" + goodList.getPhone() + "</font>"));

        holder.tvAddress.setText(Html.fromHtml("地址：<font color=\"#000000\">" + goodList.getAddress() + "</font>"));
        if (goodList.getPayType() == 1) {
            holder.tvPayType.setText(Html.fromHtml("付款方式：<font color=\"#000000\">全款</font>"));
        } else {
            holder.tvPayType.setText(Html.fromHtml("付款方式：<font color=\"#000000\">分期</font>"));
        }
        holder.tvPayTime.setText(Html.fromHtml("付款时间：<font color=\"#000000\">" + goodList.getPayDate().split(" ")[0] + "</font>"));
        holder.tvMemo.setText(Html.fromHtml("备注：<font color=\"#000000\">" + goodList.getMemo() + "</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_spec)
        TextView tvSpec;
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
        @BindView(R.id.tv_mobile)
        TextView tvMobile;
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

