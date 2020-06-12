package com.bian.dan.blr.activity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Notice;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 公告详情页面
 */
public class NoticeDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    HtmlTextView tvContent;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("公告详情");
        Notice.ListBean listBean= (Notice.ListBean) getIntent().getSerializableExtra("listBean");
        if(listBean!=null){
            tvTitle.setText(listBean.getTitle());
            tvContent.setHtml(listBean.getContent(), new HtmlHttpImageGetter(tvContent));
            scrollView.scrollTo(0,0);
        }
    }
}
