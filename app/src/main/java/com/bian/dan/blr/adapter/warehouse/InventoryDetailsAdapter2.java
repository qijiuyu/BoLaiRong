package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.InventoryDetails;
import java.util.List;

public class InventoryDetailsAdapter2 extends RecyclerView.Adapter<InventoryDetailsAdapter2.MyHolder> {

    private Activity activity;
    private List<InventoryDetails.ListBean> list;
    private int type;

    public InventoryDetailsAdapter2(Activity activity, List<InventoryDetails.ListBean> list, int type) {
        super();
        this.activity = activity;
        this.list = list;
        this.type = type;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_inventory_details, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        InventoryDetails.ListBean listBean = list.get(i);
        if(type==2){
            holder.tvName.setVisibility(View.GONE);
        }
        holder.tvName.setText(listBean.getGoodsName());
        holder.tvType.setText(listBean.getTypeStr());
        holder.tvBand.setText(listBean.getBrand());
        holder.tvSpec.setText(listBean.getSpec());
        holder.tvUnit.setText(listBean.getUnitStr());
        holder.tvStockType.setText(listBean.getParentStockTypeStr()+"-"+listBean.getStockTypeStr());
        holder.tvNum.setText(String.valueOf(listBean.getNum()));
        holder.tvBatchNo.setText(listBean.getBatchNo());
    }


    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvType,tvBand,tvSpec,tvUnit,tvStockType,tvNum,tvBatchNo;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvType=itemView.findViewById(R.id.tv_type);
            tvBand=itemView.findViewById(R.id.tv_band);
            tvSpec=itemView.findViewById(R.id.tv_spec);
            tvUnit=itemView.findViewById(R.id.tv_unit);
            tvStockType=itemView.findViewById(R.id.tv_stockType);
            tvNum=itemView.findViewById(R.id.tv_num);
            tvBatchNo=itemView.findViewById(R.id.tv_batchNo);
        }
    }
}

