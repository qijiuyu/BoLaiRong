package com.bian.dan.blr.adapter.production;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.ProductProgress;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 详情页面中--入库产品列表
 */
public class ProductProgressEntryAdapter extends BaseAdapter {

    private Activity activity;
    private List<ProductProgress.EntryList> list;
    public ProductProgressEntryAdapter(Activity activity, List<ProductProgress.EntryList> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_add_product6, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ProductProgress.EntryList entryList = list.get(position);
        holder.tvBatchNo.setText(Html.fromHtml("批次：<font color=\"#000000\">" + entryList.getBatchNo() + "</font>"));
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + entryList.getGoodsName() + "</font>"));
        holder.tvBrand.setText(entryList.getBrand()+"/"+entryList.getSpec());
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + entryList.getUnitStr() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + entryList.getNum() + "</font>"));
        holder.tvRemark.setText("备注：" + entryList.getMemo());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_batchNo)
        TextView tvBatchNo;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_brand)
        TextView tvBrand;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_remark)
        TextView tvRemark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

