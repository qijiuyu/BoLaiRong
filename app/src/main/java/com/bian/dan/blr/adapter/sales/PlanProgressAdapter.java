package com.bian.dan.blr.adapter.sales;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.PlanDetails;

import java.util.List;

public class PlanProgressAdapter extends RecyclerView.Adapter<PlanProgressAdapter.MyHolder> {

    private Context context;
    private List<PlanDetails.ProgressBean> list;
    public PlanProgressAdapter(Context context, List<PlanDetails.ProgressBean> list) {
        super();
        this.context = context;
        this.list=list;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_plan_progress, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        PlanDetails.ProgressBean progressBean=list.get(i);
        holder.tvName.setText(progressBean.getDeptName());
        holder.tvProgress.setText(progressBean.getStatusStr());
        if(i==0){
            holder.viewLeft.setVisibility(View.GONE);
        }else{
            holder.viewLeft.setVisibility(View.VISIBLE);
        }
        if(i==list.size()-1){
            holder.viewRight.setVisibility(View.GONE);
        }else{
            holder.viewRight.setVisibility(View.VISIBLE);
        }
        switch (progressBean.getStatus()){
            case 1:
                 holder.view.setBackgroundResource(R.drawable.bg_circle_huang);
                 break;
            case 2:
                holder.view.setBackgroundResource(R.drawable.bg_circle_lan);
                break;
            case 3:
                holder.view.setBackgroundResource(R.drawable.bg_circle_lv);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvProgress;
        View view,viewLeft,viewRight;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvProgress=itemView.findViewById(R.id.tv_progress);
            view=itemView.findViewById(R.id.view);
            viewLeft=itemView.findViewById(R.id.view_left);
            viewRight=itemView.findViewById(R.id.view_right);
        }
    }

}

