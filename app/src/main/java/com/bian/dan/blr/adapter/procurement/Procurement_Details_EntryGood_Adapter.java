package com.bian.dan.blr.adapter.procurement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.ProcurementDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Procurement_Details_EntryGood_Adapter extends BaseAdapter {

    private Activity activity;
    private List<ProcurementDetails.EntryList> list;
    public Procurement_Details_EntryGood_Adapter(Activity activity,List<ProcurementDetails.EntryList> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_add_procurement_enter, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ProcurementDetails.EntryList entryList=list.get(position);
        holder.tvName.setText(entryList.getGoodsName());
        holder.tvType.setText(entryList.getArriveTypeStr());
        holder.tvBatchNo.setText(entryList.getBatchNo());

//        outBoundDetailsAdapter2 = new OutBoundDetailsAdapter2(activity, 0);
//        holder.listPc.setAdapter(outBoundDetailsAdapter2);
//        outBoundDetailsAdapter2 = new OutBoundDetailsAdapter2(activity, 1);
//        holder.listNum.setAdapter(outBoundDetailsAdapter2);
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_spce)
        TextView tvSpce;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_yes_num)
        TextView tvYesNum;
        @BindView(R.id.tv_no_num)
        TextView tvNoNum;
        @BindView(R.id.tv_batchNo)
        TextView tvBatchNo;
        @BindView(R.id.tv_num)
        TextView tvNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
