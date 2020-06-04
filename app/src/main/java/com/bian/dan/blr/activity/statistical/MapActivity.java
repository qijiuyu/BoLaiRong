package com.bian.dan.blr.activity.statistical;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baidu.mapapi.map.MapView;
import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapActivity extends BaseActivity {

    @BindView(R.id.mapview)
    MapView mapview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
    }
}
