package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.PlanDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanDetailsGoodAdapter extends BaseAdapter {

    private Activity activity;
    private List<PlanDetails.GoodBean> list;

    public PlanDetailsGoodAdapter(Activity activity, List<PlanDetails.GoodBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_add_product2, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        PlanDetails.GoodBean goodBean = list.get(position);
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + goodBean.getGoodsName() + "</font>"));
        holder.tvSpec.setText(Html.fromHtml("规格/型号：<font color=\"#000000\">" + goodBean.getSpec() + "</font>"));
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + goodBean.getUnitStr() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + goodBean.getNum() + "</font>"));
        holder.tvRemark.setText("备注：" + goodBean.getMemo());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_spec)
        TextView tvSpec;
        @BindView(R.id.tv_remark)
        TextView tvRemark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

