package com.bian.dan.blr.activity.statistical;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baidu.mapapi.map.MapView;
import com.bian.dan.blr.R;
import com.zxdc.utils.library.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapActivity extends BaseActivity {

    @BindView(R.id.mapview)
    MapView mapview;
    // 用于设置个性化地图的样式文件
    private static final String CUSTOM_FILE_NAME_CX = "map.sty";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

        // 获取.sty文件路径
        String customStyleFilePath = getCustomStyleFilePath(MapActivity.this, CUSTOM_FILE_NAME_CX);
        // 设置个性化地图样式文件的路径和加载方式
//        mapview.setMapCustomStylePath(customStyleFilePath);
//        // 动态设置个性化地图样式是否生效
//        mapview.setMapCustomStyleEnable(true);
    }


    /**
     * 读取json路径
     */
    private String getCustomStyleFilePath(Context context, String customStyleFileName) {
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        String parentPath = null;
        try {
            inputStream = context.getAssets().open(customStyleFileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            parentPath = context.getFilesDir().getAbsolutePath();
            File customStyleFile = new File(parentPath + "/" + customStyleFileName);
            if (customStyleFile.exists()) {
                customStyleFile.delete();
            }
            customStyleFile.createNewFile();

            outputStream = new FileOutputStream(customStyleFile);
            outputStream.write(buffer);
        } catch (IOException e) {
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                return null;
            }
        }
        return parentPath + "/" + customStyleFileName;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapview.onDestroy();
    }


    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapview.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapview.onPause();
    }
}
