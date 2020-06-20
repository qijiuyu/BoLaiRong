package com.bian.dan.blr.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.financial.WageManagerActivity;
import com.bian.dan.blr.adapter.financial.SalesWageAdapter;
import com.bian.dan.blr.view.MyOnScrollListener;
import com.bian.dan.blr.view.MyRecyclerView;
import com.bian.dan.blr.view.ObservableHorizontalScrollView;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Wage;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.eventbus.EventStatus;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 销售工资
 */
public class SalesFragment extends BaseFragment  implements MyRefreshLayoutListener {

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
    MyRefreshLayout reList;
    Unbinder unbinder;
    //fragment是否可见
    private boolean isVisibleToUser = false;
    //页码
    private int page = 1;
    private List<Wage.ListBean> listAll = new ArrayList<>();
    private SalesWageAdapter salesWageAdapter1,salesWageAdapter2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册eventBus
        EventBus.getDefault().register(this);
    }

    View view;

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sales, container, false);
        unbinder = ButterKnife.bind(this, view);

        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        //设置不能下刷
        reList.setRefreshEnable(false);
        listName.setLayoutManager(new LinearLayoutManager(mActivity));
        listOther.setLayoutManager(new LinearLayoutManager(mActivity));

        //姓名列表
        salesWageAdapter1 = new SalesWageAdapter(mActivity, listAll,1);
        listName.setAdapter(salesWageAdapter1);

        //右边其他数据列表
        salesWageAdapter2 = new SalesWageAdapter(mActivity, listAll,2);
        listOther.setAdapter(salesWageAdapter2);

        //设置两个列表的同步滚动
        setSyncScrollListener();

        //工资列表
        if (isVisibleToUser && view != null && listAll.size() == 0) {
            getWageList();
        }
        return view;
    }


    @OnClick({R.id.tv_key, R.id.img_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_key:
                break;
            case R.id.img_clear:
                tvKey.setText(null);
                break;
            default:
                break;
        }
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType) {
        switch (eventBusType.getStatus()) {
            //根据时间刷新列表
            case EventStatus.SELECT_WAGE_TIME:
                listAll.clear();
                page = 1;
                if (isVisibleToUser && view != null) {
                    getWageList();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 下刷
     * @param view
     */
    public void onRefresh(View view) {

    }

    /**
     * 上拉加载更多
     *
     * @param view
     */
    public void onLoadMore(View view) {
        page++;
        getWageList();
    }


    /**
     * 工资列表
     */
    private void getWageList() {
        HttpMethod.getWageList(null, "12", ((WageManagerActivity) mActivity).tvTime.getText().toString().trim(), new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Wage wage= (Wage) object;
                if (wage.isSussess()) {
                    List<Wage.ListBean> list = wage.getData();
                    listAll.addAll(list);
                    salesWageAdapter1.notifyDataSetChanged();
                    salesWageAdapter2.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                } else {
                    ToastUtil.showLong(wage.getMsg());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
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


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && view != null && listAll.size() == 0) {
            getWageList();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
