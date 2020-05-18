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

public class ProductProgressWasteAdapter extends BaseAdapter {

    private Activity activity;
    private List<ProductProgress.WasteList> list;
    public ProductProgressWasteAdapter(Activity activity, List<ProductProgress.WasteList> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_add_waste, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ProductProgress.WasteList wasteList = list.get(position);
        holder.tvWasteType.setText(Html.fromHtml("类别：<font color=\"#000000\">" + wasteList.getTypeStr() + "</font>"));
        holder.tvDept.setText(Html.fromHtml("部门：<font color=\"#000000\">" + wasteList.getDeptName() + "</font>"));
//        holder.tvBatchNo.setText(Html.fromHtml("批次：<font color=\"#000000\">" + goods.getBatchNo() + "</font>"));
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + wasteList.getGoodsName() + "</font>"));
//        holder.tvBrand.setText(goods.getBrand() + "/" + goods.getSpec());
//        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + goods.getUnitStr() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + wasteList.getNum() + "</font>"));
        holder.tvRemark.setText("备注：" + wasteList.getMemo());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_waste_type)
        TextView tvWasteType;
        @BindView(R.id.tv_dept)
        TextView tvDept;
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

