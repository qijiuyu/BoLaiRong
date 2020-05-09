package com.bian.dan.blr.activity.main.warehouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.warehouse.SdEnterAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手动入库单
 */
public class SdEnterActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.et_key)
    EditText etKey;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //页码
    private int page=1;
    //要搜索的关键字
    private String keys;
    private SdEnterAdapter sdEnterAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd_enter);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("手动入库单");
        imgRight.setImageResource(R.mipmap.add);
        reList.setMyRefreshLayoutListener(this);
        listView.setAdapter(sdEnterAdapter=new SdEnterAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(SdEnterDetailsActivity.class);
            }
        });

        /**
         * 监听关键字搜索
         */
        etKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                keys=s.toString();
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.img_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.img_right:
                setClass(AddSdEnterActivity.class);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page=1;
    }

    @Override
    public void onLoadMore(View view) {
        page++;
    }

}
