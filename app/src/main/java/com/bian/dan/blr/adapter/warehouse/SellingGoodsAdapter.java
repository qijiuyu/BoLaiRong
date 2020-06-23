package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.SellingDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellingGoodsAdapter extends BaseAdapter {

    private Activity activity;
    private List<SellingDetails.GoodsList> list;

    public SellingGoodsAdapter(Activity activity, List<SellingDetails.GoodsList> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_selling_goods, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SellingDetails.GoodsList goodsList = list.get(position);
        if(goodsList.getType()==1){
            holder.tvType.setText(Html.fromHtml("类型：<font color=\"#000000\">物料</font>"));
        }else{
            holder.tvType.setText(Html.fromHtml("类型：<font color=\"#000000\">设备</font>"));
        }
        holder.tvStockType.setText(Html.fromHtml("所属仓库/部门：<font color=\"#000000\">" + goodsList.getStockTypeStr()+ "</font>"));
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + goodsList.getGoodsName() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + goodsList.getNum() + "</font>"));
        holder.tvPrice.setText(Html.fromHtml("单价(元)：<font color=\"#000000\">" + goodsList.getUnitPrice() + "</font>"));
        holder.tvMonety.setText(Html.fromHtml("金额(元)：<font color=\"#000000\">" + goodsList.getTotalPrice() + "</font>"));
        holder.tvBatchNo.setText(Html.fromHtml("批号：<font color=\"#000000\">" + goodsList.getBatchNo() + "</font>"));
        holder.tvRemark.setText(Html.fromHtml("备注：<font color=\"#000000\">" + goodsList.getMemo() + "</font>"));
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_stockType)
        TextView tvStockType;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_monety)
        TextView tvMonety;
        @BindView(R.id.tv_batchNo)
        TextView tvBatchNo;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

