package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.ShowImgActivity;
import com.bumptech.glide.Glide;
import com.zxdc.utils.library.bean.FileBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NetGridViewImgAdapter extends BaseAdapter {

    private Activity context;
    public List<FileBean> list;
    public NetGridViewImgAdapter(Activity context, List<FileBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_gridview, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        FileBean fileBean=list.get(position);
        Glide.with(context).load(fileBean.getUrl()).into(holder.image);

        /**
         * 查看大图
         */
        holder.image.setTag(R.id.imageid,position);
        holder.image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<String> imgList=new ArrayList<>();
                for (int i=0;i<list.size();i++){
                     imgList.add(list.get(i).getUrl());
                }
                Intent intent=new Intent(context, ShowImgActivity.class);
                intent.putStringArrayListExtra("imgs", (ArrayList<String>) imgList);
                intent.putExtra("position",(int)v.getTag(R.id.imageid));
                context.startActivity(intent);
            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
