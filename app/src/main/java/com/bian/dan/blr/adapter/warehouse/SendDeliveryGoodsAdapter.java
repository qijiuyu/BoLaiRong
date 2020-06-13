package com.bian.dan.blr.adapter.warehouse;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.warehouse.SendDeliveryActivity;
import com.zxdc.utils.library.bean.OutBoundDetails;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendDeliveryGoodsAdapter extends BaseAdapter {

    private SendDeliveryActivity activity;
    //产品列表
    private List<OutBoundDetails.GoodList> list;

    public SendDeliveryGoodsAdapter(SendDeliveryActivity activity, List<OutBoundDetails.GoodList> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_send_outbound_good, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        OutBoundDetails.GoodList goodList = list.get(position);
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + goodList.getGoodsName() + "</font>"));
        holder.tvBrand.setText(Html.fromHtml("牌号：<font color=\"#000000\">" + goodList.getBrand() + "</font>"));
        holder.tvSpec.setText(Html.fromHtml("规格/型号：<font color=\"#000000\">" + goodList.getSpec() + "</font>"));
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + goodList.getUnitStr() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + goodList.getNum() + "</font>"));
        holder.tvPrice.setText(Html.fromHtml("单价(元)：<font color=\"#000000\">" + goodList.getProp1() + "</font>"));
        holder.tvMoney.setText(Html.fromHtml("金额(元)：<font color=\"#FF4B4C\">" + goodList.getProp2() + "</font>"));
        holder.tvRemark.setText("备注：" + goodList.getMemo());
        if (goodList.getProp3().equals("1")) {
            holder.tvInvoice.setText(Html.fromHtml("是否开票：<font color=\"#000000\">是</font>"));
        } else {
            holder.tvInvoice.setText(Html.fromHtml("是否开票：<font color=\"#000000\">否</font>"));
        }

        /**
         * 发货
         */
        holder.tvSend.setTag(goodList);
        holder.tvSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.sendDeliveryPersenter.addDialog((OutBoundDetails.GoodList) v.getTag());
            }
        });


        /**
         * 显示批次集合
         */
        holder.listView.setAdapter(new ShowBatchNoAdapter(activity, goodList.getId()));
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
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.tv_invoice)
        TextView tvInvoice;
        @BindView(R.id.tv_send)
        TextView tvSend;
        @BindView(R.id.listView)
        MeasureListView listView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

