package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OutBoundDetailsAdapter2 extends BaseAdapter {

    private Activity activity;
    private int type;

    public OutBoundDetailsAdapter2(Activity activity,int type) {
        super();
        this.activity = activity;
        this.type=type;
    }

    @Override
    public int getCount() {
        return 3;
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_outbound_details2, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if(type==0){
            holder.tvText.setText("0001");
        }else{
            holder.tvText.setText("5");
        }
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_text)
        TextView tvText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

