package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
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
import com.bian.dan.blr.adapter.warehouse.DeviceListAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Device;
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
 * 设备列表
 */
public class DeviceListActivity extends BaseActivity implements MyRefreshLayoutListener{

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_key)
    EditText etKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.img_right)
    ImageView imgRight;
    private DeviceListAdapter deviceListAdapter;
    //列表集合
    private List<Device.ListBean> listAll=new ArrayList<>();
    //页码
    private int page=1;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        ButterKnife.bind(this);
        initView();
        //获取设备列表
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("设备管理");
        imgRight.setImageResource(R.mipmap.add);
        reList.setMyRefreshLayoutListener(this);
        deviceListAdapter = new DeviceListAdapter(this,listAll);
        listView.setAdapter(deviceListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity,DeviceDetailsActivity.class);
                intent.putExtra("listBean",listAll.get(position));
                startActivity(intent);
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
                if(s.length()>0){
                    imgClear.setVisibility(View.VISIBLE);
                }else{
                    imgClear.setVisibility(View.GONE);
                }
                //获取设备列表
                reList.startRefresh();
            }
        });
    }


    /**
     * 按钮点击事件
     *
     * @param view
     */
    @OnClick({R.id.lin_back, R.id.img_right, R.id.img_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //添加
            case R.id.img_right:
                setClass(AddDeviceActivity.class,1000);
                break;
            case R.id.img_clear:
                etKey.setText(null);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page=1;
        listAll.clear();
        getDeviceList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getDeviceList();
    }


    /**
     * 获取设备列表
     */
    private void getDeviceList(){
        HttpMethod.getDeviceList(etKey.getText().toString().trim(), null, String.valueOf(page),new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                Device device= (Device) object;
                if(device.isSussess()){
                    List<Device.ListBean> list=device.getData().getRows();
                    listAll.addAll(device.getData().getRows());
                    deviceListAdapter.notifyDataSetChanged();
                    if(list.size()<HttpMethod.limit){
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(device.getMsg());
                }

            }
            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            //加载数据
            reList.startRefresh();
        }
    }

}
