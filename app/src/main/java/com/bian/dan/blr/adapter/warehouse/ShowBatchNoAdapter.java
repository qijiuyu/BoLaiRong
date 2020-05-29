package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.warehouse.SendDeliveryActivity;
import com.zxdc.utils.library.bean.AddBatchno;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowBatchNoAdapter extends BaseAdapter {

    private Activity activity;
    private List<AddBatchno> list;
    public ShowBatchNoAdapter(Activity activity,int id) {
        super();
        this.activity = activity;
        if(activity instanceof SendDeliveryActivity){
            list=((SendDeliveryActivity)activity).map.get(id);
        }
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_batchno, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        AddBatchno addBatchno = list.get(position);
        holder.tvBatchNo.setText(Html.fromHtml("批次：<font color=\"#000000\">" + addBatchno.getBatchNo() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + addBatchno.getNum() + "</font>"));

        /**
         * 删除
         */
        holder.imgDelete.setTag(addBatchno);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddBatchno addBatchno= (AddBatchno) v.getTag();
                list.remove(addBatchno);
                notifyDataSetChanged();
            }
        });


        /**
         * 编辑
         */
        holder.imgEdit.setTag(addBatchno);
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddBatchno addBatchno= (AddBatchno) v.getTag();
                if(activity instanceof SendDeliveryActivity){
                    ((SendDeliveryActivity)activity).sendDeliveryPersenter.editDialog(addBatchno);
                }
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_batchNo)
        TextView tvBatchNo;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.img_edit)
        ImageView imgEdit;
        @BindView(R.id.img_delete)
        ImageView imgDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

