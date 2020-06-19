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
import java.util.ArrayList;
import java.util.List;

public class SelectParentDialog extends Dialog implements View.OnClickListener {

    private Activity context;
    private TextView textView;
    private List<StockList.ListBean> listAll;
    //父类仓库名称集合
    private List<String> parentName=new ArrayList<>();
    //父类仓库id集合
    private List<Integer> parentId=new ArrayList<>();
    /**
     * 父类与子类的所选位置
     */
    private int parentPosition;
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

    public SelectParentDialog(Activity context, List<StockList.ListBean> listAll, TextView textView) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.listAll=listAll;
        this.textView=textView;
    }

    private void initView() {
        final CycleWheelView wvParent=findViewById(R.id.wv_parent);
        final CycleWheelView wvChild=findViewById(R.id.wv_child);
        wvChild.setVisibility(View.GONE);
        //获取父类仓库数据
        for (int i=0;i<listAll.size();i++){
             if(listAll.get(i).getParentId()==0){
                 parentName.add(listAll.get(i).getName());
                 parentId.add(listAll.get(i).getId());
             }
        }
        wvParent.setLabels(parentName);

        try {
            wvParent.setWheelSize(5);
        } catch (CycleWheelView.CycleWheelViewException e) {
            e.printStackTrace();
        }
        wvParent.setCycleEnable(false);
        wvParent.setAlphaGradual(0.5f);
        wvParent.setDivider(Color.parseColor("#abcdef"),1);
        wvParent.setSolid(Color.WHITE,Color.WHITE);
        wvParent.setLabelColor(Color.GRAY);
        wvParent.setLabelSelectColor(Color.BLACK);
        wvParent.setOnWheelItemSelectedListener(new CycleWheelView.WheelItemSelectedListener() {
            public void onItemSelected(int position, String label) {
                parentPosition=position;
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
                 textView.setTag(parentId.get(parentPosition));
                 textView.setText(parentName.get(parentPosition));
                 break;
            case R.id.cancle:
                 break;
            case R.id.rel:
                 break;
        }
        dismiss();
    }
}
