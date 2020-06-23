package com.bian.dan.blr.activity.main.production;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.NoticeDetailsActivity;
import com.bian.dan.blr.adapter.production.WordListAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Notice;
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
 * 技术文档
 */
public class WordListActivity extends BaseActivity   implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //页码
    private int page=1;
    private WordListAdapter adapter;
    private List<Notice.ListBean> listAll=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("技术文档");
        reList.setMyRefreshLayoutListener(this);

        adapter=new WordListAdapter(this,listAll);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity, NoticeDetailsActivity.class);
                intent.putExtra("listBean",listAll.get(position));
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.lin_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getWordList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getWordList();
    }


    /**
     * 获取技术文档
     */
    private void getWordList(){
        HttpMethod.getNoticeList(2,page,20,new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Notice notice= (Notice) object;
                if(notice.isSussess()){
                    List<Notice.ListBean> list=notice.getData().getRows();
                    listAll.addAll(list);
                    adapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(notice.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }
}
