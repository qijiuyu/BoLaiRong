package com.bian.dan.blr.adapter.procurement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.ProcurementDetails;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Procurement_Details_EntryGood_Adapter extends BaseAdapter {

    private Activity activity;
    private List<ProcurementDetails.GoodList> list;
    private Map<Integer,Integer> map=new HashMap<>();
    public Procurement_Details_EntryGood_Adapter(Activity activity,List<ProcurementDetails.GoodList> list) {
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
        ProcurementDetails.GoodList goodList=list.get(position);
        holder.tvName.setText(goodList.getGoodsName());
        holder.tvSpce.setText(goodList.getSpec());
        holder.tvUnit.setText(goodList.getUnitStr());
        holder.tvYesNum.setText(String.valueOf(goodList.getArriveNum()));
        holder.tvNoNum.setText(String.valueOf(goodList.getNum()-goodList.getArriveNum()));

        holder.listView.setAdapter(new ShowBatchNoAdapter(activity,goodList.getEntryDetailList()));

        if(map.get(position)!=null){
            holder.linEntry.setVisibility(View.VISIBLE);
        }else{
            holder.linEntry.setVisibility(View.GONE);
        }

        holder.tvNoNum.setTag(position);
        holder.tvNoNum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position= (int) v.getTag();
                if(map.get(position)==null){
                    map.put(position,position);
                }else{
                    map.remove(position);
                }
                notifyDataSetChanged();
            }
        });
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
        @BindView(R.id.tv_yes_num)
        TextView tvYesNum;
        @BindView(R.id.tv_no_num)
        TextView tvNoNum;
        @BindView(R.id.listView)
        MeasureListView listView;
        @BindView(R.id.lin_entry)
        LinearLayout linEntry;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

