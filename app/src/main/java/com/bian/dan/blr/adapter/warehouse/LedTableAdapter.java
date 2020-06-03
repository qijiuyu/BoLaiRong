package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.LedTable;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LedTableAdapter extends BaseAdapter {

    private Activity activity;
    private List<LedTable.ListBean> list;

    public LedTableAdapter(Activity activity, List<LedTable.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_ledtable, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        LedTable.ListBean listBean = list.get(position);
        holder.tvDepartment.setText(Html.fromHtml("领取部门：<font color=\"#000000\">" + listBean.getDeptName() + "</font>"));
        holder.tvName.setText(Html.fromHtml("领取人：<font color=\"#000000\">" + listBean.getReceiveName() + "</font>"));
        holder.tvCreatePeople.setText(Html.fromHtml("操作人：<font color=\"#000000\">" + listBean.getCreateName() + "</font>"));
        holder.tvTime.setText(listBean.getCreateDate());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_department)
        TextView tvDepartment;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_create_people)
        TextView tvCreatePeople;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

