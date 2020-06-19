package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.SelectMaterialActivity;
import com.bian.dan.blr.adapter.warehouse.InventoryDetailsAdapter2;
import com.bian.dan.blr.persenter.warehouse.InventoryPersenter;
import com.bian.dan.blr.view.MyOnScrollListener;
import com.bian.dan.blr.view.MyRecyclerView;
import com.bian.dan.blr.view.ObservableHorizontalScrollView;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Inventory;
import com.zxdc.utils.library.bean.InventoryDetails;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库存明细详情
 */
public class InventoryDetailsListActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.list_name)
    MyRecyclerView listName;
    @BindView(R.id.list_other)
    MyRecyclerView listOther;
    @BindView(R.id.sv_room)
    ObservableHorizontalScrollView svRoom;
    @BindView(R.id.re_list)
    public MyRefreshLayout reList;
    @BindView(R.id.tv_type)
    TextView tvParent;
    @BindView(R.id.tv_list)
    TextView tvChild;
    private InventoryPersenter inventoryPersenter;
    //页码
    private int page = 1;
    private InventoryDetailsAdapter2 inventoryDetailsAdapter1,inventoryDetailsAdapter2;
    private List<InventoryDetails.ListBean> listAll=new ArrayList<>();
    private Inventory.ListBean listBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details_list);
        ButterKnife.bind(this);
        initView();
        //设置左右滑动的listview
        initList();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("库存明细详情");
        inventoryPersenter=new InventoryPersenter(this);
        listBean= (Inventory.ListBean) getIntent().getSerializableExtra("listBean");
        if(listBean!=null){
            tvKey.setTag(listBean.getGoodsId());
            tvKey.setText(listBean.getGoodsName()+"/"+listBean.getBrand()+"/"+listBean.getSpec());
            imgClear.setVisibility(View.VISIBLE);
        }

        /**
         * 监听父级仓库
         */
        tvParent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    tvChild.setTag(null);
                    tvChild.setText(null);
                }
                //加载数据
                reList.startRefresh();
            }
        });


        /**
         * 监听子类仓库
         */
        tvChild.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                //加载数据
                reList.startRefresh();
            }
        });


        /**
         * 监听物料输入框
         */
        tvKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    imgClear.setVisibility(View.VISIBLE);
                }else{
                    imgClear.setVisibility(View.GONE);
                }
                //加载数据
                reList.startRefresh();
            }
        });
    }

    /**
     * 设置左右滑动的listview
     */
    private void initList(){
        //刷新加载
        reList.setMyRefreshLayoutListener(this);

        listName.setLayoutManager(new LinearLayoutManager(this));
        listOther.setLayoutManager(new LinearLayoutManager(this));

        //姓名列表
        inventoryDetailsAdapter1 = new InventoryDetailsAdapter2(this, listAll,1);
        listName.setAdapter(inventoryDetailsAdapter1);

        //右边其他数据列表
        inventoryDetailsAdapter2 = new InventoryDetailsAdapter2(this, listAll,2);
        listOther.setAdapter(inventoryDetailsAdapter2);

        //设置两个列表的同步滚动
        setSyncScrollListener();
    }

    @OnClick({R.id.lin_back, R.id.tv_key,R.id.img_clear,R.id.tv_type, R.id.tv_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择仓库
            case R.id.tv_type:
                inventoryPersenter.getStockList(tvParent);
                break;
            //选择仓库类型
            case R.id.tv_list:
                if(TextUtils.isEmpty(tvParent.getText().toString().trim())){
                    ToastUtil.showLong("请先选择仓库");
                    return;
                }
                inventoryPersenter.showChildStock(tvChild,(int)tvParent.getTag());
                break;
            case R.id.tv_key:
                setClass(SelectMaterialActivity.class,100);
                break;
            case R.id.img_clear:
                tvKey.setTag(null);
                tvKey.setText(null);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getInventoryDetails();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getInventoryDetails();
    }


    /**
     * 获取库存明细列表
     */
    private void getInventoryDetails() {
        String goodId=null,type=null,stockType=null;
        if(!TextUtils.isEmpty(tvKey.getText().toString())){
            goodId=tvKey.getTag().toString();
        }
        if(!TextUtils.isEmpty(tvParent.getText().toString())){
            type=tvParent.getTag().toString();
        }
        if(!TextUtils.isEmpty(tvChild.getText().toString())){
            stockType=tvChild.getTag().toString();
        }
        HttpMethod.getInventoryDetails(goodId,type ,stockType, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
               InventoryDetails inventoryDetails= (InventoryDetails) object;
                if (inventoryDetails.isSussess()) {
                    List<InventoryDetails.ListBean> list = inventoryDetails.getData().getRows();
                    listAll.addAll(list);
                    inventoryDetailsAdapter1.notifyDataSetChanged();
                    inventoryDetailsAdapter2.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                } else {
                    ToastUtil.showLong(inventoryDetails.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && data!=null){
            Material.ListBean listBean= (Material.ListBean) data.getSerializableExtra("listBean");
            if(listBean!=null){
                tvKey.setTag(listBean.getId());
                tvKey.setText(listBean.getName()+"/"+listBean.getBrand()+"/"+listBean.getSpec());
            }
        }
    }


    private final RecyclerView.OnScrollListener mLayerOSL = new MyOnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            // 当楼层列表滑动时，单元（房间）列表也滑动
            listOther.scrollBy(dx, dy);
        }
    };
    private final RecyclerView.OnScrollListener mRoomOSL = new MyOnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            // 当单元（房间）列表滑动时，楼层列表也滑动
            listName.scrollBy(dx, dy);
        }
    };

    /**
     * 设置两个列表的同步滚动
     */
    private void setSyncScrollListener() {
        listName.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            private int mLastY;
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                // 当列表是空闲状态时
                if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                // 若是手指按下的动作，且另一个列表处于空闲状态
                if (e.getAction() == MotionEvent.ACTION_DOWN && listOther.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    // 记录当前另一个列表的y坐标并对当前列表设置滚动监听
                    mLastY = rv.getScrollY();
                    rv.addOnScrollListener(mLayerOSL);
                } else {
                    // 若当前列表原地抬起手指时，移除当前列表的滚动监听
                    if (e.getAction() == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(mLayerOSL);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        listOther.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            private int mLastY;
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN && listName.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    rv.addOnScrollListener(mRoomOSL);
                } else {
                    if (e.getAction() == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(mRoomOSL);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        svRoom.setScrollViewListener(new ObservableHorizontalScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {
                listName.removeOnScrollListener(mLayerOSL);
                listOther.removeOnScrollListener(mRoomOSL);
            }
        });
    }

}
