package com.bian.dan.blr.adapter.financial;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Wage;

import java.util.List;

public class SalesWageAdapter extends RecyclerView.Adapter<SalesWageAdapter.MyHolder> {

    private Activity activity;
    private List<Wage.ListBean> list;
    private int type;
    public SalesWageAdapter(Activity activity, List<Wage.ListBean> list,int type) {
        super();
        this.activity = activity;
        this.list = list;
        this.type=type;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_sales_wage, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        Wage.ListBean listBean = list.get(i);
//        if(type==1){
//            holder.tvName.setText(listBean.getUserName());
//            holder.tvTotalSales.setVisibility(View.GONE);
//            holder.tvPosition.setVisibility(View.GONE);
//            holder.tvCutMoney.setVisibility(View.GONE);
//            holder.tvEntryMoney.setVisibility(View.GONE);
//            holder.tvTotalMoney.setVisibility(View.GONE);
//        }else{
//            holder.tvName.setVisibility(View.GONE);
//            holder.tvTotalSales.setText(String.valueOf(listBean.getSales()));
//            holder.tvCutMoney.setText(String.valueOf(listBean.getCutWages()));
//            holder.tvEntryMoney.setText(String.valueOf(listBean.getEntryFee()));
//        }
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvPosition,tvTotalSales,tvCutMoney,tvEntryMoney,tvTotalMoney;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvPosition=itemView.findViewById(R.id.tv_position);
            tvTotalSales=itemView.findViewById(R.id.tv_total_sales);
            tvCutMoney=itemView.findViewById(R.id.tv_cut_money);
            tvEntryMoney=itemView.findViewById(R.id.tv_entry_money);
            tvTotalMoney=itemView.findViewById(R.id.tv_total_money);
        }
    }



}

