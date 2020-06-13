package com.bian.dan.blr.adapter.production;

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
import com.bian.dan.blr.activity.main.production.AddStorageProductActivity;
import com.bian.dan.blr.activity.main.production.ProductProgressDetailsActivity;
import com.bian.dan.blr.application.MyApplication;
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
    //入库状态 0未申请 1未入库 2已入库
    private int entryStatus;

    public ProductProgressEntryAdapter(Activity activity, List<ProductProgress.EntryList> list, int entryStatus) {
        super();
        this.activity = activity;
        this.list = list;
        this.entryStatus = entryStatus;
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_product_progress_entry, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ProductProgress.EntryList entryList = list.get(position);
        holder.tvCreatePeople.setText(Html.fromHtml("申请人：<font color=\"#70DF5D\">" + entryList.getCreateName() + "</font>"));
        holder.tvCreateTime.setText(Html.fromHtml("申请时间：<font color=\"#000000\">" + entryList.getCreateDate() + "</font>"));
        holder.tvBatchNo.setText(Html.fromHtml("批次：<font color=\"#000000\">" + entryList.getBatchNo() + "</font>"));
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + entryList.getGoodsName() + "</font>"));
        holder.tvSpec.setText(Html.fromHtml("规格/型号：<font color=\"#000000\">" + entryList.getSpec() + "</font>"));
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + entryList.getUnitStr() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + entryList.getNum() + "</font>"));
        holder.tvRemark.setText("备注：" + entryList.getMemo());


        /**
         * 在没申请入库前，只有生产组长才可以编辑
         */
        if (entryStatus == 0 && MyApplication.getRoleId() == 3 && activity instanceof ProductProgressDetailsActivity) {
            holder.imgEdit.setVisibility(View.VISIBLE);
            holder.imgEdit.setTag(entryList);
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductProgress.EntryList entryList = (ProductProgress.EntryList) v.getTag();
                    Intent intent = new Intent(activity, AddStorageProductActivity.class);
                    intent.putExtra("entryList", entryList);
                    activity.startActivityForResult(intent, 200);
                }
            });
        }
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_create_people)
        TextView tvCreatePeople;
        @BindView(R.id.tv_create_time)
        TextView tvCreateTime;
        @BindView(R.id.tv_batchNo)
        TextView tvBatchNo;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_spec)
        TextView tvSpec;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.img_edit)
        ImageView imgEdit;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

