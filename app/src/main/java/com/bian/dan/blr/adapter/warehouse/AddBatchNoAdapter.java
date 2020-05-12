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

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.AddBatchno;

import java.util.List;

public class AddBatchNoAdapter extends RecyclerView.Adapter<AddBatchNoAdapter.MyHolder> {

    private Activity activity;
    private List<AddBatchno> list;
    private AddBatchno addBatchno;
    private int type;
    public AddBatchNoAdapter(Activity activity, List<AddBatchno> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_add_batchno, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
        AddBatchno itemAddBatchno=list.get(i);

        /**
         * 监听批次的输入
         */
        holder.etBatchNo.setTag(itemAddBatchno);
        holder.etBatchNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    type=1;
                    addBatchno= (AddBatchno) v.getTag();
                    holder.etBatchNo.addTextChangedListener(textWatcher);
                }else{
                    holder.etBatchNo.removeTextChangedListener(textWatcher);
                }
            }
        });


        /**
         * 监听数量的输入
         */
        holder.etNum.setTag(addBatchno);
        holder.etNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    type=2;
                    addBatchno= (AddBatchno) v.getTag();
                    holder.etNum.addTextChangedListener(textWatcher);
                }else{
                    holder.etNum.removeTextChangedListener(textWatcher);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        EditText etBatchNo,etNum;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            etBatchNo=itemView.findViewById(R.id.et_batchNo);
            etNum=itemView.findViewById(R.id.et_num);
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
                addBatchno.setNum(Integer.parseInt(content));
            }
        }
    };

}

