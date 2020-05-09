package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;

import butterknife.BindView;
import butterknife.ButterKnife;
public class DialogSelectAdapter extends BaseAdapter {

    private Activity activity;
    public int typeIndex;
    private String[] str=new String[]{"状态1","状态2","状态3"};
    public DialogSelectAdapter(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return str.length;
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_dialog_select, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(str[position]);
        if(typeIndex==position){
            holder.imgOk.setVisibility(View.VISIBLE);
            holder.tvName.setTextSize(18);
            holder.tvName.setTextColor(activity.getResources().getColor(R.color.color_007AFF));
        }else{
            holder.imgOk.setVisibility(View.GONE);
            holder.tvName.setTextSize(14);
            holder.tvName.setTextColor(activity.getResources().getColor(android.R.color.black));
        }
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.img_ok)
        ImageView imgOk;
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
