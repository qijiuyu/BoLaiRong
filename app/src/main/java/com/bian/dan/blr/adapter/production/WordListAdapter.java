package com.bian.dan.blr.adapter.production;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Notice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Notice.ListBean> list;

    public WordListAdapter(Activity activity, List<Notice.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_word_list, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Notice.ListBean listBean = list.get(position);
        holder.tvTitle.setText(listBean.getTitle());
        holder.tvName.setText("录入人："+listBean.getCreateName());
        holder.tvTime.setText("录入时间："+listBean.getCreateDate());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

