package com.bian.dan.blr.view.stocktype;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.view.CycleWheelView;
import com.zxdc.utils.library.bean.StockList;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectChildDialog extends Dialog implements View.OnClickListener {

    private Activity context;
    private TextView textView;
    private List<StockList.ListBean> listAll;
    //子类仓库名称集合
    private List<String> childName=new ArrayList<>();
    //子类仓库id集合
    private List<Integer> childId=new ArrayList<>();
    //父类id
    private int parentId;
    /**
     * 父类与子类的所选位置
     */
    private int childPosition;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_stock);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
        initListener();
    }

    public SelectChildDialog(Activity context, List<StockList.ListBean> listAll, TextView textView,int parentId) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.listAll=listAll;
        this.textView=textView;
        this.parentId=parentId;
    }

    private void initView() {
        final CycleWheelView wvParent=findViewById(R.id.wv_parent);
        wvParent.setVisibility(View.GONE);
        final CycleWheelView wvChild=findViewById(R.id.wv_child);

        /**
         * 通过父类id获取子类数据
         */
        for (int i=0,len=listAll.size();i<len;i++){
            if(listAll.get(i).getParentId()==parentId){
                childName.add(listAll.get(i).getName());
                childId.add(listAll.get(i).getId());
            }
        }
        if(childName.size()==0){
            ToastUtil.showLong("该仓库下没有数据");
            dismiss();
        }
        wvChild.setLabels(childName);

        try {
            wvChild.setWheelSize(5);
        } catch (CycleWheelView.CycleWheelViewException e) {
            e.printStackTrace();
        }

        wvChild.setCycleEnable(false);
        wvChild.setAlphaGradual(0.5f);
        wvChild.setDivider(Color.parseColor("#abcdef"),1);
        wvChild.setSolid(Color.WHITE,Color.WHITE);
        wvChild.setLabelColor(Color.GRAY);
        wvChild.setLabelSelectColor(Color.BLACK);
        wvChild.setOnWheelItemSelectedListener(new CycleWheelView.WheelItemSelectedListener() {
            public void onItemSelected(int position, String label) {
                childPosition=position;
            }
        });
    }

    private void initListener() {
        findViewById(R.id.cancle).setOnClickListener(this);
        findViewById(R.id.confirm).setOnClickListener(this);
        findViewById(R.id.rel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                 textView.setTag(childId.get(childPosition));
                 textView.setText(childName.get(childPosition));
                 break;
            case R.id.cancle:
                 break;
            case R.id.rel:
                 break;
        }
        dismiss();
    }
}
