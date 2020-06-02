package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.parameter.AddBatchNo2;

import java.util.ArrayList;
import java.util.List;

public class AddBatchNoAdapter extends RecyclerView.Adapter<AddBatchNoAdapter.MyHolder> {

    private Activity activity;
    public List<AddBatchNo2> list;
    private AddBatchNo2 addBatchno;
    /**
     * 1：点击了批次
     * 2：点击了数量
     */
    private int type;
    public AddBatchNoAdapter(Activity activity, List<AddBatchNo2> list) {
        super();
        this.activity = activity;
        this.list=list;
        if(this.list==null || this.list.size()==0){
            this.list=new ArrayList<>();
            this.list.add(new AddBatchNo2());
        }
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_add_batchno, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
        AddBatchNo2 itemAddBatchno=list.get(i);
        holder.etBatchNo.setText(itemAddBatchno.getBatchNo());
        holder.etNum.setText(itemAddBatchno.getNum());
        if(i==list.size()-1){
            holder.imgPlay.setImageResource(R.mipmap.add_batchno);
            holder.imgPlay.setTag(1);
        }else{
            holder.imgPlay.setImageResource(R.mipmap.delete);
            holder.imgPlay.setTag(0);
        }

        /**
         * 监听批次的输入
         */
        holder.etBatchNo.setTag(itemAddBatchno);
        holder.etBatchNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    type=1;
                    addBatchno= (AddBatchNo2) v.getTag();
                    holder.etBatchNo.addTextChangedListener(textWatcher);
                }else{
                    holder.etBatchNo.removeTextChangedListener(textWatcher);
                }
            }
        });


        /**
         * 监听数量的输入
         */
        holder.etNum.setTag(itemAddBatchno);
        holder.etNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    type=2;
                    addBatchno= (AddBatchNo2) v.getTag();
                    holder.etNum.addTextChangedListener(textWatcher);
                }else{
                    holder.etNum.removeTextChangedListener(textWatcher);
                }
            }
        });


        /**
         * 追加
         */
        holder.imgPlay.setTag(R.id.tag1,itemAddBatchno);
        holder.imgPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final int tag=(int)v.getTag();
                AddBatchNo2 itemAddBatchno= (AddBatchNo2) v.getTag(R.id.tag1);
                if(tag==0){
                    list.remove(itemAddBatchno);
                }else{
                    list.add(new AddBatchNo2());
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        EditText etBatchNo,etNum;
        ImageView imgPlay;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            etBatchNo=itemView.findViewById(R.id.et_batchNo);
            etNum=itemView.findViewById(R.id.et_num);
            imgPlay=itemView.findViewById(R.id.img_play);
        }
    }


    /**
     * 监听输入框
     */
    TextWatcher textWatcher=new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        public void afterTextChanged(Editable s) {
            String content=s.toString().trim();
            if(TextUtils.isEmpty(content)){
                return;
            }
            if(type==1){
                addBatchno.setBatchNo(content);
            }else{
                addBatchno.setNum(content);
            }
        }
    };

}

