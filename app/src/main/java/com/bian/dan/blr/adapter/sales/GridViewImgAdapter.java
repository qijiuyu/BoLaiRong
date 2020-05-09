package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.AddContractActivity;
import com.bumptech.glide.Glide;
import com.zxdc.utils.library.bean.FileBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class GridViewImgAdapter extends BaseAdapter {

    private Activity context;
    public List<FileBean> list;
    public GridViewImgAdapter(Activity context, List<FileBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size()+1;
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

        if(position==list.size()){
            holder.imgDelete.setVisibility(View.GONE);
            holder.image.setImageResource(R.mipmap.add_img);
        }else{
            FileBean fileBean=list.get(position);
            Glide.with(context).load(fileBean.getUrl()).into(holder.image);
            holder.imgDelete.setVisibility(View.VISIBLE);

            /**
             * 删除图片
             */
            holder.imgDelete.setTag(fileBean);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    FileBean fileBean= (FileBean) v.getTag();
                    list.remove(fileBean);
                    notifyDataSetChanged();
                    //如果是编辑中，就要调接口来删除图片
                    if(fileBean.getId()!=-1){
                        if(context instanceof AddContractActivity){
                            ((AddContractActivity)context).deleteImg(fileBean);
                        }
                    }
                }
            });
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.img_delete)
        ImageView imgDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
